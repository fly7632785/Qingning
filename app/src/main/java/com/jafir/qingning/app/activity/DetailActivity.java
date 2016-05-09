package com.jafir.qingning.app.activity;

import android.support.v7.widget.Toolbar;

import com.jafir.qingning.R;

import org.kymjs.kjframe.SupportActivity;

/**
 * Created by jafir on 16/4/19.
 */
public class DetailActivity extends SupportActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_detail);
    }


    @Override
    public void initData() {
        super.initData();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
