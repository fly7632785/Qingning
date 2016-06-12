package com.jafir.qingning.app.activity;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.view.WheelView;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/6/11.
 */
public class WriteDaysActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.toolbar_confirm, click = true)
    private TextView mConfirm;

    @BindView(id = R.id.wheel_view)
    private WheelView mWheelView;

    private int index;
    private String days;


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_write_days);
    }


    @Override
    public void initData() {
        super.initData();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("选择持续天数");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.finish();
            }
        });


        mConfirm = (TextView) mToolbar.findViewById(R.id.toolbar_confirm);
        mConfirm.setOnClickListener(this);


        ArrayList number = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            number.add("" + i + "");
        }
        mWheelView.setOffset(2);
        mWheelView.setItems(number);
        mWheelView.setSeletion(0);
        mWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                days = item;
            }
        });


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (v.getId() == R.id.toolbar_confirm) {
            Intent intent = new Intent();
            intent.putExtra("data", days);
            setResult(index, intent);
            aty.finish();
        }
    }
}
