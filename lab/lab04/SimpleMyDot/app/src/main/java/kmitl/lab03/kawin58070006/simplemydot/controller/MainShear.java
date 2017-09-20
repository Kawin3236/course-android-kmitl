package kmitl.lab03.kawin58070006.simplemydot.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import kmitl.lab03.kawin58070006.simplemydot.R;

public class MainShear extends AppCompatActivity {
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shear);

        Intent intent = getIntent();
        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("capture")
                ,0,intent.getByteArrayExtra("capture").length);
        path = intent.getStringExtra("path");

        ImageView imageView = (ImageView) findViewById(R.id.captureView);
        imageView.setImageBitmap(bitmap);
    }
    public void onShear(View view){
        File imgFile = new File(path);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imgFile));
        startActivity(Intent.createChooser(intent, "share to..."));
    }
}
