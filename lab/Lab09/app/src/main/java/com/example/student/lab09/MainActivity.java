package com.example.student.lab09;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private Button showMessageButton;
    //private MessageDB messageDB;

    private Button btnIncom;
    private Button btnPay;
    private Button btnSave;
    private EditText editData;
    private EditText editMoney;

    private Button showMessageButton;
    private MessageDB messageDB;

    private String data;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MESSAGE")
                .build();
        new AsyncTask<Void, Void, List<MessageInfo>>() {

            @Override
            protected List<MessageInfo> doInBackground(Void... voids) {
                List<MessageInfo> result = messageDB.getMessageInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(MainActivity.this, android.R.layout.simple_list_item_1, messageInfos);

                ListView m = findViewById(R.id.messageList);
                m.setAdapter(adapter);
            }
        }.execute();
        Button btnInput = findViewById(R.id.btnInput);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });


    }
}
