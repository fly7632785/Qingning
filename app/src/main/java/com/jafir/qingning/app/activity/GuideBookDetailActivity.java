package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.jafir.qingning.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

/**
 * Created by jafir on 16/5/12.
 */
public class GuideBookDetailActivity extends BaseActivity {

    String defaults = "<blockquote><u>Android</u> 端的富文本编辑器</blockquote><ul><li>支持实时编辑</li></ul><ul><li>支持图片插入,加粗,斜体,下划线,删除线,列表,引用块,超链接,撤销与恢复等</li></ul><ul><li>使用<u>Glide</u>加载图片</li></ul><img width=\"100%\" src=\"http://img5.duitang.com/uploads/item/201409/07/20140907195835_GUXNn.thumb.700_0.jpeg\"><img width=\"100%\" src=\"http://www.bz55.com/uploads/allimg/150707/139-150FG61K2.jpg\">";

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.webview)
    private WebView webView;

    private boolean checked = false;

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
