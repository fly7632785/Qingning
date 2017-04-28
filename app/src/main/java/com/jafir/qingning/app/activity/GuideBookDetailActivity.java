package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.CommentRecyclerAdapter;
import com.jafir.qingning.app.util.KeyboardUtil;
import com.jafir.qingning.app.view.FullyLinearLayoutManager;
import com.jafir.qingning.model.bean.Comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jafir on 16/5/12.
 */
public class GuideBookDetailActivity extends BaseActivity {

    String defaults = "blockquote><u>Android</u> 端的富文本编辑器</blockquote><ul><li>支持实时编辑</li></ul><ul><li>支持图片插入,加粗,斜体,下划线,删除线,列表,引用块,超链接,撤销与恢复等</li></ul><ul><li>使用<u>Glide</u>加载图片</li></ul><img width=\"100%\" src=\"http://img5.duitang.com/uploads/item/201409/07/20140907195835_GUXNn.thumb.700_0.jpeg\"><img width=\"100%\" src=\"http://www.bz55.com/uploads/allimg/150707/139-150FG61K2.jpg\">";

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.webview)
    private WebView webView;

    private boolean checked = false;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mCommentRecycler;
    private ArrayList<String> mPicData;
    private ArrayList<Comment> mCommentData;
    @BindView(id = R.id.edit_comment)
    private EditText editComment;
    @BindView(id = R.id.send_comment)
    private TextView sendComment;


    private String[] imgUrl = new String[]{
            "http://img4.imgtn.bdimg.com/it/u=3140872691,2829849339&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=4268278613,361861111&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1350047332,1494732171&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3052494958,2009729320&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1815829197,442667104&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1657246505,1863652736&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2992702144,3552013788&fm=21&gp=0.jpg"

    };

    private String[] avatarUrl = new String[]{
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D200/sign=0afb9ebc4c36acaf46e091fc4cd88d03/bd3eb13533fa828b670a4066fa1f4134970a5a0e.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2147665307,4031352505&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2769901330,4132322556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=693362385,3280695814&fm=23&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3047710011,1274531363&fm=23&gp=0.jpg"
    };
    private CommentRecyclerAdapter mCommentAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_guide_book);
    }

    //设置ToolBar的选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.guidebook_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ab_like:
                if (!checked) {
                    item.setIcon(R.mipmap.guide_detail_like_checked);
                } else {
                    item.setIcon(R.mipmap.guide_detail_like_normal);
                }
                checked = !checked;
                break;
        }

        return true;

    }

    @Override
    public void initData() {
        super.initData();

        String html = getIntent().getStringExtra("data");
        if (!TextUtils.isEmpty(html)) {
            defaults = html;
        }

        /**
         *
         * 因为已经 setSupportActionBar(mToolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         * 所以这里的代码 都写到Activity去了  直接重写 onOptionsItemSelected
         * 和 onCreateOptionsMenu
         *
         */
//        Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.ab_like:
//                        ImageView imageView= (ImageView) menuItem.getActionView();
//                        if(checked) {
//                            imageView.setImageResource(R.mipmap.guide_detail_like_checked);
//                        }else {
//                            imageView.setImageResource(R.mipmap.guide_detail_like_normal);
//                        }
//                        checked = !checked;
//                        break;
//                }
//
//                return true;
//            }
//        };
//        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
//        mToolbar.inflateMenu(R.menu.guidebook_detail_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.finish();
            }
        });

        Intent intent = getIntent();
        String mData = intent.getStringExtra("data");


        mData = defaults;
        KJLoger.debug(mData.toString());

        /**
         * 要制定utl-8 不然乱码
         */
        webView.loadDataWithBaseURL(null, mData, "text/html", "utf-8", null);

        LinearLayoutManager lllayout = new FullyLinearLayoutManager(aty);
        lllayout.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentRecycler.setLayoutManager(lllayout);
//        //模拟数据
        mCommentData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setName("我的名字" + i);
            comment.setContent("评论评论评论评论评论评论评论评论评论评论评论评论");
            comment.setTime("2014-3-3 11:32");
            comment.setImgUrl(avatarUrl[i % avatarUrl.length]);
            mCommentData.add(comment);
        }
        mCommentAdapter = new CommentRecyclerAdapter();

        mCommentAdapter.setData(mCommentData);
        mCommentRecycler.setAdapter(mCommentAdapter);
        mCommentRecycler.setNestedScrollingEnabled(false);
        mCommentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });


        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(editComment.getText().toString())) {
                    return;
                }
                Comment comment = new Comment();
                comment.setContent(editComment.getText().toString());
                comment.setName("jafir");
                String time = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
                comment.setTime(time);
                comment.setImgUrl(avatarUrl[10 % avatarUrl.length]);
                mCommentAdapter.addItem(comment);
                mCommentAdapter.notifyDataSetChanged();
                KeyboardUtil.closeKeybord(editComment, GuideBookDetailActivity.this);
                editComment.getText().clear();
            }
        });

    }

    /**
     * 解析 修改img的大小 从而适配手机
     *
     * @param htmltext
     * @return
     */
    public static String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        return doc.toString();
    }

}
