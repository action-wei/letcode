package com.xiaowei.com.xiaowei.test;

/**
 * 测试类的加载顺序
 *
 * 结论：
 * 1.类中的静态代码块和静态变量按顺序加载
 * 2.静态内部类编译之后也是一个class文件，所以只有需要使用该类时，才会被加载，加载的顺序和普通类一致
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

    //类静态属性
    public static Singleton testOut = new Singleton(1);

    //类静态块
    static {
        System.out.println("Test Static");
    }

    public static void main(String args[]){
        Singleton t = new Singleton(2);
//        Singleton.getInstance();
    }

}
