package com.example.rebolectric;

import android.content.Context;
import android.content.Intent;
import java.util.Random;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by zhengwei on 2021/2/23.
 */
@RunWith(RobolectricTestRunner.class)
public class MySharePreferenceTest {
    MySharePreference sharePreference;

    @Before
    public void setUp() throws Exception {
        String  name = new Random().nextInt(1000) + ".pref";
        sharePreference = new MySharePreference(RuntimeEnvironment.application.getSharedPreferences(name,
                Context.MODE_PRIVATE));
        ShadowLog.stream = System.out;
    }

    @Test
    public void testPluAndGet() {
       sharePreference.put("key01", "stringA");
        String value = sharePreference.get("key01");
        Assert.assertEquals(value, "stringA");
    }

    @After
    public void tearDown() throws Exception {
    }
}