package kmitl.lab03.kawin58070006.simplemydot.model;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private DotChangedListener listener;
    private Paint paint = new Paint();

    public Dot(int centerX, int centerY, int radius, DotChangedListener listener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;
        this.listener.onDotChanged(this);
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public void setPaint(int r, int g, int b){
        paint.setColor(Color.argb(255, r, g, b));
    }

    public Paint getPaint(){
        return paint;
    }

    public interface DotChangedListener{
        void onDotChanged(Dot dot);
    }

    public void setDotChangedListener(DotChangedListener listener){
        this.listener = listener;
    }


    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
