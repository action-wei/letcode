package www.bupt.nowCoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 计算一个字符串有多少子串是偶串
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
	 * 使用动态规划的解法---偶串+偶串等于偶串
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
		int gi = 0; //gi是用低26bit（int是32bit）表示下标为[0,i]的子串所拥分别有的字母是偶数个（0）还是奇数个（1）
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0, 1);
		for(int i=0;i<len;i++){
			int x = str.charAt(i) - 'a'; //求得新加入的这个字符的bit位置
			gi ^= (1 << x);  //求加入这个字符后，原来的gi拥有字母个数的奇偶性。如果异或后是0，表示加入该字符后有偶数个字母，反正是奇数个。
			if(map.containsKey(gi)){
				count+=map.get(gi);//g0,g1,g2...g(i-1)和gi相等的，都可以得到一个偶串。有多少个相等，就有多少个偶串。 
				map.put(gi, map.get(gi)+1);
			}else{
				map.put(gi,1);
			}
		}
		return count;
	}
	
	/**
	 * 暴力解法----时间会超时
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
