package com.example.student.lab09;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by student on 11/3/2017 AD.
 */
@Dao
interface MessageInfoDAO {
    @Insert
    void insert(MessageInfo message);

    @Delete
    void delete(MessageInfo ledgerInfo);

    @Update
    void update(MessageInfo ledgerInfo);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();

    @Query("SELECT SUM(MONEY) FROM MESSAGE_INFO WHERE TYPE ='+'")
    int getIncome();

    @Query("SELECT SUM(MONEY) FROM MESSAGE_INFO WHERE TYPE ='-'")
    int getExpense();
}
