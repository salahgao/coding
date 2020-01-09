package com.salah.ali;

public class SwapTest {

    private void swapTest(Integer i1, Integer i2) {
        Integer tem = i1;
        i1 = i2;
        i2 = tem;
    }

    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(2);
        new SwapTest().swapTest(i1, i2);
        System.out.println(i1 + ":" + i2);
    }

}