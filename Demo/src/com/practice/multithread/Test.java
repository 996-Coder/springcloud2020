package com.practice.multithread;

/**
 * @author michael.zhang
 * @date 5/18/2020 8:46 AM
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        new Test().fun(0, 3);
    }

    public void fun(int left, int right) {
        System.out.println("left:" + left + "right:" + right);
        if (left < right) {
            fun(left, right - 1);
            fun(left + 1, right);
        }
    }
}
