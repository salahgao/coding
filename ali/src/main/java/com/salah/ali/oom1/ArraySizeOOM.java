package com.salah.ali.oom1;

/**
 * @author gaofeirong
 * @date 2020/1/16
 */
public class ArraySizeOOM {

    public static void main(String[] args) {
        for (int i = 10; i >= 0; i--) {
            try {
                int[] arr = new int[Integer.MAX_VALUE - i];
                System.out.format("Successfully initialized an array with %,d elements.\n", Integer.MAX_VALUE - i);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
