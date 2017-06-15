package www.leetcode.stack;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.(逆波兰式，也称后缀表达式）
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * @author leetcode
 */
public class EvalRPN {
	public static void main(String[] args) {
		//测试
		String tokens[] = {"0", "3", "/"};
		int ret = solution(tokens);
		System.out.println(ret);
	}
	
	public static Integer solution(String[] tokens){
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<tokens.length;i++){
			String s = tokens[i];
			if(s=="+" || s=="-" || s=="*" || s=="/"){
				Integer a = stack.pop();
				Integer b = stack.pop();
				Integer ret = null;
				if(s.equals("+")){
					ret = a+b;
				}else if(s.equals("-")){
					ret = b-a;
				}else if(s.equals("*")){
					ret = b*a;
				}else if(s.equals("/")){
					ret = b/a;
				}else{
					throw new IllegalArgumentException();
				}
				stack.add(ret);
			}else{
				stack.add(Integer.valueOf(s));
			}
		}
		if(!stack.isEmpty()) return stack.pop();
		else return null;
	}
}
