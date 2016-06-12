package com.jafir.qingning.app.activity.publishEvent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BaseActivity;
import com.jafir.qingning.app.util.OssUtil;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by jafir on 16/6/11.
 */
public class PublishEventActivity extends BaseActivity {

    public static final int REQUEST_TITLE = 1;
    public static final int REQUEST_COVER = 2;
    public static final int REQUEST_DESC = 3;
    public static final int REQUEST_DESTINATION = 4;
    public static final int REQUEST_END_TIME = 5;
    public static final int REQUEST_START_TIME = 6;
    public static final int REQUEST_DAYS = 7;
    public static final int REQUEST_PEOPLE = 8;
    public static final int REQUEST_MONEY = 9;
    public static final int REQUEST_REQUIRE = 10;


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.pulish_title, click = true)
    private LinearLayout mTitle;
    @BindView(id = R.id.pulish_cover, click = true)
    private LinearLayout mCover;
    @BindView(id = R.id.pulish_desc, click = true)
    private LinearLayout mDesc;
    @BindView(id = R.id.pulish_destination, click = true)
    private LinearLayout mDestination;
    @BindView(id = R.id.pulish_end_time, click = true)
    private LinearLayout mEndTime;
    @BindView(id = R.id.pulish_start_time, click = true)
    private LinearLayout mStartTime;
    @BindView(id = R.id.pulish_days, click = true)
    private LinearLayout mDays;
    @BindView(id = R.id.pulish_people, click = true)
    private LinearLayout mPeople;
    @BindView(id = R.id.pulish_money, click = true)
    private LinearLayout mMoney;
    @BindView(id = R.id.pulish_require, click = true)
    private LinearLayout mRequire;

    @BindView(id = R.id.pulish_title_tip)
    private TextView mTitleTip;
    @BindView(id = R.id.pulish_cover_tip)
    private ImageView mCoverTip;
    @BindView(id = R.id.pulish_desc_tip)
    private TextView mDescTip;
    @BindView(id = R.id.pulish_destination_tip)
    private TextView mDestinationTip;
    @BindView(id = R.id.pulish_end_time_tip)
    private TextView mEndTimeTip;
    @BindView(id = R.id.pulish_start_time_tip)
    private TextView mStartTimeTip;
    @BindView(id = R.id.pulish_days_tip)
    private TextView mDaysTip;
    @BindView(id = R.id.pulish_people_tip)
    private TextView mPeopleTip;
    @BindView(id = R.id.pulish_money_tip)
    private TextView mMoneyTip;
    @BindView(id = R.id.pulish_require_tip)
    private TextView mRequireTip;

    private String desc;
    private String title;
    private String destination;
    private String days;
    private String people;
    private String money;
    private String endTime;
    private String startTime;
    private ArrayList<String> require = new ArrayList<>();

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_publish_event);
    }

    private ProgressDialog mProgress;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mProgress == null) {
                        mProgress = new ProgressDialog(aty);
                    } else {
                        mProgress.show();
                    }
                    break;
                case 1:
                    mProgress.cancel();
                    break;
            }
        }
    };

    @Override
    public void initData() {
        super.initData();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.finish();
            }
        });


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.pulish_title:
                Intent intent = new Intent(aty, WriteTextActivity.class);
                intent.putExtra("data", title);
                intent.putExtra("key", REQUEST_TITLE);
                startActivityForResult(intent, REQUEST_TITLE);
                break;
            case R.id.pulish_cover:
                FunctionConfig functionConfig = new FunctionConfig.Builder()
                        .setEnableCamera(true)
                        .setEnableEdit(true)
                        .setEnableCrop(true)
                        .setEnableRotate(true)
                        .setCropSquare(true)
                        .setEnablePreview(true)
                        .build();
                GalleryFinal.openGallerySingle(REQUEST_COVER, functionConfig, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        String path = null;
                        for (PhotoInfo info : resultList
                                ) {
                            path = info.getPhotoPath();
                            KJLoger.debug(path);
                            Bitmap bm = BitmapFactory.decodeFile(path);
                            if (bm != null && mCoverTip != null) {
                                KJLoger.debug("有照片");
                                mCoverTip.setImageBitmap(bm);
                                OssUtil.upload(aty.getApplicationContext(), path, handler);
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        ViewInject.toast("选图失败");
                    }
                });


                break;
            case R.id.pulish_desc:
                Intent descIntent = new Intent(aty, WriteTextActivity.class);
                descIntent.putExtra("data", desc);
                descIntent.putExtra("key", REQUEST_DESC);
                startActivityForResult(descIntent, REQUEST_DESC);
                break;
            case R.id.pulish_destination:
                Intent destinationIntent = new Intent(aty, WriteTextActivity.class);
                destinationIntent.putExtra("data", destination);
                destinationIntent.putExtra("key", REQUEST_DESTINATION);
                startActivityForResult(destinationIntent, REQUEST_DESTINATION);
                break;
            case R.id.pulish_end_time:
                Intent endTime = new Intent(aty, WriteDateActivity.class);
                endTime.putExtra("key", REQUEST_END_TIME);
                startActivityForResult(endTime, REQUEST_END_TIME);
                break;
            case R.id.pulish_start_time:
                Intent startTime = new Intent(aty, WriteDateActivity.class);
                startTime.putExtra("key", REQUEST_START_TIME);
                startActivityForResult(startTime, REQUEST_START_TIME);
                break;
            case R.id.pulish_days:
                Intent days = new Intent(aty, WriteDaysActivity.class);
                days.putExtra("key", REQUEST_DAYS);
                startActivityForResult(days, REQUEST_DAYS);
                break;
            case R.id.pulish_people:
                Intent people = new Intent(aty, WriteDaysActivity.class);
                people.putExtra("key", REQUEST_PEOPLE);
                startActivityForResult(people, REQUEST_PEOPLE);
                break;
            case R.id.pulish_money:
                Intent money = new Intent(aty, WriteDaysActivity.class);
                money.putExtra("key", REQUEST_MONEY);
                startActivityForResult(money, REQUEST_MONEY);
                break;
            case R.id.pulish_require:
                Intent requireIntent = new Intent(aty, WriteRequireActivity.class);
                requireIntent.putExtra("key", REQUEST_REQUIRE);
                requireIntent.putExtra("data", require);
                startActivityForResult(requireIntent, REQUEST_REQUIRE);
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        switch (requestCode) {
            case REQUEST_TITLE:
                title = data.getStringExtra("data");
                mTitleTip.setText(title);
                break;
            case REQUEST_DESC:
                desc = data.getStringExtra("data");
                mDescTip.setText(desc);
                break;
            case REQUEST_DESTINATION:
                destination = data.getStringExtra("data");
                mDestinationTip.setText(destination);
                break;
            case REQUEST_DAYS:
                days = data.getStringExtra("data");
                mDaysTip.setText("活动持续时间" + days + "天");
                break;
            case REQUEST_PEOPLE:
                people = data.getStringExtra("data");
                mPeopleTip.setText("活动人数" + people + "人");
                break;
            case REQUEST_MONEY:
                money = data.getStringExtra("data");
                mMoneyTip.setText("人均" + money + "元");
                break;
            case REQUEST_END_TIME:
                endTime = data.getStringExtra("data");
                mEndTimeTip.setText(endTime);
                break;
            case REQUEST_START_TIME:
                startTime = data.getStringExtra("data");
                mStartTimeTip.setText(startTime);
                break;
            case REQUEST_REQUIRE:
                String ss = "";
                require = (ArrayList<String>) data.getSerializableExtra("data");
                if (require.size() == 0) {
                    return;
                }
                for (int i = 0; i < require.size(); i++) {
                    ss = ss + (i + 1) + "、" + require.get(i) + "  ";
                }
                mRequireTip.setText(ss);
                break;
        }

    }
}
