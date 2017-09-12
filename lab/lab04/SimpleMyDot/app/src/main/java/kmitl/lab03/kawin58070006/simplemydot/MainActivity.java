package kmitl.lab03.kawin58070006.simplemydot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    private Dot dot;
    private DotView dotView;
    private List<Dot> saveDot = new LinkedList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dot);
        this.dotView.setList(saveDot);

        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dot = new Dot(0, 0, 0, MainActivity.this);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dot.setCenterX((int) event.getX());
                        dot.setCenterY((int) event.getY());
                        Random random = new Random();
                        int r = random.nextInt(255);
                        int g = random.nextInt(255);
                        int b = random.nextInt(255);
                        dot.setPaint(r, g, b);
                        saveDot.add(dot);
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return true;
            }
        });
    }

    public void onRandomDot(View view) {
        dot = new Dot(0, 0, 0, this);
        Random random = new Random();
        int centerX = random.nextInt(1000);
        int centerY = random.nextInt(900);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        this.dot.setPaint(r, g, b);
        saveDot.add(dot);
    }

    public void clearDot(View view) {
        saveDot.clear();
        dotView.invalidate();

    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onCapture(View view) throws IOException {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        try {


            dotView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(dotView.getDrawingCache());
            dotView.setDrawingCacheEnabled(false);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            String path = Environment.getExternalStorageDirectory().toString() + "/capture.png";

            File imgFile = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            Intent intent = new Intent(MainActivity.this, MainShare.class);
            intent.putExtra("capture", byteArray);
            intent.putExtra("path", path);
            startActivity(intent);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
