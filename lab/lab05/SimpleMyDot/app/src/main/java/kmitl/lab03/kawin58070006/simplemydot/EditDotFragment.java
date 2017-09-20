package kmitl.lab03.kawin58070006.simplemydot;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.model.Dots;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {
    private static final String DOT = "dot";
    private static final String DOTS = "dots";
    private static final String POSITION = "position";

    private Dot dot;
    private Dots dots;
    private int dotPosition;
    private int color;


    public EditDotFragment() {
        // Required empty public constructor
    }

    public static EditDotFragment newInstance(Dot dot, Dots dots, int dotPosition) {
        EditDotFragment fragment = new EditDotFragment();
        Bundle args = new Bundle();
        args.putParcelable(DOT, dot);
        args.putParcelable(DOTS, dots);
        args.putInt(POSITION, dotPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        dot = getArguments().getParcelable(DOT);
        dots = getArguments().getParcelable(DOTS);
        dotPosition = getArguments().getInt(POSITION);

        Button btnRed = (Button) rootView.findViewById(R.id.btnRed);
        Button btnBlue = (Button) rootView.findViewById(R.id.btnBlue);
        Button btnGreen = (Button) rootView.findViewById(R.id.btnGreen);
        Button btnCyan = (Button) rootView.findViewById(R.id.btnCyan);
        Button btnYellow = (Button) rootView.findViewById(R.id.btnYellow);
        Button btnGray = (Button) rootView.findViewById(R.id.btnGray);
        Button btnMagenta = (Button) rootView.findViewById(R.id.btnMAGENTA);

        btnGreen.setOnClickListener(this);
        btnCyan.setOnClickListener(this);
        btnYellow.setOnClickListener(this);
        btnGray.setOnClickListener(this);
        btnMagenta.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBlue:
                dot.setColor(Color.BLUE);
                break;
            case R.id.btnRed:
                dot.setColor(Color.RED);
                break;
            case R.id.btnGray:
                dot.setColor(Color.GRAY);
                break;
            case R.id.btnGreen:
                dot.setColor(Color.GREEN);
                break;
            case R.id.btnYellow:
                dot.setColor(Color.YELLOW);
                break;
            case R.id.btnMAGENTA:
                dot.setColor(Color.MAGENTA);
                break;
            case R.id.btnCyan:
                dot.setColor(Color.CYAN);
                break;

        }
        dots.editAttributeDot(dotPosition, dot);
    }

}
