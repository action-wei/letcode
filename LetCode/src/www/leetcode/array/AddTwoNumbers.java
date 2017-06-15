package www.leetcode.array;

import java.util.Scanner;
import java.util.Stack;

/**
 * �����������Ӧ�������ӷ�����(ps:445)
 * 
 * @author leetcode
 */
public class AddTwoNumbers {
	public static void main(String[] args) {
		ListNode l1 = null;
		ListNode l2 = null;
		Scanner scan = new Scanner(System.in);
		int len1 = scan.nextInt();
		int len2 = scan.nextInt();
		if(len1>0) l1=new ListNode(scan.nextInt());
		else return;
		ListNode tmp = null;
		ListNode p1 = l1;
		for(int i=1;i<len1;i++){
			tmp = new ListNode(scan.nextInt());
			p1.next = tmp;
			p1=p1.next;
		}
		if(len2>0) l2=new ListNode(scan.nextInt());
		else return;
		ListNode p2 = l2;
		for(int i=1;i<len2;i++){
			tmp = new ListNode(scan.nextInt());
			p2.next = tmp;
			p2=p2.next;
		}
		ListNode newHead = solution(l1,l2);
		//������
		ListNode pn = newHead;
		while(pn!=null){
			System.out.print(pn.val+" ");
			pn = pn.next;
		}
		System.out.println();
	}
	
	
	private static ListNode solution(ListNode l1,ListNode l2){
		ListNode head = new ListNode(0);
		Stack<ListNode> s1 = new Stack<ListNode>();//������l1�ڵ���ջ
		Stack<ListNode> s2 = new Stack<ListNode>();//������l2�ڵ���ջ
		ListNode p1 = l1;
		ListNode p2 = l2;
		//������l1��l2��ջ
		while(p1!=null && p2!=null){
			s1.add(p1);
			s2.add(p2);
			p1=p1.next;
			p2=p2.next;
		}
		while(p1 != null){
			s1.add(p1);
			p1=p1.next;
		}
		while(p2 != null){
			s2.add(p2);
			p2=p2.next;
		}
		//���������������ʼ���
		boolean flag = false;//��¼��������Ƿ��λ
		ListNode tmp = null;
		while(!s1.empty() && !s2.empty()){
			int value = s1.peek().val+s2.peek().val;
			if(flag){
				value+=1;
				flag=false;
			}
			if(value>=10){
				value-=10;
				flag = true;
			}
			//�����½ڵ㣬��ͷ�巨����head�ڵ��
			tmp = new ListNode(value);
			tmp.next = head.next;
			head.next = tmp;
			s1.pop();
			s2.pop();
		}
		//������������������
		while(!s1.empty()){
			int value = s1.peek().val;
			if(flag){
				value+=1;
				flag=false;
			}
			if(value>=10){
				value-=10;
				flag=true;
			}
			tmp = new ListNode(value);
			tmp.next = head.next;
			head.next = tmp;
			s1.pop();
		}
		while(!s2.empty()){
			int value = s2.peek().val;
			if(flag){
				value+=1;
				flag=false;
			}
			if(value>=10){
				value-=10;
				flag=true;
			}
			tmp = new ListNode(value);
			tmp.next = head.next;
			head.next = tmp;
			s2.pop();
		}
		return head.next;
	}
}
