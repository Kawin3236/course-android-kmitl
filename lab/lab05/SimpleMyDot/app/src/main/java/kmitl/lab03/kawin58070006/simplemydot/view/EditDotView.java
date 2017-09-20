package kmitl.lab03.kawin58070006.simplemydot.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 19/9/2560.
 */

public class EditDotView extends View {
    public EditDotView(Context context) {
        super(context);
    }









    public EditDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EditDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
