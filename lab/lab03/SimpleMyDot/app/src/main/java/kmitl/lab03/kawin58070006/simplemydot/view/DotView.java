package kmitl.lab03.kawin58070006.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.kawin58070006.simplemydot.model.Dot;


public class DotView extends View {
    private Paint paint;
    private Dot dot;
    private List<Dot> saveDot = new LinkedList<>();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.dot != null) {
            Random random = new Random();
            for(int i=0; i<saveDot.size(); i++) {


                canvas.drawCircle(saveDot.get(i).getCenterX(), saveDot.get(i).getCenterY(), 30, saveDot.get(i).getPaint());
            }
        }

    }
    public void setList(List<Dot> list){
        this.saveDot = list;

    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


    public void setDot(Dot dot) {
        this.dot = dot;
    }
}
