package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Bike;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/12.
 */
public class PayActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.pay_layout)
    private LinearLayout mLinear;
    @BindView(id = R.id.pay_done,click = true)
    private TextView mDone;
    @BindView(id = R.id.pay_store_go,click = true)
    private ImageView mStoreGo;
    @BindView(id = R.id.pay_red_bag_go,click = true)
    private ImageView mRedBagGo;
    @BindView(id = R.id.pay_way_online_go,click = true)
    private ImageView mPayWayOnline;
    @BindView(id = R.id.pay_way_red_bag_go,click = true)
    private ImageView mPayWayRedBag;


    private ArrayList<Bike> mData;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_pay);
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
        mData = (ArrayList<Bike>) intent.getSerializableExtra("orderdata");


        initBikeInfo();


        KJLoger.debug(mData.toString());
    }

    private void initBikeInfo() {

        for (Bike bike : mData
                ) {
            View view = View.inflate(aty, R.layout.item_order_linear, null);
            TextView name = (TextView) view.findViewById(R.id.item_order_linear_name);
            TextView count = (TextView) view.findViewById(R.id.item_order_linear_count);
            TextView price = (TextView) view.findViewById(R.id.item_order_linear_price);
            name.setText(bike.getName());
            count.setText("X" + bike.getOrderCount());
            price.setText(bike.getPrice());
            mLinear.addView(view);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.pay_done:
                startActivity(new Intent(aty,EvaluateActivity.class));
                break;

            case R.id.pay_red_bag_go:
                startActivity(new Intent(aty,RedBagActivity.class));
                break;
            case R.id.pay_store_go:
                startActivity(new Intent(aty,StoreChitActivity.class));
                break;
        }
    }
}
