package com.example.mvvm.viewmodel;

import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.mvvm.BR;
import com.example.mvvm.model.AccountModel;
import com.example.mvvm.model.AccountModel.Callback;

/**
 * Created by zhengwei on 2021/5/17.
 */
public class AccountViewModel extends BaseObservable {
    private String level;
    private String userInput;
    private AccountModel model;

    public AccountViewModel() {
        model = new AccountModel();
    }

    @Bindable
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }

    @Bindable
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        notifyPropertyChanged(BR.userInput);
    }

    public void onQuery(View view) {
        model.queryLevel(userInput, new Callback() {
            @Override
            public void onSuccess(String level) {
                setLevel(level);
            }

            @Override
            public void onFailed(String msg) {
                setLevel(msg);
            }
        });
    }
}
