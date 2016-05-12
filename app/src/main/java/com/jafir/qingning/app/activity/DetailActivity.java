package com.jafir.qingning.app.activity;

import android.support.v7.widget.Toolbar;

import com.jafir.qingning.R;

import org.kymjs.kjframe.SupportActivity;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by jafir on 16/4/19.
 */
public class DetailActivity extends SupportActivity {




    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_detail);
    }


    @Override
    public void initData() {
        super.initData();
        setSupportActionBar(mToolbar);
    }
}
