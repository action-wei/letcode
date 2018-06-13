package com.xiaowei.design;

/**
 * �������ģʽ
 * Created by zhangwei on 2017/6/17.
 */
public class Singletond_design {


}

/**
 * �����򵥶�д����ʹ��ö��
 * EasySingleton.INSTANCE������ʵ��������ö��Ĭ�����̰߳�ȫ��
 *
 * ˵����ö����Ҳ��һ��������ʽ��Java�࣬ö������������ÿһ��ö��ֵ����ö�����һ��ʵ������
 */
enum Singleton_enum{
    INSTANCE;
}

/**
 * ��̬�ڲ���ʵ�ֵ���ģʽ���Ƽ���
 */
class Singleton_staticInnerClass{
    //��̬�ڲ���
    private static class SingletonHolder{
        private static Singleton_staticInnerClass instance = new Singleton_staticInnerClass();
    }
    private Singleton_staticInnerClass(){}

    public static final Singleton_staticInnerClass getInstance(){
        return SingletonHolder.instance;

    }


}

/**
 * ����ʽ
 * ��Ҫ��ȡ����ʱ�����ɶ��󣬺���������Դ��ͨ������ʵ���̰߳�ȫ
 */
class Singleton_lazy{
    private static volatile Singleton_lazy instance = null;
    //˽�й��캯��
    private Singleton_lazy(){}

    //���з���
    public void print() {
        System.out.println("this is Singleton_lazy");
    }

    /**
     * ��ȡ��������Ľӿ�
     * �ص㣺�Է������������ܵ���
     * @return
     */
    public static synchronized Singleton_lazy getInstance1(){
        if(instance==null){
            instance = new Singleton_lazy();
        }
        return instance;
    }

    /**
     * ��ȡ��������Ľӿ�
     * �ص㣺˫���жϣ����ܸ�Ч
     *
     * ���⣺ʵ��ʹ���У����ڵ��ó�����������Ȼ���ʺ�С��ԭ���������
     * instance = new Singleton_lazy();������һ��ԭ�Ӳ���,��ʵ���� JVM ����仰����������� 3 ������:
     * 1.�� instance �����ڴ�
     * 2.���� Singleton �Ĺ��캯������ʼ����Ա����
     * 3.��instance����ָ�������ڴ�ռ䣨ִ�����ⲽ instance ��Ϊ�� null �ˣ�
     * ������ JVM �ļ�ʱ�������д���ָ����������Ż���
     * Ҳ����˵����ĵڶ����͵�������˳���ǲ��ܱ�֤�ģ����յ�ִ��˳������� 1-2-3 Ҳ������ 1-3-2��
     * ����Ǻ��ߣ����� 3 ִ����ϡ�2 δִ��֮ǰ�����̶߳���ռ�ˣ���ʱ instance �Ѿ��Ƿ� null �ˣ���ȴû�г�ʼ������
     * �����̶߳���ֱ�ӷ��� instance��Ȼ��ʹ�ã�Ȼ��˳����µر���
     *
     * �����������instance��������Ϊvolatile�Ӷ���ֹjvmָ��������
     *
     */
    public static Singleton_lazy getInstance2() {
        //�ô����п���䣬ʵ��ʹ���п��Լ������߳�������ܣ���Ϊ��������ֻ�ᴴ��һ��
        //����ֻ��һ�εļ������ͷ����Ķ������ܿ��������ÿ�μ������жϣ����ܴ������
        if (instance == null) {
            synchronized (Singleton_lazy.class) {
                if (instance == null) {
                    //�������䲢��ԭ�Ӳ���
                    instance = new Singleton_lazy();
                }
            }
        }
        return instance;
    }

}

/**
 * ����ʽ
 * �ص㣺�������һ��ʼ�ʹ����˶����̰߳�ȫ�������˷���Դ
 * ȱ�㣺���ڸ÷�ʽ������غ�ʹ������󣬴Ӷ���һЩ�������޷�ʹ�ã�
 * ���紴��singleton������Ҫ����һЩ������������Щ������Ҫ�ڵ���getInstance����֮ǰ���ý�ȥ
 */
class Singleton_hungry{
    private static Singleton_hungry instance ;// = new Singleton_hungry();
    static {
        instance = new Singleton_hungry();
    }
    //˽�л����캯��
    private Singleton_hungry(){};
    //���з���
    public void print() {
        System.out.println("this is Singleton_hungry");
    }

    //��ȡ��������Ľӿ�
    public static Singleton_hungry getInstance() {
        return instance;
    }
}
