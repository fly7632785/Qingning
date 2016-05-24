package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.view.TimeView;
import com.jafir.qingning.model.bean.Bike;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/12.
 */
public class OrderSuccessActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.order_success_time)
    private TimeView mTime;
    @BindView(id = R.id.order_success_go_order_detail,click = true)
    private TextView mGo;
    @BindView(id = R.id.order_success_share,click = true)
    private TextView mShare;

    private ArrayList<Bike> mData;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_order_success);
    }


    @Override
    public void initData() {
        super.initData();
        mData = (ArrayList<Bike>) getIntent().getSerializableExtra("orderdata");

        mTime.setTime(30*60);
//        mTime.setTime(10);
        mTime.reStart();
        mGo.setText(Html.fromHtml("<u>查看预定详情</u>"));
//        mGo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()){
            case R.id.order_success_go_order_detail:
                Intent intent = new Intent(aty,OrderDetailActivity.class);
                intent.putExtra("orderdata",mData);
                startActivity(intent);
                break;
            case R.id.order_success_share:

                break;
        }

    }
}
