package com.example.annotation.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2016-2020
 * FileName: DynamicProxyTest
 * Author: zhengwei
 * Date: 2020-05-21 22:04
 * Description: 动态代理
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        IHello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//        IHello proxyInstance = (IHello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);

        IHello proxyInstance = (IHello) dynamicProxy.bind();
        proxyInstance.sayHello();
    }

    interface IHello {
        void sayHello();
    }

    static class HelloImpl implements IHello {

        @Override
        public void sayHello() {
            System.out.println("Hello dynamic proxy");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originalObject;

        public DynamicProxy(Object originalObject) {
            this.originalObject = originalObject;
        }

        public Object bind(){
            return Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), originalObject.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Apply Dynamic proxy!!!");
            return method.invoke(originalObject, args);
        }
    }
}
