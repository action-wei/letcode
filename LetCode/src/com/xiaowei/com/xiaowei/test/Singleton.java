package com.xiaowei.com.xiaowei.test;

/**
 * 测试类的加载顺序
 *
 * 结论：
 * 1.类中的静态代码块和静态变量按顺序加载
 * 2.静态内部类编译之后也是一个class文件，所以只有需要使用该类时，才会被加载，加载的顺序和普通类一致
 *
 *  测试内部类对象的创建方式及外部类访问内部类私有成员的权限
 *
 *  结论：
 *  1. 静态内部类创建对象方式-- new outClass.innerClass();
 *  2.非静态内部类创建对象方式-- outClass out = new outClass();  outClass.innerClass inner = out.new innerClass();
 *  3.内部类可以理解为外部类的成员，所以外部类和内部类可以互相访问所有成员（包含私有成员），遵循成员互访原则
 *
 *  4.静态内部类可以含有静态方法和静态变量，静态变量和静态方法可以直接访问,如：outClass.innerClass.static_function();
 *  5.非静态内部类不可以含有静态方法和静态变量（可以从类加载顺序来理解为什么)
 *
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

    //静态内部类
    static class static_inner_class{
        private int num;
        public static_inner_class(){
            num= 10;
        }

        public static void print_static() {
            System.out.println("this is inner class static function");
        }

        public void nonStatic_print() {
            System.out.println("this is inner class nonStatic function");
        }
    }
    //非静态内部类
    class nonStatic_inner_class{
        private int num;
        public nonStatic_inner_class(){
            num= 10;
        }
        public void print() {
            System.out.println("this is inner class nonStatic function");
        }
    }

    public static void main(String args[]){
        Singleton t = new Singleton(2);
//        Singleton.getInstance();


//        //测试创建非静态内部类对象的方式
        Singleton.nonStatic_inner_class nsic =  t.new nonStatic_inner_class();
        System.out.println(nsic.num);


//        //测试静态内部类new 对象的方式
//        Singleton.static_inner_class sic = new Singleton.static_inner_class();
//        System.out.println(sic.num);
//        //静态内部类的静态方法可以直接访问
//        Singleton.static_inner_class.print_static();

    }

}
