package www.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 借助一个队列实现的栈功能---
 * In this method, the point is that after you add one element to the queue, rotate the queue to make the tail be the head.
 * 
 * @author leetcode
 */
public class MyStack {
	private Queue<Integer> que;
	
	public MyStack(){
		que = new LinkedList<Integer>(); 
	}
	//pop operation
	public int poll(){
		return que.poll();
	}
	//push operatioon
	public void add(int value){
		que.add(value);
		//将前面的n-1个数，出队列然后重新加入到队列
		for(int i=1;i<que.size();i++){
			que.add(que.poll());
		}
	}
	//top operation
	public int peek(){
		return que.peek();
	}
	
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.add(1);
		System.out.println(stack.peek());
		stack.add(2);
		System.out.println(stack.peek());
		stack.poll();
		System.out.println(stack.peek());
	}
	
	
}
