package www.bupt.nowCoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ����һ���ַ����ж����Ӵ���ż��
 * 
 * @author xiaowei
 */
public class CountEvenString {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		int ret = better_solution(str);
		System.out.println(ret);
	}
	
	/**
	 * ʹ�ö�̬�滮�Ľⷨ---ż��+ż������ż��
	 * @param str
	 * @return
	 */
	private static int better_solution(String str){
		int count = 0;
		int len = str.length();
/*		char ch[] = new char[len];
		for(int i=0;i<len;i++){
			ch[i]=str.charAt(i);
		}
		int dp[] = new int[len];
		dp[0]=ch[0];
		for(int j=1;j<len;j++){
			dp[j] = dp[j-1]^str.charAt(j);
		}*/
		int gi = 0; //gi���õ�26bit��int��32bit����ʾ�±�Ϊ[0,i]���Ӵ���ӵ�ֱ��е���ĸ��ż������0��������������1��
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0, 1);
		for(int i=0;i<len;i++){
			int x = str.charAt(i) - 'a'; //����¼��������ַ���bitλ��
			gi ^= (1 << x);  //���������ַ���ԭ����giӵ����ĸ��������ż�ԡ����������0����ʾ������ַ�����ż������ĸ����������������
			if(map.containsKey(gi)){
				count+=map.get(gi);//g0,g1,g2...g(i-1)��gi��ȵģ������Եõ�һ��ż�����ж��ٸ���ȣ����ж��ٸ�ż���� 
				map.put(gi, map.get(gi)+1);
			}else{
				map.put(gi,1);
			}
		}
		return count;
	}
	
	/**
	 * �����ⷨ----ʱ��ᳬʱ
	 * @param str
	 * @return
	 */
	private static int solution(String str){
		int number = 0;
		for(int i=0;i<str.length();i++){
			for(int j=i;j<str.length();j++){
				boolean ret = isEvenStr(str.substring(i, j+1));
				if(ret) number++;
			}
		}
		return number;
	}
	
	private static boolean isEvenStr(String str){
		if(str.length()%2!=0) return false;
		int p=0;
		for(int i=0;i<str.length()-1;i++){
			p = str.charAt(i)^str.charAt(i+1);
		}
		if(p!=0) return false;
		return true;
	}
}
