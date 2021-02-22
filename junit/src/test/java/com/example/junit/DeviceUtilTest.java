package com.example.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by zhengwei on 2021/2/22.
 */
public class DeviceUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void plus() {
        DeviceUtil deviceUtil = Mockito.spy(DeviceUtil.class);
        deviceUtil.plus(1,3);
        Mockito.verify(deviceUtil).plus(1, 3);
        Mockito.verify(deviceUtil, Mockito.times(1)).plus(Mockito.anyInt(), Mockito.anyInt());
//        Mockito.verify(deviceUtil, Mockito.times(1)).plus(1, Mockito.anyInt());
        Mockito.verify(deviceUtil, Mockito.times(1)).plus(Mockito.eq(1), Mockito.anyInt());
        int eq = Mockito.eq(1);
        System.out.println("eq: " + eq);
    }

    @Test
    public void getString() {
        DeviceUtil deviceUtil = Mockito.spy(DeviceUtil.class);

        String string = deviceUtil.getString();
        System.out.println("getString: " + string);
    }
}