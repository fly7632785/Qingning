package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.view.TimeView;

import org.kymjs.kjframe.SupportActivity;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by jafir on 16/5/12.
 */
public class OrderSuccessActivity extends SupportActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.order_success_time)
    private TimeView mTime;
    @BindView(id = R.id.order_success_go_order_detail,click = true)
    private TextView mGo;
    @BindView(id = R.id.order_success_share,click = true)
    private TextView mShare;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_order_success);
    }


    @Override
    public void initData() {
        super.initData();
        mTime.setTime(30*60);
//        mTime.setTime(10);
        mTime.reStart();
        mGo.setText(Html.fromHtml("<u>使用html实现下划线样式</u>"));
//        mGo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()){
            case R.id.order_success_go_order_detail:
                startActivity(new Intent(aty,OrderDetailActivity.class));
                break;
            case R.id.order_success_share:

                break;
        }

    }
}
