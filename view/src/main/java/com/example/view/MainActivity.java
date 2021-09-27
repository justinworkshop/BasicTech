package com.example.view;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.view.customview.CustomFrameLayout;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        // ScrollView内部嵌套LinearLayout，测试ACTION_CANCEL事件
        // 产生原因是 ScrollView刚开始不拦截，滑动一段距离才拦截，此时子View就会产生Cancel事件

        final CustomFrameLayout customFrameLayout = findViewById(R.id.cfl);
        final TextView textView = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("removeAllViews", "customFrameLayout.getChildCount()=" + customFrameLayout.getChildCount());
//                customFrameLayout.removeAllViews();
//                customFrameLayout.setVisibility(View.GONE);

                Test test = new Test();

                String path = MainActivity.this.getExternalCacheDir().getAbsolutePath();
                textView.setText("path: " + path + ", uid: " + test.getUid());

                File[] externalCacheDirs = MainActivity.this.getExternalCacheDirs();
                for (int i = 0; i < externalCacheDirs.length; i++) {
                    Log.i("ExternalCacheDir", "i: " + i + " - " + externalCacheDirs[i].getAbsolutePath());
                }
            }
        });
    }
}
