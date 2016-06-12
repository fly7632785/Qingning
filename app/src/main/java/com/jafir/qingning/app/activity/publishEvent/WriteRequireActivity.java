package com.jafir.qingning.app.activity.publishEvent;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/6/11.
 */
public class WriteRequireActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.toolbar_confirm, click = true)
    private TextView mConfirm;
    private int index;

    @BindView(id = R.id.linear)
    private LinearLayout mLinear;

    @BindView(id = R.id.add, click = true)
    private ImageView mAdd;

    private ArrayList<String> data = new ArrayList<>();

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_write_require);
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


        data = (ArrayList<String>) getIntent().getSerializableExtra("data");

        if (data.size() == 0 || data == null) {
            EditText e = new EditText(aty);
            e.setHint("如：对商家的期望和一些个人化要求");
            mLinear.addView(e);
        } else {
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                EditText e = new EditText(aty);
                e.setText(s);
                mLinear.addView(e);

            }
        }

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.toolbar_confirm:
                Intent intent = new Intent();
                getData();
                intent.putExtra("data", data);
                setResult(index, intent);
                aty.finish();
                break;
            case R.id.add:
                EditText e = new EditText(aty);
                e.requestFocus();
                mLinear.addView(e);
        }
    }

    private void getData() {

        for (int i = 0; i < mLinear.getChildCount(); i++) {
            EditText e = (EditText) mLinear.getChildAt(i);
            String s = e.getText().toString().trim();
            if (!org.kymjs.kjframe.utils.StringUtils.isEmpty(s)) {
                data.add(s);
            }
        }

    }


}
