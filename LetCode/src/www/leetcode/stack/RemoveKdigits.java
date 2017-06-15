package www.leetcode.stack;

import java.util.Stack;

/**
 * ��һ�������������ַ�����ɾ��k������ʹ�ø�����С
 * 
 * @author leetcode
 */
public class RemoveKdigits {
	public static void main(String[] args) {
		
	}
	/**
	 * ���ν�����ѹ��ջ��,�����ǰ����ջ����С����ջ������ջ,����ջ��ĿΪkʱ��ʣ�µ���ֱ��ȫѹ��ջ
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
