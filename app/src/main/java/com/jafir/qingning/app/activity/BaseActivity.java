package com.jafir.qingning.app.activity;

import android.os.Bundle;
import android.util.Log;

import com.jafir.qingning.app.manager.ActivityManager;

import org.kymjs.kjframe.SupportActivity;

/**
 * Created by jafir on 16/5/12.
 */
public abstract class BaseActivity extends SupportActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(this);
        Log.d("debug","add :size:"+ActivityManager.getInstance().getCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
        Log.d("debug","remove :size:"+ActivityManager.getInstance().getCount());
    }
}
