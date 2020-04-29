package com.practice.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ABADemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(1);

    public static void main(String[] args) {
        new Thread(() -> {
            // 先将value改为2，再改回为1
            atomicReference.compareAndSet(1, 2);
            atomicReference.compareAndSet(2, 1);
        }, "t1").start();

        // 事实上这里线程t2一开始获取到的atomicReference的值就是线程t1改动过后的值，所以并没有真正的模拟出ABA过程！
        new Thread(() -> {
            try {
                // 线程t2休眠一秒，保证线程t1完成ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(1, 2020) + "\t" + atomicReference.get());
        }, "t2").start();
    }
}
