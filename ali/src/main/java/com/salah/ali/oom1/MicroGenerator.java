package com.salah.ali.oom1;

import javassist.ClassPool;

/**
 * 1.7.0_80
 * -XX:MaxPermSize=5m
 * Exception in thread "main"
 * Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
 *
 */
public class MicroGenerator {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000000; i++) {
            generate("cn.moondev.User" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}