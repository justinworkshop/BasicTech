package com.example.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by zhengwei on 2021/2/23.
 */
@RunWith(MockitoJUnitRunner.class)
public class LifeCycleTest {

    @BeforeClass
    public static void start() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void end() {
        System.out.println("AfterClass");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @Test
    public void test_1() {
        System.out.println("test_1");
    }

    @Test
    public void test_2() {
        System.out.println("test_2");
    }
}
