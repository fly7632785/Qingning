package com.jafir.qingning.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jafir on 16/5/19.
 */
public class Bezier extends View {


    private PointF start, end, control;

    private Paint paint;
    private int centerX;
    private int centerY;


    public Bezier(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);


        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
    }


    public Bezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Bezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        control.x = event.getX();
        control.y = event.getY();
        invalidate();

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(20);
        paint.setColor(Color.GRAY);

        canvas.drawPoint(start.x, start.y, paint);
        canvas.drawPoint(end.x, end.y, paint);
        canvas.drawPoint(control.x, control.y, paint);

        //辅助线

        canvas.drawLine(start.x, start.y, control.x, control.y, paint);
        canvas.drawLine(end.x, end.y, control.x, control.y, paint);
        // 绘制贝塞尔曲线
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, paint);


    }
}
