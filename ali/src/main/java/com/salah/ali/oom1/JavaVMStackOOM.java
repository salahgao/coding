package com.salah.ali.oom1;

/**
 * @author salahgao
 * @date 2020/1/16
 */
public class JavaVMStackOOM {

    public static void main(String[] args) {
        while (true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
    }
}
