package com.salah.ali.proxy2;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//代理对象
public class DemoMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before in cglib");
        Object result = null;
        try {
            result = proxy.invokeSuper(obj, args);
        } catch (Exception e) {
            System.out.println("get ex:" + e.getMessage());
            throw e;
        } finally {
            System.out.println("after in cglib");
        }
        return result;
    }
}