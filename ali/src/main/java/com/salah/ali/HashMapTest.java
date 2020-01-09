package com.salah.ali;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap 三种遍历方式
 * @author gaofeirong
 * @date 2019/10/31
 */
public class HashMapTest {

    public void test1() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
        }
    }

    public void test2() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
        }
    }

    public void test3() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (String key : hashMap.keySet()) {
            String value = hashMap.get(key);
        }
    }

}
