package com.example.pagdemoapp;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import org.libpag.PAGComposition;
import org.libpag.PAGFile;
import org.libpag.PAGImage;
import org.libpag.PAGText;
import org.libpag.PAGView;
import org.libpag.PAGView.PAGViewListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PAG";

    private PAGView cannonPAGView;
    private PAGView bulletPAGView1;
    private PAGView bulletPAGView2;
    private PAGView bulletPAGView3;
    private ImageView cannonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        cannonImage = findViewById(R.id.cannon_default);
        cannonPAGView = findViewById(R.id.pag_view);

        bulletPAGView1 = findViewById(R.id.bullet_1);
        bulletPAGView2 = findViewById(R.id.bullet_2);
        bulletPAGView3 = findViewById(R.id.bullet_3);

        cannonImage.setOnClickListener(this);
        cannonPAGView.setOnClickListener(this);

        cannonPAGView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                Log.d(TAG, ">>>> onSurfaceTextureAvailable cannon width: " + width + ", height: " + height);
                initCannon(width, height);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
                Log.d(TAG, ">>>> onSurfaceTextureSizeChanged ");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                Log.d(TAG, ">>>> onSurfaceTextureDestroyed ");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                Log.d(TAG, ">>>> onSurfaceTextureUpdated ");
            }
        });

        bulletPAGView1.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {

            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                Log.d(TAG, ">>>> onSurfaceTextureAvailable bullet width: " + width + ", height: " + height);
                initBullet(width, height);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cannon_default) {
            Log.d(TAG, ">>>> click ");
            handleCannonClick();
            playBullet();
        }
    }

    private int flag = 1;

    private void playBullet() {

        if (flag == 1) {
            bulletPAGView1.play();
        }

        if (flag == 2) {
            bulletPAGView2.play();
        }

        if (flag == 3) {
            bulletPAGView3.play();
        }

        flag++;
        if (flag > 3) {
            flag = 1;
        }
    }

    private void handleCannonClick() {
        cannonPAGView.play();
    }

    void initCannon(int width, int height) {
        Log.d(TAG, "initCannon -----> ");
        PAGFile cannonPAGFile = PAGFile.Load(getAssets(), "cannon_blue.pag");
        cannonPAGFile.setDuration(0);

        PAGComposition composition = PAGComposition.Make(width, height);
        composition.addLayer(cannonPAGFile);
//
        cannonPAGView.setComposition(composition);
        cannonPAGView.setRepeatCount(1);
        cannonPAGView.addListener(new PAGViewListener() {
            @Override
            public void onAnimationStart(PAGView pagView) {
                Log.d(TAG, ">>>> onAnimationStart ");
                cannonPAGView.setProgress(0);
            }

            @Override
            public void onAnimationEnd(PAGView pagView) {
                Log.d(TAG, ">>>> onAnimationEnd ");
            }

            @Override
            public void onAnimationCancel(PAGView pagView) {
                Log.d(TAG, ">>>> onAnimationCancel ");
            }

            @Override
            public void onAnimationRepeat(PAGView pagView) {
                Log.d(TAG, ">>>> onAnimationRepeat ");
            }
        });
    }

    void initBullet(int width, int height) {
        PAGComposition composition1 = PAGComposition.Make(width, height);
        PAGFile pagFile1 = PAGFile.Load(getAssets(), "bullet_level_1.pag");
        pagFile1.setDuration(1);

        PAGFile pagFile2 = PAGFile.Load(getAssets(), "bullet_level_2.pag");
        pagFile2.setDuration(1);

        PAGFile pagFile3 = PAGFile.Load(getAssets(), "bullet_level_3.pag");
        pagFile3.setDuration(1);

        composition1.addLayer(pagFile1);

        PAGComposition composition2 = PAGComposition.Make(width, height);
        composition2.addLayer(pagFile2);

        PAGComposition composition3 = PAGComposition.Make(width, height);
        composition3.addLayer(pagFile3);

        bulletPAGView1.setRepeatCount(1);
        bulletPAGView1.setComposition(composition1);

        bulletPAGView2.setRepeatCount(1);
        bulletPAGView2.setComposition(composition2);

        bulletPAGView3.setRepeatCount(1);
        bulletPAGView3.setComposition(composition3);

        bulletPAGView1.addListener(new PAGViewListener() {
            @Override
            public void onAnimationStart(PAGView pagView) {
                Log.d(TAG, ">>>> bullet onAnimationStart ");
                bulletPAGView1.setProgress(0);
            }

            @Override
            public void onAnimationEnd(PAGView pagView) {
                Log.d(TAG, ">>>> bullet onAnimationEnd ");
            }

            @Override
            public void onAnimationCancel(PAGView pagView) {
                Log.d(TAG, ">>>> bullet onAnimationCancel ");
            }

            @Override
            public void onAnimationRepeat(PAGView pagView) {
                Log.d(TAG, ">>>> bullet onAnimationRepeat ");
            }
        });

        bulletPAGView2.addListener(new PAGViewListener() {
            @Override
            public void onAnimationStart(PAGView pagView) {
                bulletPAGView2.setProgress(0);
            }

            @Override
            public void onAnimationEnd(PAGView pagView) {

            }

            @Override
            public void onAnimationCancel(PAGView pagView) {

            }

            @Override
            public void onAnimationRepeat(PAGView pagView) {

            }
        });

        bulletPAGView3.addListener(new PAGViewListener() {
            @Override
            public void onAnimationStart(PAGView pagView) {
                bulletPAGView3.setProgress(0);
            }

            @Override
            public void onAnimationEnd(PAGView pagView) {

            }

            @Override
            public void onAnimationCancel(PAGView pagView) {

            }

            @Override
            public void onAnimationRepeat(PAGView pagView) {

            }
        });

//        bulletPAGView2.setRepeatCount(1);
//        bulletPAGView2.setComposition(composition);
//
//        bulletPAGView3.setRepeatCount(1);
//        bulletPAGView3.setComposition(composition);
    }

    /**
     * Test replace image.
     */
    void testReplaceImage(PAGFile pagFile) {
        if (pagFile == null || pagFile.numImages() <= 0) {
            return;
        }
        pagFile.replaceImage(0, PAGImage.FromAssets(getAssets(), "test.png"));
    }

    /**
     * Test edit text.
     */
    void testEditText(PAGFile pagFile) {
        if (pagFile == null || pagFile.numTexts() <= 0) {
            return;
        }
        PAGText textData = pagFile.getTextData(0);
        textData.text = "haha哈哈大😆";
        textData.fontSize = 13;
        pagFile.replaceText(0, textData);
    }
}
