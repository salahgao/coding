package com.salah.ali.proxy2;

import net.sf.cglib.proxy.Enhancer;

//客户端
public class Client {
    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        // 此刻，realSubject不是单纯的目标类，而是增强过的目标类  
        RealSubject realSubject = (RealSubject) enhancer.create();
        realSubject.hello();
        realSubject.request();
    }
}