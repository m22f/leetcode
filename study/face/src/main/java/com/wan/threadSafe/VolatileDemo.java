package com.wan.threadSafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程与进程：线程隶属于进程，进程是操作系统分配资源的最小单元，线程是操作系统分配任务的最小单元，主要是cpu是被分配到线程
 * 堆和方法区为进程内的线程共享，每个线程有自己的程序计数器pc和栈。
 * 堆：     存对象的实例，数组。gc主要负责的地方，新生代，老年代，垃圾回收算法相关
 * 方法区： 存类的结构信息，类名、字段、方法、构造器。 字节码，常量池，静态变量
 *          在jdk8前称为永久代，在堆中？
 *          在jdk8后称为元空间，使用本地内存，不在堆中
 *          也会发生垃圾回收，卸载类，回收常量
 * 栈：     栈包含栈帧，对应方法调用，包含
 *          局部变量表：方法参数，局部变量(基本类型，对象引用)
 */
public class VolatileDemo {
    // 创建线程的方法
    // 1. 继承Thread类，重写run()方法。
    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("通过继承Thread类开启线程");
        }
    }

    // 2. 实现runnable接口
    public static class MyThread2 implements Runnable {

        @Override
        public void run() {
            System.out.println("通过实现runnable接口开启线程");;
        }
    }

    // 3. 实现callable接口,上面两种方法没有返回值，
    public static class MyThread3 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("通过实现callable接口开启线程的打印语句");
            return "通过实现callable接口开启线程的返回值";
        }
    }


    public static volatile boolean flag = true;

    public static void main(String[] args) {
        // 线程的使用,对于没有返回值的线程对象，
        // 对于直接继承Thread类的子类，直接运行其start()即可
        // 为什是start方法而不是run方法？因为直接调用run方法本质上是在主线程里运行方法，而start方法会先创建一条线程，然后在那个线程里运行run方法
        MyThread1 thread1 = new MyThread1();
        thread1.start();
        thread1.run();


        // 而对于通过实现接口的类，不能直接运行其start()方法，因为根本没有。
        // 要通过新创建Thread对象，将实现了runnable的类作为Thread构造方法的参数，执行该Thread对象的start方法。
        // 只实现callable的对象不能作为其参数，因为Thread 构造函数接受的是 Runnable 接口参数，可以是任何一个实现了 Runnable 接口的对象
        // 此外，实现继承了Runnable接口的接口的类也可以作为Thread()参数，但是Callable和Runnable之间无继承关系
        MyThread2 thread2 = new MyThread2();
        // thread2.start(); // 编译不通过
        new Thread(thread2).start();

        // 对于有返回值的线程对象，要创建异步任务等待执行完成，获取返回结果
        // 异步任务
        // FutureTask类实现了RunnableFuture接口，而该接口继承了Runnable, Future<V>，所以可作为Thread构造方法的参数。
        // 构造方法FutureTask<>()接受 Callable 接口参数。
        // FutureTask在run()运行 构造方法中传入的实现Callable接口的类的 call()方法，并保存返回值，通过get()方法传出
        // 以下代码简单粗暴理解 
        // Thread的start执行FutureTask的run方法
        // FutureTask的run方法执行MyThread3的call方法
        // 返回值由 FutureTask的get方法返回
        FutureTask<String> task = new FutureTask<>(new MyThread3());

        new Thread(task).start();
        try{
            String string = task.get();
            System.out.println(string);
        } catch(InterruptedException e){
            e.printStackTrace();
        } catch(ExecutionException e){
            e.printStackTrace();
        }

        //TODO 线程池

        // 线程可见性，原子性， 
        // Synchronized
        // public static volatile boolean flag = true;
        // volatile：用volatile只能修饰变量，会使得其线程可见，并且该变量的读写操作禁止指令重排
        flag = true;
        // 关于lambda表达式，编译器会将lambda表达式转化为一个类，并将捕获变量作为该类的构造函数的参数和字段, 将方法体作为函数式接口的方法实现。
        // 目标类型化（Target Typing）:  Lambda 表达式本身没有类型，它的类型是根据上下文“推断”出来的
        // 函数式接口   ： 一个接口是 函数式接口，当且仅当：它有且仅有一个抽象方法（无论是否标记 @FunctionalInterface）
        //                可以有多个 default 方法或 static 方法
        // 闭包与捕获：对于局部变量：对于基本类型：通过赋值捕获其值，不可以进行修改
        //                         对于引用类型：获取其引用，可以通过引用修改其对象内容，只是不能重新赋值
        //            理解为lambda创建的内部类这些字段全用final修饰
        //            对于实例变量和静态变量，可以直接访问
        new Thread(()->{
            // 由于flag = true，该线程会一直阻塞，直到主线程将其修改， volatile使得flag对不同线程可见
            // 
            // 由于线程不直接操作内存，而是操作cpu缓存，所以子线程涉及主线程的变量时，会存在修改不可见的情况
            // 静态变量放在方法区，是进程内的线程共享的，导致不可见的原因是缓存机制，volatile的原理在于使得线程读取变量刷新缓存
            // ai:JIT 编译器优化会认为以下代码中flag不会变，所以直接走寄存器，不在访问缓存，即使 MESI 已经让缓存失效，JIT 优化绕过了缓存检查
            // volatile 会禁止这种优化，强制每次读取都走缓存一致性协议
            while (flag) {}
            System.out.println("用于测试volatile的线程被执行");
        }).start();

        // 主线程停10秒用于看效果
        try{
            Thread.sleep(100);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("main主线程被执行");
        flag = false;
        
    }
}
