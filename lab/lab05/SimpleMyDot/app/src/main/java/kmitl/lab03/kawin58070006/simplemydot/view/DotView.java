package kmitl.lab03.kawin58070006.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.model.Dots;

/**
 * Created by Administrator on 18/9/2560.
 */
public class DotView extends View {
    private Paint paint;
    private Dots allDot;
    GestureDetector gestureDetector;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null) {
            for (Dot dot : allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }


    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y);

        void onDotViewLongPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void OnItemTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                onDotViewPressListener.onDotViewPressed((int) e.getX(), (int) e.getY());
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                onDotViewPressListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
            }
        });
    }

    public DotView(Context context) {
        super(context);
        OnItemTouchListener(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        OnItemTouchListener(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        OnItemTouchListener(context);
        paint = new Paint();
    }

    public void setDots(Dots dots) {
        this.allDot = dots;
    }
}
