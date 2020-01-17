package com.salah.ali.oom1;

import java.util.Map;
import java.util.Random;

/**
 * 1.8.0_181
 * <p>
 * -Xmx10m -XX:+UseParallelGC
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.Hashtable.rehash(Hashtable.java:402)
 * at java.util.Hashtable.addEntry(Hashtable.java:426)
 * at java.util.Hashtable.put(Hashtable.java:477)
 * at com.salah.ali.oom.Wrapper.main(Wrapper.java:19)
 * <p>
 * -Xmx100m -XX:+UseParallelGC
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * at java.lang.Integer.valueOf(Integer.java:832)
 * at com.salah.ali.oom.Wrapper.main(Wrapper.java:11)
 * <p>
 * <p>
 * -Xmx100m -XX:+UseConcMarkSweepGC
 * -Xmx100m -XX:+UseG1GC
 * Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
 */
public class Wrapper {
    public static void main(String args[]) throws Exception {
        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }
}