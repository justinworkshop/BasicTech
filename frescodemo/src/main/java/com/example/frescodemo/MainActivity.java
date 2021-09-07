package com.example.frescodemo;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView draweeView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uri = Uri.parse("https://nowpic.gtimg.com/hy_personal/4376ae1e0cf0ccceee3cbb34a3e245b733c748b746f274c75a3527a360656f82e938639156cc52b92ddf28c4a25f8bd2/");
        draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);

        resize();

    }

    @Override
    protected void onResume() {
        super.onResume();
        doShowMoveGuide(draweeView);
    }

    private void doShowMoveGuide(View parent) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_user_guide_view, null);
        final PopupWindow popWnd = new PopupWindow(parent.getContext());
        popWnd.setContentView(contentView);
        popWnd.setBackgroundDrawable(new BitmapDrawable());
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.showAtLocation(parent, Gravity.TOP, 0, 0);

        contentView.findViewById(R.id.tv_move_guide).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popWnd.dismiss();
            }
        });
    }
    private void notResize() {
        draweeView.setImageURI(uri);
        draweeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String drawee = "draweeView: w=" + draweeView.getWidth() + ", h=" + draweeView.getHeight();
                Drawable drawable = draweeView.getDrawable();
                Rect bounds = drawable.getBounds();
                int width = bounds.right - bounds.left;
                int height = bounds.bottom - bounds.top;

                String bitmap = "bitmap: " + width + ", " + height;
                Toast.makeText(MainActivity.this, drawee + ", " + bitmap, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resize() {
        int width = /*draweeView.getWidth()*/300;
        int height = /*draweeView.getHeight()*/300;
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();

        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(imageRequest)
                .build();

        draweeView.setController(controller);
    }


}
