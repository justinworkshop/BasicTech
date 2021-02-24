package com.example.robolectric;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import androidx.lifecycle.Lifecycle.State;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ActivityScenario.ActivityAction;
import androidx.test.core.app.ApplicationProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by zhengwei on 2021/2/23.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {VERSION_CODES.O_MR1}, application = CustomApplication.class)
public class MainActivityTest {
    private Context context;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testJump() {
        final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        final MainActivity mainActivity = controller.get();
        controller.create();
        controller.resume();
        mainActivity.findViewById(R.id.text).performClick();

        //获取对应的Shadow类
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent intent = shadowActivity.getNextStartedActivity();
        //验证Intent的正确性
        Assert.assertEquals(intent.getComponent().getClassName(), SplashActivity.class.getName());
        //验证Intent数据传递
        int intExtra = intent.getIntExtra("key", 0);
        Assert.assertEquals(intExtra, 111);
        System.out.println("intExtra: " + intExtra);
    }

    /**
     * 验证Activity跳转，是否触发了intent
     */
    @Test
    public void testClick() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(new ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                //触发跳转
                activity.findViewById(R.id.text).performClick();

                //验证
                Intent expectedIntent = new Intent(activity, SplashActivity.class);
                Intent actualIntent = Shadows.shadowOf(activity).getNextStartedActivity();
                System.out.println("expectIntent: " + expectedIntent.getComponent() + ", actualIntent: " + actualIntent.getComponent());
                Assert.assertThat(expectedIntent.getComponent(), Matchers.is(actualIntent.getComponent()));
            }
        });
    }

    @Test
    public void testGetAppName() {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        System.out.println("appName: " + appName);
        Assert.assertThat(appName, Matchers.is("robolectric"));
    }

    @Test
    public void testLifeCycle() {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity mainActivity = controller.get();
        State currentState = mainActivity.getLifecycle().getCurrentState();
        System.out.println("state 1: " + currentState);
        Assert.assertThat(currentState, Matchers.is(State.INITIALIZED));

        controller.create();
        currentState = mainActivity.getLifecycle().getCurrentState();
        System.out.println("currentState: " + currentState);
        Assert.assertThat(currentState, Matchers.is(State.CREATED));

        controller.resume();
        currentState = mainActivity.getLifecycle().getCurrentState();
        System.out.println("currentState: " + currentState);
        Assert.assertThat(currentState, Matchers.is(State.RESUMED));
    }

    @Test
    public void testStartActivity() {
        Intent intent = new Intent();
        intent.putExtra("key", 123);
        Activity activity = Robolectric.buildActivity(MainActivity.class).newIntent(intent).create().get();
        Assert.assertEquals(123, activity.getIntent().getExtras().getInt("key"));
    }

    @Test
    public void testBroadcastReceiver() {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();

        String action = "com.robolectric.Test";
        Intent intent = new Intent(action);

        Assert.assertTrue(shadowApplication.hasReceiverForIntent(intent));
    }

    @Test
    public void testReceive() {
        String action = "com.robolectric.Test";
        Intent intent = new Intent(action);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        myBroadcastReceiver.onReceive(RuntimeEnvironment.application, intent);

        System.out.println("flag: " + myBroadcastReceiver.flag);
        Assert.assertThat(myBroadcastReceiver.flag, Matchers.is(1));
    }
}
