package com.xiaowei.com.xiaowei.test;

/**
 * ������ļ���˳��
 *
 * ���ۣ�
 * 1.���еľ�̬�����;�̬������˳�����
 * 2.��̬�ڲ������֮��Ҳ��һ��class�ļ�������ֻ����Ҫʹ�ø���ʱ���Żᱻ���أ����ص�˳�����ͨ��һ��
 *
 * Created by zhangwei on 2017/6/17.
 */
public class Singleton {

    public static class Inner{
        public final static Singleton testInstance = new Singleton(3);
        static {
            System.out.println("TestInner Static!");
        }
    }

    public static Singleton getInstance(){
        return Inner.testInstance;
    }

    public Singleton(int i ) {
        System.out.println("Test " + i +" Construct! ");
    }

    //�ྲ̬����
    public static Singleton testOut = new Singleton(1);

    //�ྲ̬��
    static {
        System.out.println("Test Static");
    }

    public static void main(String args[]){
        Singleton t = new Singleton(2);
//        Singleton.getInstance();
    }

}
