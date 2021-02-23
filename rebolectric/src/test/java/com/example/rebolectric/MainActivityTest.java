package com.example.rebolectric;

import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import org.apache.tools.ant.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by zhengwei on 2021/2/23.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {VERSION_CODES.O_MR1})
public class MainActivityTest {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void testJump() {
        final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        final MainActivity mainActivity = controller.get();
        controller.create();
        controller.resume();
        mainActivity.findViewById(R.id.text).performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent intent = shadowActivity.getNextStartedActivity();
        //验证Intent数据传递正确性
        Assert.assertEquals(intent.getComponent().getClassName(), SplashActivity.class.getName());
        int intExtra = intent.getIntExtra("key", 0);
        Assert.assertEquals(intExtra, 111);
        System.out.println("intExtra: " + intExtra);
    }
}
