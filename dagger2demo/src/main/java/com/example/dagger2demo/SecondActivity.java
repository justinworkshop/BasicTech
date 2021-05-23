package com.example.dagger2demo;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dagger2demo.component.DaggerMyComponent;
import com.example.dagger2demo.module.DatabaseModule;
import com.example.dagger2demo.module.HttpModule;
import com.example.dagger2demo.object.HttpObject;
import javax.inject.Inject;

/**
 * Created by zhengwei on 2021/5/23.
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.TAG + "-SecondActivity";

    @Inject
    HttpObject httpObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getApplication()).getAppMyComponent().injectSecondActivity(this);

        Log.i(TAG, "----->");
        Log.i(TAG, "HttpObject hashCode: " + httpObject.hashCode());
    }
}
