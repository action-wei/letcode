package com.xiaowei.design;

import java.util.Arrays;

/**
 * Created by zhangwei on 2017/6/22.
 */
public class CircleQueue<T> {


    private int DEFAULT_SIZE = 10;

    private int capacity;//��������ĳ���
    private Object[] elementData;//����һ���������ڱ���ѭ�����е�Ԫ��

    private int front = 0;//��ͷ

    private int rear = 0;//��β

    //��Ĭ�����鳤�ȴ�����ѭ������
    public CircleQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    //��һ����ʼ��Ԫ��������ѭ������
    public CircleQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    /**
     * ��ָ�����ȵ�����������ѭ������
     * @param element ָ��ѭ�������е�һ��Ԫ��
     * @param initSize ָ��ѭ�����еײ�����ĳ���
     */
    public CircleQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    //��ȡѭ�����еĴ�С
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear > front ? rear - front : capacity - (front - rear);
    }

    //�������
    public void add(T element) {
        if (rear == front && elementData[front] != null) {
            front = (rear+capacity/2)%capacity;
        }
        elementData[rear++] = element;
        //���rear�Ѿ���ͷ���Ǿ�תͷ
        rear = rear == capacity ? 0 : rear;
    }

    //�Ƴ�����
    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("�ն����쳣");
        }
        //�������е�front�˵�Ԫ�ص�ֵ
        T oldValue = (T) elementData[front];
        //�ͷŶ��е�front�˵�Ԫ��
        elementData[front++] = null;
        //���front�Ѿ���ͷ���Ǿ�תͷ
        front = front == capacity ? 0 : front;
        return oldValue;
    }

    //���ض��ж�Ԫ�أ�����ɾ�����ж�Ԫ��
    public T poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("�ն����쳣");
        }
        return (T) elementData[front];
    }

    //�ж�ѭ�������Ƿ�Ϊ�ն���
    public boolean isEmpty() {
        //rear==front
        return rear == front;
    }

    //���ѭ������
    public void clear() {
        //���ײ���������Ԫ�ظ�Ϊnull
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            //���front < rear����ЧԪ�ؾ���front��rear֮���Ԫ��
            if (front < rear) {
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < rear; i++) {
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len - 2, len).append("]").toString();
            }
            //���front >= rear����ЧԪ��Ϊfront->capacity֮�䡢0->front֮���
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
        final CircleQueue<String> queue = new CircleQueue<String>("aaaa", 10);
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