package kmitl.lab03.kawin58070006.simplemydot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainShare extends AppCompatActivity {
    private  String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_share);

        Intent intent = getIntent();
        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("capture"),0, intent.getByteArrayExtra("capture").length);
        path = intent.getStringExtra("path");

        ImageView imageView = (ImageView) findViewById(R.id.vCapture);
        imageView.setImageBitmap(bitmap);
    }

    public void onShare(View view){
        File imgFile = new File(path);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imgFile));
        startActivity(Intent.createChooser(intent, "Share to..."));
    }
}
