package kmitl.lab03.kawin58070006.simplemydot;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab03.kawin58070006.simplemydot.controller.MainActivity;
import kmitl.lab03.kawin58070006.simplemydot.controller.MainShear;
import kmitl.lab03.kawin58070006.simplemydot.model.Colors;
import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.model.Dots;
import kmitl.lab03.kawin58070006.simplemydot.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Dots.OnDotsChangeListener, View.OnClickListener{
    private Dots dots;
    private DotView dotView;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        dotView = (DotView) rootview.findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Dot newDot = new Dot(0, 0, 0, new Colors().getColor(), (Dot.DotChangedListener) MainActivity.this);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Random random = new Random();
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        final int dotPosition = dots.findDot(x, y);
                        if (dotPosition == -1){
                            int color = new Colors().getColor();
                            Dot newDot = new Dot(x, y, 30, color);
                            dots.addDot(newDot);
                        }else{
                            AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                            a_builder.setMessage("Edit or Delete")
                                    .setCancelable(false)
                                    .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Colors colors = new Colors();
                                            dots.getAllDot().get(dotPosition).setColor(colors.getColor());
                                            dots.dotsChanged();

                                        }
                                    })
                                    .setNegativeButton("Delete",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dots.removeBy(dotPosition);
                                        }
                                    }) ;
                            AlertDialog alert = a_builder.create();
                            alert.setTitle("Alert !!!");
                            alert.show();

                            // dots.removeBy(dotPosition);
                        }
                }
                return false;
            }
        });
        initView(rootview);
        return rootview;
    }

    public void initView(View rootView){
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        Button btnRandom = (Button) rootView.findViewById(R.id.btnRandomDot);
        Button btnCapture = (Button) rootView.findViewById(R.id.btnCapture);
        Button btnClear = (Button) rootView.findViewById(R.id.btnClear);
        btnRandom.setOnClickListener(this);
        btnCapture.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable("dots");
        } else {
            dots = new Dots();
        }
        dots.setListener(this);

    }





    public void onRandomDot(View view) {
        Random random = new Random();
        int x = random.nextInt(this.dotView.getWidth());
        int y = random.nextInt(this.dotView.getHeight());
        int color = new Colors().getColor();
        Dot newDot = new Dot(x, y, 30, color);
        dots.addDot(newDot);

    }

    public void clearDot(View view) {
        dots.clearDot();
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onCapture(View view) throws IOException {
        if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        try {
            dotView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(dotView.getDrawingCache());
            dotView.setDrawingCacheEnabled(false);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            String path = Environment.getExternalStorageDirectory().toString()+ "/capture.png";

            File imgFile = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            Intent intent = new Intent(getActivity(), MainShear.class);
            intent.putExtra("capture", byteArray);
            intent.putExtra("path", path);
            startActivity(intent);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRandomDot:
                onRandomDot(view);
                break;
            case R.id.btnClear:
                clearDot(view);
                break;
            case R.id.btnCapture:
                try {
                    onCapture(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
