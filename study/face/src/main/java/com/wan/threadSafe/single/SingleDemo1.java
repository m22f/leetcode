package com.wan.threadSafe.single;

// new对象的过程:  1. 类加载: 类在首次被使用时，会通过类加载器将.class文件加载到内存，类的字段，方法，构造器会被存入方法区
//                   链接: 验证:确保字节码合法
//                   准备: 为静态变量分配内存，并设置初始值
//                   初始化: 执行静态初始化代码
//                2. 内存分配: 在new一个对象时,jvm会先在堆内存中分配一个空间包含 实例字段(自身参数)
//                                                                           对象头(标记字(哈希值,gc年龄,锁状态,线程持有信息),类指针) 
//                                                                           对齐填充
//                3. 初始化：为字段设置默认值,然后调用构造器
//                4. 返回引用

// 注意实例变量和静态变量区别，静态变量不能用this,初始化时机也不同
/**
 * 单例模式: 加载时构造自身对象，并私有化构造函数，实现一个方法返回对象
 * 饿汉式，在类的初始化阶段直接创建对象，而非实际获取时。
 */
public class SingleDemo1 {
    private static SingleDemo1 singleDemo1= new SingleDemo1();
    private SingleDemo1(){}

    public static SingleDemo1 getInstance(){
        return singleDemo1;
    }
}
