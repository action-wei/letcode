package www.leetcode.nowCoder;

public class Test {
	
	//����String�Ĳ��������ص�
	static String str = new String("hello");
	
	//final�����ĳ�ʼ������
	final int count;
	{//����ʹ�ô����ʼ��
//		this.count = 3;
	}
	Test(){
		//����ʹ�ù��캯����ʼ����������ÿ�����캯�����ý��г�ʼ��
		this.count = 0;
	}
	
	//����String����ʱ��ʹ�õĴ����÷�ʽ������String��������޸�ʱ�����������ɶ���
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
	
	//���Ի������͵İ�װ���͵ĺ���������ֵ
	public void funInteger(Integer obj){
		obj+=10;
		System.out.println("this obj value is: "+ obj);
	}
	
	public static void main(String[] args){
		
/*		//����Integer�Ȱ�װ��Ĵ����ص�----���ۣ�ֵ����
		Integer inte = new Integer(6);
		new Test().funInteger(inte);
		System.out.println("orgin inte value: "+inte);*/
		
/*		//����String�Ĵ����ص�
		new Test().funStr(str);
		System.out.println(str);*/
		
		
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
		
//		int num = Integer.MAX_VALUE;
//		System.out.println(num+1);
//		if(num+1>num){
//			System.out.println("greater");
//		}else{
//			System.out.println("less");
//		}
		
		}
}

