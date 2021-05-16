package com.example.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.mvvm.BR;

/**
 * Created by zhengwei on 2021/5/16.
 */
public class Account extends BaseObservable {
    private String name;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}
