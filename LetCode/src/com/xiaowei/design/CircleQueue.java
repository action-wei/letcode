package com.xiaowei.design;

import java.util.Arrays;

/**
 * Created by zhangwei on 2017/6/22.
 */
public class CircleQueue<T> {


    private int DEFAULT_SIZE = 10;

    private int capacity;//保存数组的长度
    private Object[] elementData;//定义一个数组用于保存循环队列的元素

    private int front = 0;//队头

    private int rear = 0;//队尾

    //以默认数组长度创建空循环队列
    public CircleQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    //以一个初始化元素来创建循环队列
    public CircleQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    /**
     * 以指定长度的数组来创建循环队列
     * @param element 指定循环队列中第一个元素
     * @param initSize 指定循环队列底层数组的长度
     */
    public CircleQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    //获取循环队列的大小
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear > front ? rear - front : capacity - (front - rear);
    }

    //插入队列
    public void add(T element) {
        if (rear == front && elementData[front] != null) {
            front = (rear+capacity/2)%capacity;
        }
        elementData[rear++] = element;
        //如果rear已经到头，那就转头
        rear = rear == capacity ? 0 : rear;
    }

    //移除队列
    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        //保留队列的front端的元素的值
        T oldValue = (T) elementData[front];
        //释放队列的front端的元素
        elementData[front++] = null;
        //如果front已经到头，那就转头
        front = front == capacity ? 0 : front;
        return oldValue;
    }

    //返回队列顶元素，但不删除队列顶元素
    public T poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        return (T) elementData[front];
    }

    //判断循环队列是否为空队列
    public boolean isEmpty() {
        //rear==front
        return rear == front;
    }

    //清空循环队列
    public void clear() {
        //将底层数组所有元素赋为null
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            //如果front < rear，有效元素就是front到rear之间的元素
            if (front < rear) {
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < rear; i++) {
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len - 2, len).append("]").toString();
            }
            //如果front >= rear，有效元素为front->capacity之间、0->front之间的
            else {
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < capacity; i++) {
                    sb.append(elementData[i].toString() + ", ");
                }
                for (int i = 0; i < rear; i++) {
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len - 2, len).append("]").toString();
            }
        }
    }

    public static void main(String[] args) {
        CircleQueue<String> queue = new CircleQueue<String>("aaaa", 10);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int num = 17;
                while(num>0){
                    System.out.println("num:"+num);
                    queue.add("string"+num);
                    num--;
                }
                System.out.println(queue);;
            }
        });
        thread.start();
    }

}
