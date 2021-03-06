package com.project.demorecord;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoListActivity extends AppCompatActivity {

    public static final String EXTTRA_LIST = "EXTTRA_LIST";

    private UserInfoList userInfoList;

    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;

    private MyAdapter adapter;
    private CommonSharePreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);
        preference = new CommonSharePreference(this);
        userInfoList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);

        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        //UserInfoList suggestSearchList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);
        if (userInfoList != null) {
            displaySuggestsList(userInfoList.getUserInfoList());

        } else {
            displaySuggestsList(new ArrayList<UserInfo>());
        }
    }

    @SuppressLint("WrongConstant")
    @OnClick(R.id.btnClearList)
    public void clearList() {
        userInfoList.getUserInfoList().clear();
        preference.save(UserInfoListActivity.EXTTRA_LIST, userInfoList);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.EXTRA_DOCK_STATE_CAR);
        startActivity(i);
    }

    public void displaySuggestsList(List<UserInfo> suggestsList) {
        if (suggestsList.size() <= 0) {
            userInfoList = new UserInfoList();
            userInfoList.setUserInfoList(suggestsList);
            textNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            textNotFound.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter.setData(suggestsList);
            adapter.notifyDataSetChanged();
        }

    }

}
