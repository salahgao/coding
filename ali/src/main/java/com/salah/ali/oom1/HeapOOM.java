package com.salah.ali.oom1;

/**
 * 1.8.0_181
 * java -Xmx12m OOM
 * <p>
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at com.salah.ali.oom.HeapOOM.main(OOM.java:11)
 */
public class HeapOOM {

    static final int SIZE = 2 * 1024 * 1024;

    public static void main(String[] a) {
        int[] i = new int[SIZE];
    }
}