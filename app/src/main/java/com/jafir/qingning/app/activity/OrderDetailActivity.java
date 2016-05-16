package com.jafir.qingning.app.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;

import org.kymjs.kjframe.SupportActivity;
import org.kymjs.kjframe.ui.BindView;

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
    @BindView(id = R.id.order_detail_state3_pay)
    private TextView mState3Pay;



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
    }

}
