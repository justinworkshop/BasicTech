package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * Created by zhengwei on 2021/5/26.
 */
public class ScoreBarView extends View {

    private Paint mBgPaint = new Paint();
    private Path mLeftPath = new Path();
    private RectF mLeftArcRect = new RectF();

    public ScoreBarView(Context context) {
        super(context);
    }

    public ScoreBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBgPaint = new Paint();
        mBgPaint.setColor(Color.RED);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setDither(true);
        mBgPaint.setStyle(Style.FILL);


        mLeftArcRect.top = 100;
        mLeftArcRect.left = 100;
        mLeftArcRect.right = 200;
        mLeftArcRect.bottom = 200;

        mLeftPath.addArc(mLeftArcRect, 90, 90);
        mLeftPath.moveTo(100, 150);
        mLeftPath.lineTo(100, 100);
        mLeftPath.lineTo(200, 100);
        mLeftPath.lineTo(200, 200);
        mLeftPath.lineTo(150, 200);
        mLeftPath.close();

        mLeftPath.reset();
        // 绘制左-下背景
        canvas.drawPath(mLeftPath, mBgPaint);

        // 使用 path 对图形进行描述（这段描述代码不必看懂）
//        mBgPaint.setStyle(Style.FILL);
//        mLeftPath.addArc(200, 200, 400, 400, -225, 225);
//        mLeftPath.arcTo(400, 200, 600, 400, -180, 225, false);
//        mLeftPath.lineTo(400, 542);
//        canvas.drawPath(mLeftPath, mBgPaint);
    }
}
