package com.example.annotation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.click_inject.InjectClickEventActivity;
import com.example.annotation.click_inject.InjectUtils;
import com.example.annotation.click_inject.annotation.OnClick;
import com.example.annotation.click_inject.annotation.OnLongClick;
import com.example.annotation.params_inject.ParseParamsActivity;
import com.example.annotation.params_inject.annotation.UserInfo;

import java.util.ArrayList;

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
                ArrayList<UserInfo> list = new ArrayList<>(10);
                list.add(new UserInfo("mark", 10));

                Intent intent = new Intent(this, ParseParamsActivity.class);
                intent.putExtra("name", "zhengwei");
                intent.putExtra("attr", "worker");
                intent.putExtra("array", new int[]{1, 2, 3, 4, 5});
                intent.putExtra("userInfo", new UserInfo("justin", 30));
                intent.putExtra("userInfos", new UserInfo[]{new UserInfo("justin", 29)});
                intent.putParcelableArrayListExtra("userInfoList", list);
                startActivity(intent);

                break;
            case R.id.btn2:
                text = "btn2 click";
                startActivity(new Intent(this, InjectClickEventActivity.class));
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
