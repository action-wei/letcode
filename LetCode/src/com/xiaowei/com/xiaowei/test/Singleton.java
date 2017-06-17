package com.xiaowei.com.xiaowei.test;

/**
 * ������ļ���˳��
 *
 * ���ۣ�
 * 1.���еľ�̬�����;�̬������˳�����
 * 2.��̬�ڲ������֮��Ҳ��һ��class�ļ�������ֻ����Ҫʹ�ø���ʱ���Żᱻ���أ����ص�˳�����ͨ��һ��
 *
 *  �����ڲ������Ĵ�����ʽ���ⲿ������ڲ���˽�г�Ա��Ȩ��
 *
 *  ���ۣ�
 *  1. ��̬�ڲ��ഴ������ʽ-- new outClass.innerClass();
 *  2.�Ǿ�̬�ڲ��ഴ������ʽ-- outClass out = new outClass();  outClass.innerClass inner = out.new innerClass();
 *  3.�ڲ���������Ϊ�ⲿ��ĳ�Ա�������ⲿ����ڲ�����Ի���������г�Ա������˽�г�Ա������ѭ��Ա����ԭ��
 *
 *  4.��̬�ڲ�����Ժ��о�̬�����;�̬��������̬�����;�̬��������ֱ�ӷ���,�磺outClass.innerClass.static_function();
 *  5.�Ǿ�̬�ڲ��಻���Ժ��о�̬�����;�̬���������Դ������˳�������Ϊʲô)
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

    //�ྲ̬����
    public static Singleton testOut = new Singleton(1);

    //�ྲ̬��
    static {
        System.out.println("Test Static");
    }

    //��̬�ڲ���
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
    //�Ǿ�̬�ڲ���
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


//        //���Դ����Ǿ�̬�ڲ������ķ�ʽ
        Singleton.nonStatic_inner_class nsic =  t.new nonStatic_inner_class();
        System.out.println(nsic.num);


//        //���Ծ�̬�ڲ���new ����ķ�ʽ
//        Singleton.static_inner_class sic = new Singleton.static_inner_class();
//        System.out.println(sic.num);
//        //��̬�ڲ���ľ�̬��������ֱ�ӷ���
//        Singleton.static_inner_class.print_static();

    }

}
