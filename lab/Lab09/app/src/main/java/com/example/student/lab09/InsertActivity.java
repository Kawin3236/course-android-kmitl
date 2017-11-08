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

import java.util.List;

public class InsertActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_insert);

        btnIncom = findViewById(R.id.btnIncom);
        btnPay = findViewById(R.id.btnPay);
        btnSave = findViewById(R.id.btnSave);

        editData = findViewById(R.id.editData);

        editMoney = findViewById(R.id.editMoney);
        money = editMoney.getText().toString();


        messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MESSAGE")
                .build();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, List<MessageInfo>>() {


                    @Override
                    protected List<MessageInfo> doInBackground(Void... voids) {
                        MessageInfo messageInfo = new MessageInfo();

                        messageInfo.setData(editData.getText().toString());
                        messageInfo.setMoney(editMoney.getText().toString());
                        messageDB.getMessageInfoDAO().insert(messageInfo);
                        List<MessageInfo> result = messageDB.getMessageInfoDAO().findAll();
                        return result;
                    }

                    @Override
                    protected void onPostExecute(List<MessageInfo> messageInfos) {
                        ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(InsertActivity.this, android.R.layout.simple_list_item_1, messageInfos);
//                        ListView m = findViewById(R.id.listData);
//                        m.setAdapter(adapter);
//                        ListView m2 = findViewById(R.id.messageList);
//                        m2.setAdapter(adapter);
                        Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                        startActivity(intent);


                    }
                }.execute();
            }
        });


    }





}
