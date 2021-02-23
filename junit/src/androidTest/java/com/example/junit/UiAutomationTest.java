package com.example.junit;

import static android.os.SystemClock.sleep;

import android.app.Instrumentation;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import org.junit.Test;

/**
 * Created by zhengwei on 2021/2/23.
 */
public class UiAutomationTest {

    @Test
    public void testUI() throws UiObjectNotFoundException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiDevice uiDevice = UiDevice.getInstance(instrumentation);
//        uiDevice.pressHome();
        UiObject browserObject = new UiObject(new UiSelector().descriptionContains("登 录"));
        browserObject.clickAndWaitForNewWindow();
//        UiObject editObject = new UiObject(new UiSelector().descriptionContains(""));
//        editObject.click();
//        uiDevice.pressDelete();
//        editObject.setText("www.baidu.com");
//        uiDevice.pressEnter();

        sleep(2000);
    }

}
