package com.jafir.qingning.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jafir.qingning.R;

/**
 * Created by jafir on 16/5/12.
 */
public class TimeView extends TextView {

    private long hours;
    private long minutes;
    private long seconds;
    private long diff;
    private long days;
    private long time = 0;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        diff = a.getInteger(R.styleable.TimeView_time, 50) * 1000;
//        Log.d("TAG", "再打印  onCreate :" + diff);

        onCreate();

    }


    /**
     * 根据 attrs 设置时间开始
     */
    private void onCreate() {
        start();
    }

    //开始计时
    private void start() {

        handler.removeMessages(1);

        Log.d("TAG", "再打印  onCreate");
        Log.d("TAG", "再打印  setTime/..................................//////////////////////////");
        getTime();
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    public void log() {
//        Log.d("TAG", "再打印  log/////////////////////////////////////////////////////////////////");
    }


    /**
     * 设置事件
     *
     * @param time
     */
    public void setTime(long time) {
        this.time = time * 1000;
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {         // handle message
//            Log.d("TAG", "再打印  handleMessage");
            switch (msg.what) {
                case 1:
                    setVisibility(View.VISIBLE);
                    diff = diff - 1000;
                    getShowTime();
                    if (diff > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
//                        setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
//            Log.d("TAG", "再打印");
            super.handleMessage(msg);
        }
    };

    /**
     * 得到时间差
     */
    private void getTime() {
//        Log.d("TAG", "再打印 :getTime");

        try {

            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
            if (days == 0) {
                setText(hours + ":" + minutes + ":" + seconds);
            } else {
                setText(days + ":" + hours + ":" + minutes + ":" + seconds);
            }

        } catch (Exception e) {
        }
    }

    /**
     * 获得要显示的时间
     */
    private void getShowTime() {
//        Log.d("TAG", "再打印 :getShowTime");

        days = diff / (1000 * 60 * 60 * 24);
        hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);

        if (days == 0) {
            setText(hours + ":" + minutes + ":" + seconds);

        } else {
            setText(days + ":" + hours + ":" + minutes + ":" + seconds);
        }
    }

    /**
     * 以之前设置的时间重新开始
     */
    public void reStart() {
        this.diff = this.time;
        start();
    }

    /**
     * 设置时间重新开始
     *
     * @param time 重新开始的事件
     */
    public void reStart(long time) {
        if (time > 0) {
            this.diff = time * 1000;
//            Log.d("TAG", "+=========================" + diff);
        }
        start();
    }

}
