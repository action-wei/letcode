package www.xiaowei.nowCoder;

import java.util.HashMap;
import java.util.Hashtable;

public class Test {
	//final�����ĳ�ʼ������
	final int count;
//	{//����ʹ�ô����ʼ��
//		this.count = 3;
//	}
	Test(){//����ʹ�ù��캯����ʼ����������ÿ�����캯�����ý��г�ʼ��
		this.count = 0;
	}
	public static void main(String[] args){
//		Integer i = new Integer(100);
		
/*		//�����޷������ƺ��з�������
		int num = -4;
		System.out.println(Integer.toBinaryString(num));
		System.out.println(num>>>1);//�޷�������
		System.out.println(Integer.valueOf("01111111111111111111111111111110", 2));
		System.out.println(num>>1);//�з�������     
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

