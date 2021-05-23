package com.example.dagger2demo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dagger2demo.component.DaggerMyComponent;
import com.example.dagger2demo.module.DatabaseModule;
import com.example.dagger2demo.module.HttpModule;
import com.example.dagger2demo.object.DatabaseObject;
import com.example.dagger2demo.object.HttpObject;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Dagger";

    @Inject
    HttpObject httpObject;
    @Inject
    DatabaseObject databaseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMyComponent.create().injectMainActivity(this);
        Log.i(TAG, "HttpObject hashCode: " + httpObject.hashCode());
        Log.i(TAG, "DatabaseObject hashCode: " + databaseObject.hashCode());

        DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .build()
                .injectMainActivity(this);

        Log.i(TAG, "----->");
        Log.i(TAG, "HttpObject hashCode: " + httpObject.hashCode());
        Log.i(TAG, "DatabaseObject hashCode: " + databaseObject.hashCode());
    }
}
