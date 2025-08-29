package com.wan.threadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁:可以多次加锁
 * AQS：AbstractQueuedSynchronizer，维护一个信号量state和一个双向链表，链表元素代表线程
 * 在可重入锁中，0为为无锁，加锁+1，放锁-1
 * 
 */
public class AQSDemo {
    // 这里是引用类型，只要不改引用本身，就不需要volatile修饰保证可见性
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("线程1开始");
            try {
                Thread.sleep(5000);
                System.out.println("线程1结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("线程2开始");
            while (lock.isLocked()) {

            }
            System.out.println("线程2结束");

        });
        thread1.start();
        thread2.start();

        ReentrantLock lockABC = new ReentrantLock();
        Condition conditionA = lockABC.newCondition();
        Condition conditionB = lockABC.newCondition();
        Condition conditionC = lockABC.newCondition();
        int[] flag = new int[]{1};
        // 使用锁控制线程执行顺序
        // await() 必须在 lock() 和 unlock() 之间调用，否则会抛出 IllegalMonitorStateException
        Thread threadA = new Thread(() -> {
            lockABC.lock();
            try {
                // 使用while防止虚假唤醒
                while (flag[0] != 1) {
                    conditionA.await();
                }
                System.out.println("线程a开始");
                Thread.sleep(1500);
                System.out.println("线程a结束");
                flag[0] = 2;
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockABC.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            lockABC.lock();
            try {
                while (flag[0] != 2) {
                    conditionB.await();
                }
                System.out.println("线程b开始");
                Thread.sleep(1000);
                System.out.println("线程b结束");
                flag[0] = 3;
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockABC.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            lockABC.lock();
            
            try {
                while (flag[0] != 3) {
                    conditionC.await();
                }
                System.out.println("线程c开始");
                Thread.sleep(1000);
                System.out.println("线程c结束");
                // flag[0] = 1;
                // conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockABC.unlock();
            }
        });
        threadC.start();
        threadA.start();
        threadB.start();

        // 同时执行
        // 使用countdown
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    countDownLatch.await();
                    System.out.println(System.currentTimeMillis());
                System.out.println("线程" + finalI + "被执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        // countDown用于将计数减一
        countDownLatch.countDown();
    }


}
