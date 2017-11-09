package com.example.student.lab09;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 11/3/2017 AD.
 */
@Entity(tableName = "MESSAGE_INFO")
class MessageInfo implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Data")
    private String data;

    @ColumnInfo(name = "Money")
    private Double money;

    public MessageInfo(String data, Double money) {
        this.data = data;
        this.money = money;
    }

    public MessageInfo() {

    }

    protected MessageInfo(Parcel in) {
        id = in.readInt();
        type = in.readString();
        data = in.readString();
        money = Double.valueOf(in.readString());
    }

    public static final Creator<MessageInfo> CREATOR = new Creator<MessageInfo>() {
        @Override
        public MessageInfo createFromParcel(Parcel in) {
            return new MessageInfo(in);
        }

        @Override
        public MessageInfo[] newArray(int size) {
            return new MessageInfo[size];
        }
    };

    @Override
    public String toString() {
        return String.format("%s  |                             %s |   จำนวน    %s   บาท", type, data, money);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeString(data);
        dest.writeString(String.valueOf(money));
    }
}
