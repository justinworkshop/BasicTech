package com.example.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Created by zhengwei on 2021/2/21.
 */
public class DateUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() {
    }

    @Test
    public void getDate() {
        DateUtil dateUtilMock = Mockito.mock(DateUtil.class);
        dateUtilMock.getDate();
        dateUtilMock.getMonth();
        int a = dateUtilMock.getValue();
        System.out.println("a=" + a);

        Mockito.doCallRealMethod().when(dateUtilMock).getMonth();
        dateUtilMock.getMonth();

        Mockito.doCallRealMethod().when(dateUtilMock).getValue();
        int b = dateUtilMock.getValue();
        System.out.println("b=" + b);
    }

    @Test
    public void getYear() {
        DateUtil dateUtilSpy = Mockito.spy(DateUtil.class);
        dateUtilSpy.getDate();
        dateUtilSpy.getMonth();
        int a = dateUtilSpy.getValue();
        System.out.println("a=" + a);

        Mockito.doCallRealMethod().when(dateUtilSpy).getMonth();
        dateUtilSpy.getMonth();

        Mockito.doCallRealMethod().when(dateUtilSpy).getValue();
        int b = dateUtilSpy.getValue();
        System.out.println("b=" + b);

        Mockito.doNothing().when(dateUtilSpy).getMonth();
        dateUtilSpy.getMonth();

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(dateUtilSpy).getValue();
        System.out.println("c=" + dateUtilSpy.getValue());
    }

    @Test
    public void getMonth() {
    }

    @Test
    public void getValue() {
    }
}