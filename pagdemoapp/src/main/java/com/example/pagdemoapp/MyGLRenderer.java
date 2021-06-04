package com.example.pagdemoapp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import org.libpag.PAGFile;
import org.libpag.PAGPlayer;
import org.libpag.PAGSurface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FRAMEBUFFER;
import static android.opengl.GLES20.GL_FRAMEBUFFER_BINDING;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private static final String VERTEX_MAIN =
        "attribute vec2  vPosition;\n" +
            "attribute vec2  vTexCoord;\n" +
            "varying vec2    texCoord;\n" +
            "\n" +
            "void main() {\n" +
            "    texCoord = vTexCoord;\n" +
            "    gl_Position = vec4 ( vPosition.x, vPosition.y, 0.0, 1.0 );\n" +
            "}";
    private static final String FRAGMENT_MAIN =
        "precision mediump float;\n" +
            "\n" +
            "varying vec2                texCoord;\n" +
            "uniform sampler2D sTexture;\n" +
            "\n" +
            "void main() {\n" +
            "    gl_FragColor = texture2D(sTexture, texCoord);\n" +
            "}";

    static final float SQUARE_COORDS[] = {
        1.0f, -1.0f,
        -1.0f, -1.0f,
        1.0f, 1.0f,
        -1.0f, 1.0f,
    };
    static final float TEXTURE_COORDS[] = {
        1f, 1f,
        0f, 1f,
        1f, 0f,
        0f, 0f
    };
    static FloatBuffer VERTEX_BUF, TEXTURE_COORD_BUF;

    private static final String TAG = "MyGLRenderer";
    private int mProgram;

    // PAG将要绘制到的纹理
    int mPagTextureId;

    Context mContext;
    PAGPlayer mPAGPlayer;
    long currentFrame;

    public MyGLRenderer(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        currentFrame = 0;
    }

    private void initPAG(int width, int height) {
        if (mPagTextureId > 0) {
            return;
        }
        int id[] = {0};
        GLES20.glGenTextures(1, id, 0);
        if (id[0] == 0) {
            return;
        }
        mPagTextureId = id[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mPagTextureId);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        mPAGPlayer = new PAGPlayer();
        mPAGPlayer.setComposition(PAGFile.Load(mContext.getAssets(), "vlog.pag"));

        // 调用FromTexture获取Surface，mPagTextureId是PAG将要绘制到的目标纹理
        mPAGPlayer.setSurface(PAGSurface.FromTexture(mPagTextureId, width, height));
    }

    private void initShader() {
        if (mPagTextureId == 0) {
            return;
        }
        if (VERTEX_BUF == null) {
            VERTEX_BUF = ByteBuffer.allocateDirect(SQUARE_COORDS.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
            VERTEX_BUF.put(SQUARE_COORDS);
            VERTEX_BUF.position(0);
        }
        if (TEXTURE_COORD_BUF == null) {
            TEXTURE_COORD_BUF = ByteBuffer.allocateDirect(TEXTURE_COORDS.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
            TEXTURE_COORD_BUF.put(TEXTURE_COORDS);
            TEXTURE_COORD_BUF.position(0);
        }
        mProgram = GLUtil.buildProgram(VERTEX_MAIN, FRAGMENT_MAIN);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        if (mPagTextureId == 0) {
            return;
        }
        int drawFboId[] = {0};
        GLES20.glGetIntegerv(GL_FRAMEBUFFER_BINDING, drawFboId, 0);


        float progress = ((currentFrame / 60.f) % (mPAGPlayer.getComposition().duration() / 1_000_000)) / (mPAGPlayer.getComposition().duration() / 1_000_000);
        mPAGPlayer.setProgress(progress);
        mPAGPlayer.flush();


        GLES20.glBindFramebuffer(GL_FRAMEBUFFER, drawFboId[0]);
        GLES20.glUseProgram(mProgram);
        int vPositionLocation = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(vPositionLocation);
        GLES20.glVertexAttribPointer(vPositionLocation, 2, GLES20.GL_FLOAT, false, 4 * 2, VERTEX_BUF);
        int vTexCoordLocation = GLES20.glGetAttribLocation(mProgram, "vTexCoord");
        GLES20.glEnableVertexAttribArray(vTexCoordLocation);
        GLES20.glVertexAttribPointer(vTexCoordLocation, 2, GLES20.GL_FLOAT, false, 4 * 2, TEXTURE_COORD_BUF);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mPagTextureId);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        currentFrame++;
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        currentFrame = 0;
        initPAG(width, height);
        initShader();
    }

}