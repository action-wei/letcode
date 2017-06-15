package www.leetcode.stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * pop,top,push function is same as normal.
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * @author leetcode
 */
public class MinStack {
	// �洢���ݵ���ջ
	private static Stack<Integer> stack = new Stack<Integer>();
	//�洢ջ����Сֵ�ĸ���ջ
	private static Stack<Integer> minStack = new Stack<Integer>();
	
	public static int top(){
		return stack.peek();
	}
	
	public static int pop(){
		if(stack.peek().equals(minStack.peek())){
			minStack.pop();
		}
		return stack.pop();
	}
	
	public static void push(int x){
		if(minStack.isEmpty() || x <= minStack.peek()){
			minStack.add(x);
		}
		stack.push(x);
	}
	
	public static int getMin(){
		return minStack.peek();
	}
	
	public static void main(String[] args) {
		push(-1);
		System.out.println(getMin());
		System.out.println(top());
	}
}
