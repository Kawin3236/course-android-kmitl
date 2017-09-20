package kmitl.lab03.kawin58070006.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 18/9/2560.
 */

public class Dots implements Parcelable{
    private List<Dot> allDot = new ArrayList<>();
    private OnDotsChangeListener listener;

    public List<Dot> getAllDot(){
        return allDot;
    }
    public void addDot(Dot dot){
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public interface OnDotsChangeListener{
        void onDotsChanged(Dots dots);
    }
    public void setListener(OnDotsChangeListener listener){
        this.listener = listener;
    }

    public void clearDot(){
        allDot.clear();
        this.listener.onDotsChanged(this);

    }

    public int findDot(int x, int y){
        for (int i = 0; i<allDot.size(); i++){
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance<=30){
                return i;
            }
        }return -1;
    }

    public int findColorDot(int dotPosition){
        return this.allDot.get(dotPosition).getColor();
    }

    public void dotsChanged(){
        this.listener.onDotsChanged(this);
    }


    public void removeBy(int dot){
        allDot.remove(dot);
        this.listener.onDotsChanged(this);
    }

    public void editAttributeDot(int position, Dot dot) {
        allDot.set(position, dot);
        this.listener.onDotsChanged(this);
    }
}
