package com.jafir.qingning.app.activity;

import android.os.Bundle;

import com.jafir.qingning.app.manager.ActivityManager;

import org.kymjs.kjframe.SupportActivity;

/**
 * Created by jafir on 16/5/12.
 */
public abstract  class  BaseActivity extends SupportActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getScreenManager().pushActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
