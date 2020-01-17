package com.salah.ali.oom1;

/**
 * VM Args：-XX:MaxMetaspaceSize=64m
 */
public class MetaspaceOOM {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
        }
    }
}