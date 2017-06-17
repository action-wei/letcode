package com.xiaowei.design;

/**
 * 单例设计模式
 * Created by zhangwei on 2017/6/17.
 */
public class Singletond_design {


}

/**
 * 超级简单对写法，使用枚举
 * EasySingleton.INSTANCE来访问实例，创建枚举默认是线程安全的
 *
 * 说明：枚举类也是一种特殊形式的Java类，枚举类中声明的每一个枚举值代表枚举类的一个实例对象
 */
enum Singleton_enum{
    INSTANCE;
}

/**
 * 静态内部类实现单例模式（推荐）
 */
class Singleton_staticInnerClass{
    //静态内部类
    private static class SingletonHolder{
        private static Singleton_staticInnerClass instance = new Singleton_staticInnerClass();
    }
    private Singleton_staticInnerClass(){}

    public static final Singleton_staticInnerClass getInstance(){
        return SingletonHolder.instance;

    }


}

/**
 * 懒汉式
 * 需要获取对象时才生成对象，合理利用资源，通过加锁实现线程安全
 */
class Singleton_lazy{
    private static volatile Singleton_lazy instance = null;
    //私有构造函数
    private Singleton_lazy(){}

    //公有方法
    public void print() {
        System.out.println("this is Singleton_lazy");
    }

    /**
     * 获取单例对象的接口
     * 特点：对方法加锁，性能低下
     * @return
     */
    public static synchronized Singleton_lazy getInstance1(){
        if(instance==null){
            instance = new Singleton_lazy();
        }
        return instance;
    }

    /**
     * 获取单例对象的接口
     * 特点：双重判断，性能高效
     *
     * 问题：实际使用中，存在调用出错对情况，当然概率很小，原因分析如下
     * instance = new Singleton_lazy();并非是一个原子操作,事实上在 JVM 中这句话大概做了下面 3 件事情:
     * 1.给 instance 分配内存
     * 2.调用 Singleton 的构造函数来初始化成员变量
     * 3.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
     * 但是在 JVM 的即时编译器中存在指令重排序的优化。
     * 也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。
     * 如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null 了（但却没有初始化），
     * 所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。
     *
     * 解决方案：将instance变量声明为volatile从而禁止jvm指令重排序
     *
     */
    public static Singleton_lazy getInstance2() {
        //该处的判空语句，实际使用中可以极大的提高程序的性能，因为单例对象只会创建一次
        //所以只有一次的加锁和释放锁的额外性能开销，相比每次加锁的判断，性能大大提升
        if (instance == null) {
            synchronized (Singleton_lazy.class) {
                if (instance == null) {
                    //下面对语句并非原子操作
                    instance = new Singleton_lazy();
                }
            }
        }
        return instance;
    }

}

/**
 * 饿汉式
 * 特点：加载类后，一开始就创建了对象；线程安全，但是浪费资源
 * 缺点：由于该方式是类加载后就创建对象，从而在一些场景中无法使用：
 * 比如创建singleton对象需要依赖一些外界参数，而这些参数需要在调用getInstance方法之前设置进去
 */
class Singleton_hungry{
    private static Singleton_hungry instance ;// = new Singleton_hungry();
    static {
        instance = new Singleton_hungry();
    }
    //私有化构造函数
    private Singleton_hungry(){};
    //公有方法
    public void print() {
        System.out.println("this is Singleton_hungry");
    }

    //获取单例对象的接口
    public static Singleton_hungry getInstance() {
        return instance;
    }
}
