package com.jafir.qingning.app.activity.publishEvent;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BaseActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by jafir on 16/6/11.
 */
public class WriteDateActivity extends BaseActivity {

    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.toolbar_confirm, click = true)
    private TextView mConfirm;

    @BindView(id = R.id.time)
    private TextView mTime;
    @BindView(id = R.id.date)
    private TextView mDate;
    @BindView(id = R.id.pick_time, click = true)
    private TextView mPickTime;
    @BindView(id = R.id.pick_date, click = true)
    private TextView mPickDate;


    private int index;
    private String data;
    private String date;
    private String time;


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_write_date);
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
        switch (index) {
            case PublishEventActivity.REQUEST_START_TIME:
                getSupportActionBar().setTitle("选择开始时间");
                break;
            case PublishEventActivity.REQUEST_END_TIME:
                getSupportActionBar().setTitle("选择参与截止时间");
                break;
        }


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.toolbar_confirm:
                Intent intent = new Intent();
                if(StringUtils.isEmpty(time) || StringUtils.isEmpty(date)){
                    ViewInject.toast("未完成日期或时间");
                    return;
                }
                data = date+" "+time;
                intent.putExtra("data",data);
                setResult(index, intent);
                aty.finish();
                break;
            case R.id.pick_time:
                openTimeDialog();
                break;
            case R.id.pick_date:
                openDateDialog();
                break;
        }
    }


    private void openDateDialog() {
        final DatePicker datePicker = new DatePicker(aty);
        AlertDialog dialog = new AlertDialog.Builder(aty)
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        date  = year + "-" + month + "-" + day;
                        mDate.setText(date);
                    }
                })
                .create();
        dialog.show();

    }

    private void openTimeDialog() {
        final TimePicker timePicker = new TimePicker(aty);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                KJLoger.debug("hour" + hourOfDay + ":" + minute);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(aty)
                .setView(timePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = timePicker.getCurrentHour();
                        int minute = timePicker.getCurrentMinute();
                        time = hour+":"+minute;
                        mTime.setText(time);
                    }
                })
                .create();
        dialog.show();
    }
}
