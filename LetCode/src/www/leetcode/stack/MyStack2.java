package www.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ��������ջʵ�ֵĶ���
 * ʵ�ַ�����push the data into the empty queue, and then push all of the elements of the other queue into the stack. 
 * After that,the newest data is in the top;
 * @author leetcode
 */
public class MyStack2<T> {
	private Queue<T> que1 ;
	private Queue<T> que2 ;
	public MyStack2(){
		que1 = new LinkedList<T>();
		que2 = new LinkedList<T>();
	}
	
	public T peek(){
		return que1.isEmpty()? que2.peek() : que1.peek();
	}
	
	public T poll(){
		return que1.isEmpty() ? que2.poll() : que2.poll();
	}
	
	public void add(T value){
		if(que1.isEmpty()){
			//����que1Ϊ�յ����
			que1.add(value);
			while(!que2.isEmpty()){
				que1.add(que2.poll());
			}
		}else{
			//����que2Ϊ�յ����
			que2.add(value);
			while(que1.isEmpty()){
				que2.add(que1.poll());
			}
		}
	}
	
	public static void main(String[] args) {
		MyStack2<Integer> stack = new MyStack2<Integer>();
		Integer ret = stack.peek();
		if(ret!=null){
			System.out.println(ret);
		}else{
			System.out.println("the stack is empty");
		}
		stack.add(1);
		stack.add(2);
		stack.add(3);
		System.out.println(stack.peek());
	}	
}
