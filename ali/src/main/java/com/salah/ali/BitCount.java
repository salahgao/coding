package com.salah.ali;

import java.util.Random;

/**
 * 统计整数二进制1的个数
 * <p>
 * 1 转化为二进制数1的个数
 * 2 判断二进制位是否是否为1计数
 *
 * @author gaofeirong
 * @date 2019/12/3
 */
public class BitCount {

    public static void main(String[] args) {

//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(-2));

        int randInt = new Random().nextInt();
        int count = 0;

        for (int i = 0; i < 32; i++) {
            // 注意 ((randInt >>> i) & 1) 需要括号都抱起来
            count = count + ((randInt >>> i) & 1);
        }

        System.out.println(randInt);
        System.out.println(Integer.toBinaryString(randInt));
        System.out.println(Integer.bitCount(randInt));
        System.out.println(count);

    }
}
