package com.example.customviewdrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DoodleView extends View {

    private Paint _paintDoodle = new Paint();
    private Path _pathDoodle = new Path();
    public DoodleView(Context context) {
        super(context);

        init(null, 0);
    }

    public DoodleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    public DoodleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    //

    private void init( @Nullable AttributeSet attrs, int defStyleAttr) {

        _paintDoodle.setColor(Color.RED);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        
        /*
        >> Anti-aliasing is a technique used in computer graphics to smooth out the jagged,
         or pixelated edges of geometric shapes or text,
         making them appear smoother and more visually appealing.
         It works by blending the edges of shapes or text with the background color,
         creating the illusion of smoother curves and edges.
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // canvas.drawLine(0, 0 , getWidth(), getHeight(), _paintDoodle);
        canvas.drawPath(_pathDoodle, _paintDoodle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                _pathDoodle.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _pathDoodle.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();
        return true;
        // return super.onTouchEvent(event);
        /*
        >> should write super, without the super,
        I would just be calling the method of onTouchEvent in this class
         in an infinite loop!!
         */
    }
}
