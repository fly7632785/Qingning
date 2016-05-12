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
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/12.
 */
public class OrderDetailActivity extends SupportActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.order_layout)
    private LinearLayout mLinear;

    private ArrayList<Bike> mData;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_order_detail);
    }


    @Override
    public void initData() {
        super.initData();
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
}
