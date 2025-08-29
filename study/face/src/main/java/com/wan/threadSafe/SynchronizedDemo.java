package com.wan.threadSafe;

import org.w3c.dom.css.Counter;

/**
 * synchronized是jvm提供的锁，
 * 加锁实际上是修改对象实例的元数据中的锁状态
 * 偏向锁
 * 轻量级锁 自旋锁 无锁
 * 重量级锁 交给操作系统 
 * 锁升级：
 * 不是依次升级，偏向锁会因为竞争过于激烈直接上重量级，一个对象也会因为创建太晚创建时就带偏向锁
 * CAS操作：
 */
public class SynchronizedDemo {
    private int count = 0;

    // 1.修饰实例方法，锁的是当前对象的实例
    // 锁的是 this（当前实例）
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    // 2.修饰静态方法，锁的是对象
    private static int totalCount = 0;

    // 锁的是 Counter.class
    public static synchronized void globalIncrement() {
        totalCount++;
    }

    // 3.锁任意对象
    public static void globalOp() {
        synchronized (SynchronizedDemo.class) {
            totalCount++;
        }
    }

}
