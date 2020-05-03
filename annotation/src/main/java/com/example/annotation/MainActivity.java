package com.example.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.click_inject.InjectUtils;
import com.example.annotation.click_inject.annotation.OnClick;
import com.example.annotation.click_inject.annotation.OnLongClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InjectUtils.injectEvent(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void click(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.btn1:
                text = "Button 1 click";
                break;
            case R.id.btn2:
                text = "Button 2 click";
                break;
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn1, R.id.btn2})
    public boolean longClick(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.btn1:
                text = "Button 1 long click";
                break;
            case R.id.btn2:
                text = "Button 2 long click";
                break;
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        return true;
    }
}
