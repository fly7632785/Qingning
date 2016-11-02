package com.jafir.qingning.app.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jafir.qingning.R;

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


}
