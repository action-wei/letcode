package www.xiaowei.stack;

import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * k[encoded_string] ��ʾk��encoded_string,k������
 * ������Ķ�������ĸ�����Ậ������
 * 
 * @author xiaowei
 */
public class DecodeString {
	public static void main(String[] args) {
		String str = "3[a]2[bc]";
		String ret = solution(str);
		System.out.println(ret);
	}
	 private static String solution(String str){
		 Stack<Integer> countStack = new Stack<Integer>();
		 Stack<String> resStack = new Stack<String>();
		 String res = "";
		 int idx = 0;
		 while(idx<str.length()){
			 if(Character.isDigit(str.charAt(idx))){
				 //��ȡ����
				 int count = 0;
				 while(Character.isDigit(str.charAt(idx))){
					 count=10*count + str.charAt(idx)-'0';
					 idx++;
				 }
				 countStack.add(count);
			 }
			 else if(str.charAt(idx)=='['){
				 resStack.add(res);
				 res=""; //����һ���µġ�����Ŀ�ͷ��res�ÿ�
				 idx++;
			 }
			 else if(str.charAt(idx)==']'){
				 //һ�������ġ������򣬿�ʼ����
				 StringBuilder tmp = new StringBuilder(resStack.pop());
				 int num = countStack.pop();
				 for(int i=0;i<num;i++){
					 tmp.append(res);
				 }
				 res=tmp.toString();
				 idx++;
			 }else{
				 res+=str.charAt(idx++);
			 }
		 }
		 return res;
	 }
}
