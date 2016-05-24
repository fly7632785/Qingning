package com.jafir.qingning.app.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jafir.qingning.R;
import com.jafir.qingning.app.manager.ActivityManager;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by jafir on 16/5/12.
 */
public class RedBagActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;




    @Override
    public void setRootView() {
        setContentView(R.layout.aty_red_bag);
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





    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.evaluate_submit:
                ActivityManager.getScreenManager().popActivity(OrderSuccessActivity.class);
                ActivityManager.getScreenManager().popActivity(OrderDetailActivity.class);
                ActivityManager.getScreenManager().popActivity(PayActivity.class);
                ActivityManager.getScreenManager().popActivity(this);
                break;
        }
    }
}
