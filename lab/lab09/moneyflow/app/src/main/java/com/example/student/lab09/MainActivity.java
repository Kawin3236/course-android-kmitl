package com.example.student.lab09;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageDB messageDB;
    private Double allMoney;
    private int income;
    private int expense;
    private List<MessageInfo> result;

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
                result = messageDB.getMessageInfoDAO().findAll();
                income = messageDB.getMessageInfoDAO().getIncome();
                expense = messageDB.getMessageInfoDAO().getExpense();
                return result;
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                final ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(MainActivity.this, android.R.layout.simple_list_item_1, messageInfos);
                TextView txtValue = findViewById(R.id.txtValue);
                allMoney = Double.valueOf((txtValue.getText().toString()));
                allMoney += income;
                allMoney -= expense;
                txtValue.setText(String.valueOf(allMoney));
                if (allMoney / income <= 0.25) {
                    txtValue.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));
                } else if (allMoney / income <= 0.5) {
                    txtValue.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_orange_light));
                } else {
                    txtValue.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_light));
                }
                ListView m = findViewById(R.id.messageList);
                m.setAdapter(adapter);
                m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setItems(new CharSequence[]{" Edit", " Delete"},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                startIntent(adapter.getItem(position));
                                                break;
                                            case 1:
                                                new AsyncTask<Void, Void, MessageInfo>() {
                                                    @Override
                                                    protected MessageInfo doInBackground(Void... voids) {
                                                        messageDB.getMessageInfoDAO().delete(adapter.getItem(position));
                                                        finish();
                                                        startActivity(getIntent());
                                                        return null;
                                                    }
                                                }.execute();
                                                break;
                                        }
                                    }
                                });
                        builder.show();
                    }
                });
            }
        }.execute();

        Button btnInput = findViewById(R.id.btnInput);
        btnInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }

    private void startIntent(MessageInfo messageInfo) {
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        intent.putExtra("messageInfo", (Parcelable) messageInfo);
        finish();
        startActivity(intent);
    }
}
