package com.jafir.qingning.app.activity.publishEvent;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BaseActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * Created by jafir on 16/6/11.
 */
public class WriteTextActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.edit)
    private EditText mEdit;
    @BindView(id = R.id.toolbar_confirm, click = true)
    private TextView mConfirm;

    private int index;

    private String data;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_write_text);
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


        mConfirm = (TextView) mToolbar.findViewById(R.id.toolbar_confirm);
        mConfirm.setOnClickListener(this);

        index = getIntent().getIntExtra("key", 0);
        data = getIntent().getStringExtra("data");
        mEdit.setText(data);
        switch (index) {
            case 1:
                getSupportActionBar().setTitle("填写主题");
                break;
            case 3:
                getSupportActionBar().setTitle("填写描述");
                break;
            case 4:
                getSupportActionBar().setTitle("填写目的地");
                break;

        }


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (v.getId() == R.id.toolbar_confirm) {
            String data = mEdit.getText().toString().trim();
            if (org.kymjs.kjframe.utils.StringUtils.isEmpty(data)) {
                ViewInject.toast("请输入内容");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("data", data);
            setResult(index, intent);
            aty.finish();
        }
    }
}
