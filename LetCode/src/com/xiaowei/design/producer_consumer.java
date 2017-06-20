package com.xiaowei.design;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 * Created by xiaowei on 2017/6/20.
 */
public class producer_consumer {
    public static void main(String[] args) {
        Storage store = new Storage(100);
        //生产者
        Producer producer1 = new Producer(10, store);
        Producer producer2 = new Producer(3, store);
        Producer producer3 = new Producer(2, store);
        //消费者
        Consumer consumer1 = new Consumer(5, store);
        Consumer consumer2 = new Consumer(2, store);

        Thread thread1 = new Thread(producer1);
        Thread thread2 = new Thread(producer2);
        Thread thread3 = new Thread(producer3);
        Thread thread4 = new Thread(consumer1);
        Thread thread5 = new Thread(consumer2);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }
}

/**
 * 产品仓库
 */
class Storage{
    private int MAX_SIZE;// 仓库的最大容量
    private LinkedList<Object> list = new LinkedList<Object>();

    public Storage(int size) {
        this.MAX_SIZE = size;
    }

    public List<Object> getList() {
        return list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    //获取仓库当前容量
    public int getSize() {
        return list.size();
    }

    //获取仓库的剩余容量空间
    public int getRemain() {
        return MAX_SIZE - list.size();
    }
    //向仓库中添加产品
    public boolean add(Object o) {
        return list.add(o);
    }

    //从队列中移除产品
    public Object remove() {
        return list.remove();
    }

    /**
     * 方案一：使用基类的wait()和notify（）方法
     */
    /***-----------------------使用wait()和notify（）方法实现同步------------------------**/
    //生产方法
    public void produce1(Producer producer) {
        synchronized (list) {
            //判断仓库是否还有未用空间
            while (getSize()+producer.getNum() > getMAX_SIZE()) {
                System.out.println("[当前准备生产的产品数量为："+producer.getNum()+", 仓库的可存储容量为："+ getRemain()+",暂停生产任务]");
                try {
                    list.wait();//生产阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //仓库容量充足，生产商品
            for (int i = 0; i < producer.getNum(); i++) {
                this.add(new Object());
            }
            System.out.println("[本次生产产品："+producer.getNum()+" 件，仓库存储产品数量为："+getSize()+"]");
            list.notifyAll();
        }
    }

    //消费方法
    public void consume1(Consumer consumer) {
        synchronized (list) {
            while (getSize() - consumer.getNum() < 0) {
                //仓库库存容量不足消费数量
                System.out.println("[仓库存储量为：" + getSize() + ", 本次需要消费的数量为：" + consumer.getNum() + ",数量不足，停止消费任务]");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //可以正常消费了
            for (int i = 0; i < consumer.getNum(); i++) {
                remove();
            }
            System.out.println("[本次消费产品数量：" + consumer.getNum() + ",仓库剩余产品数量：" + getSize() + "]");
            list.notifyAll();
        }
    }

    /***-----------------------------使用wait()和notify（）方法实现同步--------------------------------**/

    /**
     * 方案二：使用Lock锁机制
     */
    /*** ----------------------------使用await()和signal()实现同步-------------------------------------**/
    //锁
    private Lock lock = new ReentrantLock();
    //仓库满的条件变量
    private final Condition full = lock.newCondition();
    //仓库空的条件变量
    private final Condition empty = lock.newCondition();
    //生产者
    public void produce2(Producer producer) {
        lock.lock();
        while (getSize()+producer.getNum() > getMAX_SIZE()) {
            System.out.println("[当前准备生产的产品数量为："+producer.getNum()+", 仓库的可存储容量为："+ getRemain()+",暂停生产任务]");
            try {
                full.await();//生产阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //仓库容量充足，生产商品
        for (int i = 0; i < producer.getNum(); i++) {
            this.add(new Object());
        }
        System.out.println("[本次生产产品："+producer.getNum()+" 件，仓库存储产品数量为："+getSize()+"]");

        // 唤醒其他所有线程
        full.signalAll();
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }

    //消费方法
    public void consume2(Consumer consumer) {
        lock.lock();
        while (getSize() - consumer.getNum() < 0) {
            //仓库库存容量不足消费数量
            System.out.println("[仓库存储量为：" + getSize() + ", 本次需要消费的数量为：" + consumer.getNum() + ",数量不足，停止消费任务]");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //可以正常消费了
        for (int i = 0; i < consumer.getNum(); i++) {
            remove();
        }
        System.out.println("[本次消费产品数量：" + consumer.getNum() + ",仓库剩余产品数量：" + getSize() + "]");
        //唤醒其他线程
        full.signalAll();
        empty.signalAll();
        //释放锁
        lock.unlock();
    }
    /** -----------------------------使用await()和signal()--------------------------------------------**/


    /**
     * 方案三：该方式直接使用同步队列BlockingQueue，内部实现原理同await() / signal()方法
     */
    /**------------------------------BlockingQueue阻塞队列方法-----------------------------------------**/
    // 仓库存储的载体
    private LinkedBlockingQueue<Object> blockQueue = new LinkedBlockingQueue<>(100);
    //生产方法
    public void produce3(Producer producer) {
        if (blockQueue.size() == getMAX_SIZE()) {
            System.out.println("[仓库的可存储容量为："+ (getMAX_SIZE()-blockQueue.size()) +",暂停生产任务]");
        }
        //仓库容量充足，生产商品
        for (int i = 0; i < producer.getNum(); i++) {
            try {
                blockQueue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[生产产品，仓库存储产品数量为："+ blockQueue.size() + "]");
    }
    //消费方法
    public void consume3(Consumer consumer) {
        if (blockQueue.size() == 0) {
            System.out.println("[仓库的剩余产品数为："+blockQueue.size()+"，停止消费任务]");
        }
        //剩余产品充足，正常消费
        for(int i=0;i<consumer.getNum();i++) {
            try {
                blockQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[消费产品，仓库剩余产品数为："+blockQueue.size()+"]");
    }
    /**------------------------------BlockingQueue阻塞队列方法-----------------------------------------**/

}

/**
 * 生产者
 */
class Producer implements Runnable{
    private int num; //每次生产的数量
    private Storage store;//产品的存放地

    public Producer( int num,Storage store) {
        this.num = num;
        this.store = store;
    }

    public int getNum() {
        return num;
    }
    //重新设置每次生产的数量
    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStore() {
        return store;
    }

    public void setStore(Storage store) {
        this.store = store;
    }

    //生产方法
    public void produce() {
//        store.produce1(this);
//        store.produce2(this);
        store.produce3(this);
    }

    @Override
    public void run() {
        while(true)
            produce();
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private int num;//一次消费的数量
    private Storage store; //消费的仓库

    public Consumer(int num, Storage store) {
        this.num = num;
        this.store = store;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStore() {
        return store;
    }

    public void setStore(Storage store) {
        this.store = store;
    }

    //消费方法
    public void consumer() {
//        store.consume1(this);
//        store.consume2(this);
        store.consume3(this);
    }

    @Override
    public void run() {
        while (true)
            consumer();
    }
}
