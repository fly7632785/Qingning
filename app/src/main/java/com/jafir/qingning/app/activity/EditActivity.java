package com.jafir.qingning.app.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jafir.qingning.R;
import com.jafir.qingning.app.fragment.GuideBookFragment;
import com.jafir.qingning.app.util.OssUtil;
import com.scrat.app.richtext.RichEditText;

import org.greenrobot.eventbus.EventBus;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

import static com.jafir.qingning.app.AppContext.context;

public class EditActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GET_CONTENT = 666;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 444;
    private RichEditText richEditText;
    private String html;
    private String url = "http://qing-ning.oss-cn-shenzhen.aliyuncs.com/20170426023956158";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("文本编辑器");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.finish();
            }
        });
        richEditText = (RichEditText) findViewById(R.id.rich_text);
        richEditText.fromHtml("<blockquote>青城后山一日游</blockquote><ul><li>时间：2017-5-1</li></ul><ul><li>地点：青城后山</li></ul><ul><li>人数：4-6人</li></ul><ul><li>主题：爬山、戏水、吃青城山老腊肉、山顶烧香、下山缆车</li></ul><img width=\"100%\" src=\"http://qing-ning.oss-cn-shenzhen.aliyuncs.com/20170426023910038\"><br><br>泰安古寺<br><img width=\"100%\" src=\"http://qing-ning.oss-cn-shenzhen.aliyuncs.com/20170426023923338\"><br><br>寺庙<br><img width=\"100%\" src=\"http://qing-ning.oss-cn-shenzhen.aliyuncs.com/20170426023943113\"><br><br>山水<br><img width=\"100%\" src=\"http://qing-ning.oss-cn-shenzhen.aliyuncs.com/20170426023956158\"><br><br>古道\n");
        int len = richEditText.getText().length();
        int l = richEditText.length();
        richEditText.setSelection(richEditText.getText().length());

        findViewById(R.id.publish).setOnClickListener(v -> {
            html = richEditText.toHtml();
            Log.d("html", html + "");
            PreferenceHelper.write(EditActivity.this, "html", "html", html);
            EventBus.getDefault().post(new GuideBookFragment.RefreshEvent(url));
            Toast.makeText(getBaseContext(), "发布成功", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.undo:
                richEditText.undo();
                break;
            case R.id.redo:
                richEditText.redo();
                break;
            case R.id.export:
                Log.e("xxx", richEditText.toHtml());
                Intent intent = new Intent(EditActivity.this, GuideBookDetailActivity.class);
                intent.putExtra("data", richEditText.toHtml());
                startActivity(intent);

                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (data == null || data.getData() == null || requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
            return;

        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        //picturePath就是图片在储存卡所在的位置
        String picturePath = cursor.getString(columnIndex);
        Log.d("picturePath", "::" + picturePath);
        cursor.close();

        final Uri uri = data.getData();

        Log.d("uri", "::" + uri);
        Log.d("uripath", "::" + uri.getPath());

        url = OssUtil.upload(this, data.getData().getPath(), handler);


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what ==1) {
                final int width = richEditText.getMeasuredWidth() - richEditText.getPaddingLeft() - richEditText.getPaddingRight();
                richEditText.image(Uri.parse(url), width);
                Log.d("htmal", "1" + richEditText.toHtml());
            }

        }
    };

    /**
     * 加粗
     */
    public void setBold(View v) {
        richEditText.bold(!richEditText.contains(RichEditText.FORMAT_BOLD));
    }

    /**
     * 斜体
     */
    public void setItalic(View v) {
        richEditText.italic(!richEditText.contains(RichEditText.FORMAT_ITALIC));
    }

    /**
     * 下划线
     */
    public void setUnderline(View v) {
        richEditText.underline(!richEditText.contains(RichEditText.FORMAT_UNDERLINED));
    }

    /**
     * 删除线
     */
    public void setStrikethrough(View v) {
        richEditText.strikethrough(!richEditText.contains(RichEditText.FORMAT_STRIKETHROUGH));
    }

    /**
     * 序号
     */
    public void setBullet(View v) {
        richEditText.bullet(!richEditText.contains(RichEditText.FORMAT_BULLET));
    }

    /**
     * 引用块
     */
    public void setQuote(View v) {
        richEditText.quote(!richEditText.contains(RichEditText.FORMAT_QUOTE));
    }

    public void insertImg(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }

//        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
//        getImage.addCategory(Intent.CATEGORY_OPENABLE);
//        getImage.setType("image/*");
//        startActivityForResult(getImage, REQUEST_CODE_GET_CONTENT);
        GalleryFinal.openGallerySingle(1, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                String path = resultList.get(0).getPhotoPath();
                Log.d("path", "::" + path);

                url = OssUtil.upload(EditActivity.this, path, handler);

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });

    }

}
