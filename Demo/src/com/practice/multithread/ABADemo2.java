package com.practice.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo2 {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            // 获得初始的版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第1次获取到的值: " + atomicStampedReference.getReference() + ", 第1次获取到的版本号: " + stamp);
            try {
                // 线程t1暂停1秒，保证线程t2读取到atomicStampedReference的初始值和初始版本号
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "第2次获取到的值: " + atomicStampedReference.getReference() + ", 第2次获取到的版本号: " + atomicStampedReference.getStamp());
            // 先将value改为2，再改回为1
            boolean result1 = atomicStampedReference.compareAndSet(1, 2, 1, stamp + 1);
            System.out.println("t1第1次修改成功： " + result1 + ",当前值(修改后): " + atomicStampedReference.getReference() + " 当前版本号(修改后)： " + atomicStampedReference.getStamp());
            boolean result2 = atomicStampedReference.compareAndSet(2, 1, 2, atomicStampedReference.getStamp() + 1);
            System.out.println("t1第2次修改成功： " + result2 + ",当前值(修改后): " + atomicStampedReference.getReference() + " 当前版本号(修改后)： " + atomicStampedReference.getStamp());
        }, "t1").start();

        new Thread(() -> {
            // 获得初始的版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第1次获取到的值: " + atomicStampedReference.getReference() + ", 第1次获取到的版本号: " + stamp);
            try {
                // 线程t2暂停2秒，保证线程t1完成ABA操作
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "第2次获取到的值: " + atomicStampedReference.getReference() + ", 第2次获取到的版本号: " + atomicStampedReference.getStamp());
            boolean result = atomicStampedReference.compareAndSet(1, 2020, 1, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "第1次修改成功： " + result + " 当前版本号(修改后)： " + atomicStampedReference.getStamp());

        }, "t2").start();
    }
}
