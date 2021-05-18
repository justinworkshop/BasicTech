package com.example.mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by zhengwei on 2021/5/17.
 */
public class TestViewModel extends ViewModel {
    private MutableLiveData<String> nameEvent = new MutableLiveData<>();

    public MutableLiveData<String> getNameEvent() {
        return nameEvent;
    }

    public static class Factory implements ViewModelProvider.Factory{

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return null;
        }
    }
}
