package www.bupt.nowCoder;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * 查找一个有序数组的绝对值最小的值
 * 最好的解法--二分查找
 * 次好解法---使用优先队列
 */
public class getAbsoluteMinValue {
    static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int ret = solve();
		System.out.println(ret);
		
		MyComparator com = new getAbsoluteMinValue().new MyComparator();
		com.test();
	}
	
	//成员内部类
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
	 * 使用优先队列的方式
	 */
	static int solve(){
		//成员内部类在函数中调用时，需要外部类的对象调用new InnerClass()才行！
		//此种只用一次的类，最好使用局部内部类或者匿名内部类更加合适
		MyComparator mycomparator = new getAbsoluteMinValue().new MyComparator();
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>(62,mycomparator);
		int number;
		int n;//数组的长度
		n = scan.nextInt();
		for(int i=0;i<n;i++){
			number = scan.nextInt();
			pque.add(Integer.valueOf(number));
		}
		return pque.peek().intValue();
	}
}
