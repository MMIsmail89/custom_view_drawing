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
        _paintDoodle.setStrokeWidth(5f); // Set the desired stroke width here

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

        /*
            >> case MotionEvent.ACTION_DOWN:: This case handles the ACTION_DOWN event, which occurs when the user presses their finger on the screen.

            >> case MotionEvent.ACTION_MOVE:: This case handles the ACTION_MOVE event, which occurs when the user's finger is moving across the screen after pressing it.

            >> case MotionEvent.ACTION_UP:: This case handles the ACTION_UP event, which occurs when the user releases their finger from the screen.
         */

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
        // postInvalidate();

        return true;
        /*
        >> Use invalidate() when you are on the UI thread and want to schedule a redraw.
        >> Use postInvalidate() when you are on a background or non-UI thread and need to trigger a redraw of a view on the UI thread.
        >> Both methods are essential for ensuring proper synchronization and responsiveness in Android apps,
        especially when dealing with multi-threaded or background processing scenarios.

        >> In general, if you are unsure about the context in which your code is executing,
        or if it's possible for your code to be called from different threads,
        it's a good practice to use postInvalidate() to ensure that view invalidation
        and redraw always happen on the UI thread.

        >> This approach helps prevent concurrency issues and ensures that your UI updates are safe and predictable.

        >> However, if you are certain that your code is running on the UI thread,
        you can use invalidate() for a more immediate redraw.
         */

        // return super.onTouchEvent(event);
        /*
        >> should write super, without the super,
        I would just be calling the method of onTouchEvent in this class
         in an infinite loop!!
         */
    }
}
