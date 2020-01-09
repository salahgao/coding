package com.salah.ali.proxy;

public class RealSubject implements Subject {

    public void request() {
        System.out.println("real subject execute request");
    }

    public void hello() {
        System.out.println("hello");
    }
}