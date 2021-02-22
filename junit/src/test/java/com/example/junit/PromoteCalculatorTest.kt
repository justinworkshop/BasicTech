package com.example.junit

import org.hamcrest.CoreMatchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by zhengwei on 2021/2/21.
 */
class PromoteCalculatorTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun plus() {
        val mock = Mockito.mock(PromoteCalculator::class.java )
        val value = mock.plus(1, 2)
        println("value1: $value")
        Mockito.doCallRealMethod().`when`(mock).plus(Mockito.anyInt(), Mockito.anyInt())
        Assert.assertThat(value, CoreMatchers.`is`(3))
        println("value2: $value")
        Mockito.verify(mock).plus(1, 2)

    }
}