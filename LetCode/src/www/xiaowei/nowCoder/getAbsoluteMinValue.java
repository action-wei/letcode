package www.bupt.nowCoder;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * ����һ����������ľ���ֵ��С��ֵ
 * ��õĽⷨ--���ֲ���
 * �κýⷨ---ʹ�����ȶ���
 */
public class getAbsoluteMinValue {
    static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int ret = solve();
		System.out.println(ret);
		
		MyComparator com = new getAbsoluteMinValue().new MyComparator();
		com.test();
	}
	
	//��Ա�ڲ���
	class MyComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			if(Math.abs(o1)<Math.abs(o2)){
				return -1;
			}
			return 0;
		}
		
		void test(){
			System.out.println("this is a test");
		}
	}
	/*
	 * ʹ�����ȶ��еķ�ʽ
	 */
	static int solve(){
		//��Ա�ڲ����ں����е���ʱ����Ҫ�ⲿ��Ķ������new InnerClass()���У�
		//����ֻ��һ�ε��࣬���ʹ�þֲ��ڲ�����������ڲ�����Ӻ���
		MyComparator mycomparator = new getAbsoluteMinValue().new MyComparator();
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>(62,mycomparator);
		int number;
		int n;//����ĳ���
		n = scan.nextInt();
		for(int i=0;i<n;i++){
			number = scan.nextInt();
			pque.add(Integer.valueOf(number));
		}
		return pque.peek().intValue();
	}
}
