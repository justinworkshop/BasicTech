package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 1.BitmapFactory.Options参数使用
 * 2.Bitmap内存复用，只要将要分配的内存小于当前使用的内存，即可复用当前使用的内存
 * 3.BitmapRegionDecoder图片分片加载到内存，配合手势操作
 * 有开源LargeImageView
 * 4.Bitmap缓存，常用有LruCache
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Bitmap";

    private ImageView mImageView;
    private Button mButton;
    private Bitmap reuseBitmap;

    private int resIndex;
    private int[] resIds = {R.drawable.markword, R.drawable.andriod};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        System.out.println("density: " + displayMetrics.density + ", dpi: " + displayMetrics.densityDpi);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.markword, options);
        System.out.println("Bitmap size: " + bitmap.getAllocationByteCount() + " w: " + bitmap.getWidth() + ", h: " + bitmap.getHeight());

        mImageView = findViewById(R.id.image);
        mImageView.setImageBitmap(bitmap);
        reuseBitmap = bitmap;


        mButton = findViewById(R.id.btn_switch);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageView.setImageBitmap(getBitmap());
            }
        });


    }

    private Bitmap getBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2], options);
        if (BitmapUtils.canUseForInBitmap(reuseBitmap, options)) {
            options.inMutable = true;
            options.inBitmap = reuseBitmap;
        }

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex++ % 2], options);

    }
}
