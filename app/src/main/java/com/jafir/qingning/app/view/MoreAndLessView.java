package com.jafir.qingning.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;

/**
 * Created by jafir on 16/5/11.
 */
public class MoreAndLessView extends LinearLayout implements View.OnClickListener {

    private ImageView right;
    private ImageView left;
    private TextView text;
    private int count;
    private int leftImg0 = R.mipmap.ic_launcher;
    private int leftImg = R.mipmap.ic_launcher;
    private int rightImg = R.mipmap.ic_launcher;
    private Context context;

    private CountChangedListener listener;

    public CountChangedListener getListener() {
        return listener;
    }

    public void setListener(CountChangedListener listener) {
        this.listener = listener;
    }

    public MoreAndLessView(Context context) {
        super(context);
        init(context);
    }

    public MoreAndLessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoreAndLessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.layout_more_less, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        right = (ImageView) view.findViewById(R.id.layout_more);
        left = (ImageView) view.findViewById(R.id.layout_less);
        text = (TextView) view.findViewById(R.id.layout_count);
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        view.setOnClickListener(this);
        left.setImageResource(leftImg0);
        this.addView(view);

    }

    public void setImgs(int leftImg, int leftImg0, int rightImg) {
        this.leftImg = leftImg;
        this.leftImg0 = leftImg0;
        this.rightImg = rightImg;

        left.setImageResource(leftImg0);
        right.setImageResource(rightImg);
    }

    public int getCount() {
        return count;
    }


    public void setImgSize(int size) {
        size = (int) (size * context.getResources().getDisplayMetrics().density);
        left.setLayoutParams(new LayoutParams(size, size));
        right.setLayoutParams(new LayoutParams(size, size));
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void changeCount(int count){
        this.count = count;
        text.setText(""+count);
        if(count == 0){
            left.setImageResource(leftImg0);
        }else {
            left.setImageResource(leftImg);
        }
    }


    public void setTextSize(int size) {
        text.setTextSize(size);
    }

    public void setTextColor(int color) {
        text.setTextColor(color);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_less:
                leftClick();
                break;
            case R.id.layout_more:
                rightClick();
                break;
        }
    }


    private void leftClick() {
        if (count == 1) {
            left.setImageResource(leftImg0);
            count--;
        } else if (count <= 0) {
            return;
        } else {
            count--;
            left.setImageResource(leftImg);
        }
        text.setText(count + "");
        listener.onCountChanged(count);
    }

    private void rightClick() {
        if (count >= 0) {
            left.setImageResource(leftImg);
        }
        count++;
        text.setText(count + "");
        listener.onCountChanged(count);
    }

    public  interface CountChangedListener{
         void onCountChanged(int count);
    }
}
