package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.jafir.qingning.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by jafir on 16/5/12.
 */
public class GuideBookActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.webview)
    private WebView webView;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_guide_book);
    }


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

        Intent intent = getIntent();
        String mData = intent.getStringExtra("data");

        KJLoger.debug(mData.toString());



//        webView.loadData(mData,"text/html","utf-8");
        webView.loadDataWithBaseURL(null,mData, "text/html",  "utf-8", null);
    }

    /**
     * 解析 修改img的大小 从而适配手机
     * @param htmltext
     * @return
     */
    public static String getNewContent(String htmltext){

        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        return doc.toString();
    }

}
