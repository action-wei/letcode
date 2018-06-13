package com.xiaowei.design;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������������ģʽ
 * Created by xiaowei on 2017/6/20.
 */
public class producer_consumer {
    public static void main(String[] args) {
        Storage store = new Storage(100);
        //������
        Producer producer1 = new Producer(10, store);
        Producer producer2 = new Producer(3, store);
        Producer producer3 = new Producer(2, store);
        //������
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
 * ��Ʒ�ֿ�
 */
class Storage{
    private int MAX_SIZE;// �ֿ���������
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

    //��ȡ�ֿ⵱ǰ����
    public int getSize() {
        return list.size();
    }

    //��ȡ�ֿ��ʣ�������ռ�
    public int getRemain() {
        return MAX_SIZE - list.size();
    }
    //��ֿ�����Ӳ�Ʒ
    public boolean add(Object o) {
        return list.add(o);
    }

    //�Ӷ������Ƴ���Ʒ
    public Object remove() {
        return list.remove();
    }

    /**
     * ����һ��ʹ�û����wait()��notify��������
     */
    /***-----------------------ʹ��wait()��notify��������ʵ��ͬ��------------------------**/
    //��������
    public void produce1(Producer producer) {
        synchronized (list) {
            //�жϲֿ��Ƿ���δ�ÿռ�
            while (getSize()+producer.getNum() > getMAX_SIZE()) {
                System.out.println("[��ǰ׼�������Ĳ�Ʒ����Ϊ��"+producer.getNum()+", �ֿ�Ŀɴ洢����Ϊ��"+ getRemain()+",��ͣ��������]");
                try {
                    list.wait();//��������
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //�ֿ��������㣬������Ʒ
            for (int i = 0; i < producer.getNum(); i++) {
                this.add(new Object());
            }
            System.out.println("[����������Ʒ��"+producer.getNum()+" �����ֿ�洢��Ʒ����Ϊ��"+getSize()+"]");
            list.notifyAll();
        }
    }

    //���ѷ���
    public void consume1(Consumer consumer) {
        synchronized (list) {
            while (getSize() - consumer.getNum() < 0) {
                //�ֿ�������������������
                System.out.println("[�ֿ�洢��Ϊ��" + getSize() + ", ������Ҫ���ѵ�����Ϊ��" + consumer.getNum() + ",�������㣬ֹͣ��������]");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //��������������
            for (int i = 0; i < consumer.getNum(); i++) {
                remove();
            }
            System.out.println("[�������Ѳ�Ʒ������" + consumer.getNum() + ",�ֿ�ʣ���Ʒ������" + getSize() + "]");
            list.notifyAll();
        }
    }

    /***-----------------------------ʹ��wait()��notify��������ʵ��ͬ��--------------------------------**/

    /**
     * ��������ʹ��Lock������
     */
    /*** ----------------------------ʹ��await()��signal()ʵ��ͬ��-------------------------------------**/
    //��
    private Lock lock = new ReentrantLock();
    //�ֿ�������������
    private final Condition full = lock.newCondition();
    //�ֿ�յ���������
    private final Condition empty = lock.newCondition();
    //������
    public void produce2(Producer producer) {
        lock.lock();
        while (getSize()+producer.getNum() > getMAX_SIZE()) {
            System.out.println("[��ǰ׼�������Ĳ�Ʒ����Ϊ��"+producer.getNum()+", �ֿ�Ŀɴ洢����Ϊ��"+ getRemain()+",��ͣ��������]");
            try {
                full.await();//��������
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //�ֿ��������㣬������Ʒ
        for (int i = 0; i < producer.getNum(); i++) {
            this.add(new Object());
        }
        System.out.println("[����������Ʒ��"+producer.getNum()+" �����ֿ�洢��Ʒ����Ϊ��"+getSize()+"]");

        // �������������߳�
        full.signalAll();
        empty.signalAll();

        // �ͷ���
        lock.unlock();
    }

    //���ѷ���
    public void consume2(Consumer consumer) {
        lock.lock();
        while (getSize() - consumer.getNum() < 0) {
            //�ֿ�������������������
            System.out.println("[�ֿ�洢��Ϊ��" + getSize() + ", ������Ҫ���ѵ�����Ϊ��" + consumer.getNum() + ",�������㣬ֹͣ��������]");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //��������������
        for (int i = 0; i < consumer.getNum(); i++) {
            remove();
        }
        System.out.println("[�������Ѳ�Ʒ������" + consumer.getNum() + ",�ֿ�ʣ���Ʒ������" + getSize() + "]");
        //���������߳�
        full.signalAll();
        empty.signalAll();
        //�ͷ���
        lock.unlock();
    }
    /** -----------------------------ʹ��await()��signal()--------------------------------------------**/


    /**
     * ���������÷�ʽֱ��ʹ��ͬ������BlockingQueue���ڲ�ʵ��ԭ��ͬawait() / signal()����
     */
    /**------------------------------BlockingQueue�������з���-----------------------------------------**/
    // �ֿ�洢������
    private LinkedBlockingQueue<Object> blockQueue = new LinkedBlockingQueue<>(100);
    //��������
    public void produce3(Producer producer) {
        if (blockQueue.size() == getMAX_SIZE()) {
            System.out.println("[�ֿ�Ŀɴ洢����Ϊ��"+ (getMAX_SIZE()-blockQueue.size()) +",��ͣ��������]");
        }
        //�ֿ��������㣬������Ʒ
        for (int i = 0; i < producer.getNum(); i++) {
            try {
                blockQueue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[������Ʒ���ֿ�洢��Ʒ����Ϊ��"+ blockQueue.size() + "]");
    }
    //���ѷ���
    public void consume3(Consumer consumer) {
        if (blockQueue.size() == 0) {
            System.out.println("[�ֿ��ʣ���Ʒ��Ϊ��"+blockQueue.size()+"��ֹͣ��������]");
        }
        //ʣ���Ʒ���㣬��������
        for(int i=0;i<consumer.getNum();i++) {
            try {
                blockQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[���Ѳ�Ʒ���ֿ�ʣ���Ʒ��Ϊ��"+blockQueue.size()+"]");
    }
    /**------------------------------BlockingQueue�������з���-----------------------------------------**/

}

/**
 * ������
 */
class Producer implements Runnable{
    private int num; //ÿ������������
    private Storage store;//��Ʒ�Ĵ�ŵ�

    public Producer( int num,Storage store) {
        this.num = num;
        this.store = store;
    }

    public int getNum() {
        return num;
    }
    //��������ÿ������������
    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStore() {
        return store;
    }

    public void setStore(Storage store) {
        this.store = store;
    }

    //��������
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
 * ������
 */
class Consumer implements Runnable{
    private int num;//һ�����ѵ�����
    private Storage store; //���ѵĲֿ�

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

    //���ѷ���
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
