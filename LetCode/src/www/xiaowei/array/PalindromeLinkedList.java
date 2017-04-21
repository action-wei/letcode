package www.xiaowei.array;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一个单链表，确定是否是回文串
 * 
 * @author xiaowei
 */
public class PalindromeLinkedList {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn=pn.next;
		}
		boolean ret=solution(head);
		System.out.println(ret);
		
	}
	
	private static boolean solution(ListNode head){
		Stack<Integer> stack = new Stack<Integer>();
		ListNode pn = head;
		while(pn!=null){
			stack.add(pn.val);
			pn = pn.next;
		}
		//遍历链表，比较正序串和倒序串是否相等
		pn = head;
		while(pn!=null){
			if(pn.val != stack.peek()) return false;
			pn=pn.next;
			stack.pop();
		}
		return true;
		
	}
}
