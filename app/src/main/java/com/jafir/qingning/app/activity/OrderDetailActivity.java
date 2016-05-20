package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Bike;

import org.kymjs.kjframe.SupportActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/12.
 */
public class OrderDetailActivity extends SupportActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;


    @BindView(id = R.id.order_detail_state1_name)
    private TextView mState1Name;
    @BindView(id = R.id.order_detail_state1_time)
    private TextView mState1Time;
    @BindView(id = R.id.order_detail_state1_linear)
    private LinearLayout mState1Linear;



    @BindView(id = R.id.order_detail_state2_name)
    private TextView mState2Name;
    @BindView(id = R.id.order_detail_state2_time)
    private TextView mState2Time;
    @BindView(id = R.id.order_detail_state2_spare_time)
    private TextView mState2SpareTime;
    @BindView(id = R.id.order_detail_state2_start)
    private TextView mState2SStart;
    @BindView(id = R.id.order_detail_state2_linear)
    private LinearLayout mState2Linear;



    @BindView(id = R.id.order_detail_state3_name)
    private TextView mState3Name;
    @BindView(id = R.id.order_detail_state3_time)
    private TextView mState3Time;
    @BindView(id = R.id.order_detail_state3_end)
    private TextView mState3End;
    @BindView(id = R.id.order_detail_state3_pay,click = true)
    private TextView mState3Pay;


    private ArrayList<Bike> mData;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_order_detail);
    }


    @Override
    public void initData() {
        super.initData();

        mData = (ArrayList<Bike>) getIntent().getSerializableExtra("orderdata");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.finish();
            }
        });

        initBikeInfo();

    }

    private void initBikeInfo() {

        for (Bike bike : mData
                ) {
            View view = View.inflate(aty, R.layout.item_pay_linear, null);
            TextView name = (TextView) view.findViewById(R.id.item_pay_linear_name);
            TextView count = (TextView) view.findViewById(R.id.item_pay_linear_count);
            TextView price = (TextView) view.findViewById(R.id.item_pay_linear_price);
            name.setText(bike.getName());
            count.setText("X" + bike.getOrderCount());
            price.setText(bike.getPrice());
            mState1Linear.addView(view);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if(v.getId()==R.id.order_detail_state3_pay){
            Intent intent = new Intent(aty,PayActivity.class);
            intent.putExtra("orderdata",mData);
            startActivity(intent);
        }
    }
}
