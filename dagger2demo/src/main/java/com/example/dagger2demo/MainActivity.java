package com.example.dagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dagger2demo.component.DaggerMyComponent;
import com.example.dagger2demo.di.Presenter;
import com.example.dagger2demo.module.DatabaseModule;
import com.example.dagger2demo.module.HttpModule;
import com.example.dagger2demo.object.DatabaseObject;
import com.example.dagger2demo.object.HttpObject;
import javax.inject.Inject;

/**
 * 基本使用
 * 1.Module提供对象
 * 2.Component声明有哪些Module
 * 3.调用Component开始inject
 *
 * Single使用方式
 * Module声明Singleton,所有依赖此Module的Component也要声明Singleton
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Dagger";

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject1;
    @Inject
    DatabaseObject databaseObject;
    @Inject
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_msg_info).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

//        DaggerMyComponent.create().injectMainActivity(this);
//        Log.i(TAG, "HttpObject hashCode: " + httpObject.hashCode());
//        Log.i(TAG, "HttpObject1 hashCode: " + httpObject1.hashCode());
//        Log.i(TAG, "DatabaseObject hashCode: " + databaseObject.hashCode());

        ((BaseApplication)getApplication()).getAppMyComponent().injectMainActivity(this);

        Log.i(TAG, "----->");
        Log.i(TAG, "HttpObject hashCode: " + httpObject.hashCode());
        Log.i(TAG, "HttpObject1 hashCode: " + httpObject1.hashCode());
        Log.i(TAG, "DatabaseObject hashCode: " + databaseObject.hashCode());

        Log.i(TAG, "" + "presenter hashCode: " + presenter.hashCode());
    }
}
