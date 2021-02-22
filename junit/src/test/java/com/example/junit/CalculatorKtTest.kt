package com.example.junit

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by zhengwei on 2021/2/21.
 */
class CalculatorKtTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testAdd() {
        val value = add(1, 2)
        Assert.assertTrue(value == 3)
        Assert.assertThat(value, equalTo(3))
        Assert.assertThat(value, CoreMatchers.`is`(3))

    }

    @Test
    fun testSub(){
        val value = sub(3,1)
        Assert.assertThat(value, `is`(2))
    }

    @Test
    fun testMul(){
        val value = mul(5, 6)
        Assert.assertThat(value, `is`(30))
    }
    @Test
    fun testDiv(){
        val value = div(8, 2)
        Assert.assertThat(value, `is`(4))
    }

}
