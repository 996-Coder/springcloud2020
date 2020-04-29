package com.practice.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CASDemo {
    private static int count = 0;

    // 模拟统计网站访问次数
    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);

        /**
         * count++ 的底层实现
         * A = count
         * B = count + 1
         * count = B
         *
         * 在多线程环境下，如果有两个线程同时执行 count++ ，那么它们执行结束后 count的值只增加了一次
         */
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;

        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        // 模拟有100个用户
        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                try {
                    // 模拟每个用户发出10次请求
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        // 如何保证在所有线程执行结束后再执行后面的代码？ CountDownLatch
        countDownLatch.await();

        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime) + ", count：" + count);
    }
}
