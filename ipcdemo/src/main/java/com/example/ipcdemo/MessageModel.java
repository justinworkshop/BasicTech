package com.example.ipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhengwei on 2021/9/8.
 */
public class MessageModel implements Parcelable {

    protected MessageModel(Parcel in) {
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
