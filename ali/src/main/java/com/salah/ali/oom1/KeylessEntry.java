package com.salah.ali.oom1;

import java.util.HashMap;
import java.util.Map;

/**
 * -Xmx12m
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * at java.lang.StringBuilder.toString(StringBuilder.java:407)
 * at com.salah.ali.oom.KeylessEntry.main(KeylessEntry.java:38)
 * <p>
 * -Xmx512m
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.HashMap.resize(HashMap.java:704)
 * at java.util.HashMap.putVal(HashMap.java:663)
 * at java.util.HashMap.put(HashMap.java:612)
 * at com.salah.ali.oom.KeylessEntry.main(KeylessEntry.java:29)
 */
public class KeylessEntry {

    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        while (true)
            for (int i = 0; i < 100000; i++)
                if (!m.containsKey(new Key(i)))
                    m.put(new Key(i), "Number:" + i);
    }
}