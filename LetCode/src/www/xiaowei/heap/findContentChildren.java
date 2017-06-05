package www.xiaowei.heap;

import java.util.Arrays;

/**
 * 455. Assign Cookies
 * 
 * @author xiaowei
 *
 * 2017年6月5日 下午5:31:23
 */
public class findContentChildren {
	public static int solution(int[] g, int[] s){
		Arrays.sort(g);
		Arrays.sort(s);
		int i = 0;
		for(int j=0;i<g.length && j<s.length;j++) {
			if(g[i]<=s[j]) i++;
		}
		return i;
		
	}
	public static void main(String[] args) {
		int[] g={10,9,8,7};
		int[] s={5,6,7,8};
		int ret= solution(g,s);
		System.out.println(ret);
	}
	
}
