package www.xiaowei.stack;

import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * k[encoded_string] 表示k个encoded_string,k是正数
 * 被编码的对象都是字母，不会含有数字
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
				 //抽取数字
				 int count = 0;
				 while(Character.isDigit(str.charAt(idx))){
					 count=10*count + str.charAt(idx)-'0';
					 idx++;
				 }
				 countStack.add(count);
			 }
			 else if(str.charAt(idx)=='['){
				 resStack.add(res);
				 res=""; //遇到一个新的【】域的开头，res置空
				 idx++;
			 }
			 else if(str.charAt(idx)==']'){
				 //一个完整的【】的域，开始解码
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
