package www.xiaowei.nowCoder;

import java.util.HashMap;
import java.util.Hashtable;

public class Test {
	//final常量的初始化方法
	final int count;
//	{//可以使用代码初始化
//		this.count = 3;
//	}
	Test(){//可以使用构造函数初始化，不过得每个构造函数都得进行初始化
		this.count = 0;
	}
	public static void main(String[] args){
//		Integer i = new Integer(100);
		
/*		//测试无符号右移和有符号右移
		int num = -4;
		System.out.println(Integer.toBinaryString(num));
		System.out.println(num>>>1);//无符号右移
		System.out.println(Integer.valueOf("01111111111111111111111111111110", 2));
		System.out.println(num>>1);//有符号右移     
*/	
//		HashMap map = new HashMap();
//		Hashtable table = new Hashtable();
		
		int num = Integer.MAX_VALUE;
		System.out.println(num+1);
		if(num+1>num){
			System.out.println("greater");
		}else{
			System.out.println("less");
		}
		
		}
}

