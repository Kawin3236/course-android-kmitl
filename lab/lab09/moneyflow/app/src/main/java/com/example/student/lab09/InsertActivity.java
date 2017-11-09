package com.example.student.lab09;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class InsertActivity extends AppCompatActivity {
    private Button btnIncom;
    private Button btnPay;
    private Button btnSave;
    private EditText editData;
    private EditText editMoney;
    private MessageDB messageDB;
    private String money;
    private String type;
    private MessageInfo messageInfo;
    private int update = 0;


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

        Intent intent = getIntent();
        if (intent.getParcelableExtra("messageInfo") != null) {
            update = 1;
            messageInfo = intent.getParcelableExtra("messageInfo");
            editData.setText(messageInfo.getData());
            editMoney.setText(messageInfo.getMoney() + "");
        } else {
            update = 0;
            messageInfo = new MessageInfo();
        }

        btnIncom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIncom.setBackgroundColor(Color.BLUE);
                btnPay.setBackgroundColor(Color.WHITE);
                type = "+";
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPay.setBackgroundColor(Color.BLUE);
                btnIncom.setBackgroundColor(Color.WHITE);
                type = "-";
            }
        });


        messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MESSAGE")
                .build();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AsyncTask<Void, Void, List<MessageInfo>>() {
                        @Override
                        protected List<MessageInfo> doInBackground(Void... voids) {
                            messageInfo.setData(editData.getText().toString());
                            messageInfo.setMoney(Double.valueOf(editMoney.getText().toString()));
                            messageInfo.setType(type);
                            if (update == 1) {
                                messageDB.getMessageInfoDAO().update(messageInfo);
                            } else {
                                messageDB.getMessageInfoDAO().insert(messageInfo);
                            }
                            return null;
                        }



                    @Override
                    protected void onPostExecute (List < MessageInfo > messageInfos) {
                        if (type != null && editData != null || editMoney.equals("")) {
                            Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(InsertActivity.this, "Please complete the infomation", Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute();
            }catch (Exception e){
                    Toast.makeText(InsertActivity.this, "Please complete the infomation", Toast.LENGTH_LONG).show();

                }
        }
    });
}
}
