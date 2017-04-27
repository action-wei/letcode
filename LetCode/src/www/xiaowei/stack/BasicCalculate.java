package www.xiaowei.stack;

import java.util.Stack;

/**
 * ����һ���򵥵��ַ������ʽ���������������ʽ�к��� + - ���� �Ǹ�����
 * 
 * @author xiaowei
 */
public class BasicCalculate {
	public static void main(String[] args) {
		String expression = "( 1 + ( 4 + 5 + 2 ) - 3 ) + ( 6 + 8 )";
//		String expression = "( 1 )";
		//���Խ��
		int ret = calculate(expression);
		System.out.println(ret);
	}
	
	public static int calculate(String str){
		Stack<Integer> stack = new Stack<Integer>();
		int result = 0;
		int sign = 1; //���ڱ�ǡ�+�� ���� ��-��
		int num = 0;
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(Character.isDigit(c)){
				num = num*10 + c-'0';
			}else if(c=='+'){
				result+=num*sign;
				num=0;
				sign=1;
			}else if(c=='-'){
				result+=num*sign;
				num=0;
				sign=-1;
			}else if(c=='('){
				stack.add(result);
				stack.add(sign);
				result =0;
				sign = 1;
			}else if(c==')'){
				result+= num*sign;
				num = 0;
				result*=stack.pop();
				result+=stack.pop();
			}
		}
		if(num!=0){
			result+=num*sign;
		}
		return result;
	}
}
