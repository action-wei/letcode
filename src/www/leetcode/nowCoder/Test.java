package www.leetcode.nowCoder;

public class Test {
	
	//测试String的参数传递特点
	static String str = new String("hello");
	
	//final常量的初始化方法
	final int count;
	{//可以使用代码初始化
//		this.count = 3;
	}
	Test(){
		//可以使用构造函数初始化，不过得每个构造函数都得进行初始化
		this.count = 0;
	}
	
	//测试String传递时，使用的传引用方式，当对String对象进行修改时，会重新生成对象
	public void funStr(String para_obj){
		System.out.println("before modified:");
		if(para_obj==str){
			System.out.println("para and str are the same one object");
		}else{
			System.out.println("para and str are not the same one object");
		}
		para_obj+=" world ";
		System.out.println("after modified");
		if(para_obj==str){
			System.out.println("para_obj and str are the same one object");
		}else{
			System.out.println("para and str are not the same one object");
		}
	}
	
	//测试基本类型的包装类型的函数参数传值
	public void funInteger(Integer obj){
		obj+=10;
		System.out.println("this obj value is: "+ obj);
	}
	
	public static void main(String[] args){
		
/*		//测试Integer等包装类的传参特点----结论：值传递
		Integer inte = new Integer(6);
		new Test().funInteger(inte);
		System.out.println("orgin inte value: "+inte);*/
		
/*		//测试String的传参特点
		new Test().funStr(str);
		System.out.println(str);*/
		
		
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
		
//		int num = Integer.MAX_VALUE;
//		System.out.println(num+1);
//		if(num+1>num){
//			System.out.println("greater");
//		}else{
//			System.out.println("less");
//		}
		
		}
}

