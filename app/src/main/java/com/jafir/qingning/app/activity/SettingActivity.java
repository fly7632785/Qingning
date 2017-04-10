package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jafir.qingning.R;
import com.jafir.qingning.app.manager.ActivityManager;
import com.jafir.qingning.app.util.DataCleanManager;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by jafir on 2017/4/6.
 */

public class SettingActivity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.aty_setting);
    }

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.setting_about, click = true)
    private RelativeLayout mRlAbout;
    @BindView(id = R.id.setting_clear, click = true)
    private RelativeLayout mRlClear;
    @BindView(id = R.id.setting_update, click = true)
    private RelativeLayout mRlUpdate;
    @BindView(id = R.id.setting_login_out, click = true)
    private ImageView logout;
    @BindView(id = R.id.setting_cache)
    private TextView mCache;


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

        try {
            mCache.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.setting_clear:

                DataCleanManager.clearAllCache(this);
                try {
                    mCache.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "清理成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_about:
                startActivity(new Intent(SettingActivity.this,AboutActivity.class));
                break;

            case R.id.setting_update:
                Toast.makeText(this, "已经是最新版本了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_login_out:
                ActivityManager.getInstance().popAllActivity();

                startActivity(new Intent(SettingActivity.this,GuideActivity.class));
                break;
        }
    }

}
