package kmitl.lab03.kawin58070006.simplemydot.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab03.kawin58070006.simplemydot.MainFragment;
import kmitl.lab03.kawin58070006.simplemydot.R;
import kmitl.lab03.kawin58070006.simplemydot.model.Colors;
import kmitl.lab03.kawin58070006.simplemydot.model.Dot;
import kmitl.lab03.kawin58070006.simplemydot.model.Dots;
import kmitl.lab03.kawin58070006.simplemydot.view.DotView;


import static kmitl.lab03.kawin58070006.simplemydot.R.*;

public class MainActivity extends AppCompatActivity {

    private Dots dots;
    private DotView dotView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        if(savedInstanceState == null){
            initialFragment();
        }


    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment.newInstance())
                .commit();
    }





}