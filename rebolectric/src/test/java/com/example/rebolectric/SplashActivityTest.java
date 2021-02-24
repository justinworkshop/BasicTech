package com.example.rebolectric;

import static org.junit.Assert.*;

import android.os.Build.VERSION_CODES;
import android.widget.Toast;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

/**
 * Created by zhengwei on 2021/2/24.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {VERSION_CODES.O_MR1})
public class SplashActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToast() {
        ActivityController<SplashActivity> controller = Robolectric.buildActivity(SplashActivity.class).setup();
        SplashActivity splashActivity = controller.get();
        Toast toast = ShadowToast.getLatestToast();
        Assert.assertNull(toast);

        splashActivity.findViewById(R.id.splash_text).performClick();
        toast = ShadowToast.getLatestToast();
        Assert.assertNotNull(toast);
        Assert.assertThat(toast.getDuration(), Matchers.is(Toast.LENGTH_SHORT));
        Assert.assertThat(ShadowToast.getTextOfLatestToast(), Matchers.is("SplashText"));

    }
}