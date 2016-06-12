package com.jafir.qingning.app.activity;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.view.WheelView;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by jafir on 16/6/11.
 */
public class WriteDaysActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.toolbar_confirm, click = true)
    private TextView mConfirm;
    @BindView(id = R.id.left)
    private TextView mLeft;
    @BindView(id = R.id.right)
    private TextView mRight;

    @BindView(id = R.id.edit)
    private EditText mEdit;
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


        index = getIntent().getIntExtra("key", 0);
        switch (index) {
            case PublishEventActivity.REQUEST_DAYS:
                getSupportActionBar().setTitle("选择持续天数");
                mLeft.setText("活动持续");
                mRight.setText("天");
                break;
            case PublishEventActivity.REQUEST_PEOPLE:
                getSupportActionBar().setTitle("选择活动人数");
                mLeft.setText("活动参与");
                mRight.setText("人");
                break;
            case PublishEventActivity.REQUEST_MONEY:
                getSupportActionBar().setTitle("编辑活动金额");
                mLeft.setText("活动金额");
                mRight.setText("元每人");
                mWheelView.setVisibility(View.GONE);
                mEdit.setVisibility(View.VISIBLE);
                break;
        }

        ArrayList number = new ArrayList();
        for (int i = 1; i < 1000; i++) {
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
            String money = mEdit.getText().toString().trim();

            if (index == PublishEventActivity.REQUEST_MONEY && StringUtils.isEmpty(money)) {
                ViewInject.toast("不能为空");
                return;
            }


            if (index == PublishEventActivity.REQUEST_MONEY) {
                intent.putExtra("data", money);
            } else {
                intent.putExtra("data", days);
            }
            setResult(index, intent);
            aty.finish();
        }
    }
}
