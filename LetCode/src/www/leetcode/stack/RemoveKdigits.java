package www.leetcode.stack;

import java.util.Stack;

/**
 * 从一个给定的数字字符串中删除k个数，使得该数最小
 * 
 * @author leetcode
 */
public class RemoveKdigits {
	public static void main(String[] args) {
		
	}
	/**
	 * 依次将数字压入栈中,如果当前数比栈顶数小，则将栈顶数出栈,当出栈数目为k时，剩下的数直接全压入栈
	 * @param s
	 * @param k
	 * @return
	 */
	private static String solution(String s,int k){
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		if(k>=len) return "0";
		Stack<Character> st = new Stack<Character>();
		for(int i=0;i<len;i++){
			while(k>0 && !st.isEmpty() && s.charAt(i)<st.peek()){
				st.pop();
				k--;
			}
			st.push(s.charAt(i));
		}
		while(k>0){
			st.pop();
			k--;
		}
		while(!st.isEmpty()){
			sb.append(st.pop());
		}
		sb.reverse();
		while(sb.length()>=1&& sb.charAt(0)=='0'){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
}
