package com.example.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by zhengwei on 2021/7/23.
 */
public class CustomFrameLayout extends FrameLayout {


    public CustomFrameLayout(@NonNull Context context) {
        super(context);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        Log.i("CustomFrameLayout", "removeAllViews");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Toast.makeText(getContext(), "onDetachedFromWindow", Toast.LENGTH_SHORT).show();
    }
}
