package kmitl.lab03.kawin58070006.simplemydot.model;

/**
 * Created by Administrator on 18/9/2560.
 */

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    private DotChangedListener dotChangedListener;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public Dot(int centerX, int centerY, int radius, int color, DotChangedListener dotChangedListener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
        this.dotChangedListener = dotChangedListener;
    }

    public interface DotChangedListener{
        void onDotChanged(Dot dot);
    }

    public void setDotChangedListener(DotChangedListener dotChangedListener){
        this.dotChangedListener = dotChangedListener;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
