package com.wan.threadSafe;

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

}
