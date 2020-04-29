package com.practice.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CASDemo3 {
    // volatile关键字，保证不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
    // 禁止编译器优化，每次到内存中取值
    volatile static int count = 0;

    /**
     * 如果直接给方法加锁，最后的结果是没问题，但是会对性能影响很严重，因为有些代码是不需要同步的(sleep())
     * 所以只给有必要的代码加锁就行
     */
    // 模拟统计网站访问次数
    private static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         * count++ 的底层实现
         * A = count
         * B = count + 1
         * count = B
         *
         * 在多线程环境下，如果有两个线程同时执行 count++ ，那么它们执行结束后 count的值只增加了一次
         *
         *
         */
//        count++;
        while (!compareAndSwap(count, count + 1)) {
            System.out.println("不同步，不修改count");
        }
    }

    /**
     * @param expectCount 期望值
     * @param newCount    要给count赋予的新值
     * @return
     */
    private static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        // 判断count的值是否和期望的值一致？如果一致，就将新值赋给count
        if (getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;

        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                // 模拟每个用户发出10次请求
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }, "").start();

        }
        // 如何保证在所有线程执行结束后再执行后面的代码？ CountDownLatch
        countDownLatch.await();

        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime) + ", count：" + count);
    }
}
