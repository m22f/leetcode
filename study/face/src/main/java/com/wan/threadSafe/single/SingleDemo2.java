package com.wan.threadSafe.single;

/**
 * 只有“懒加载 + 方法参数”的方式（如双重检查锁）能支持传参
 * 懒加载方式（懒汉式、静态内部类式）可以“延迟”构造器重的影响
 */
public class SingleDemo2 {
    private static SingleDemo2 singleDemo2;

    private SingleDemo2() {};

    /**
     * 线程不安全
     * @return
     */
    public static SingleDemo2 getInstance() {
        if (singleDemo2 == null) {
            singleDemo2 = new SingleDemo2();
        }
        return singleDemo2;
    }

    /**
     * 双重检查保证线程安全DCL
     * @return
     */
    public static SingleDemo2 getInstanceDCL() {

        if (singleDemo2 == null) {
            synchronized (SingleDemo2.class) {
                if (singleDemo2 == null) {
                    singleDemo2 = new SingleDemo2();
                }
            }
        }

        return singleDemo2;
    }

}
