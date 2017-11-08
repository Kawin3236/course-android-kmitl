package com.example.student.lab09;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by student on 11/3/2017 AD.
 */
@Dao
interface MessageInfoDAO {
    @Insert
    void insert(MessageInfo message);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();
}
