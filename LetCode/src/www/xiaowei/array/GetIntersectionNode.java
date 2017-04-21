package www.xiaowei.array;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 找出两个单链表的第一个交叉点
 * 
 * @author xiaowei
 */
public class GetIntersectionNode {
	public static void main(String[] args) {
		/* -------------链表构造-----------------*/
		int len1 = 3;
		ListNode l1 = new ListNode(0);
		ListNode pn = l1;
		for(int i=1;i<len1;i++){
			ListNode tmp = new ListNode(i);
			pn.next = tmp;
			pn=pn.next;
		}
		ListNode l2 = new ListNode(6);
		l2.next = new ListNode(9);
		l2.next.next = l1.next;
		//输出链表的值
		ListNode.printList(l1);
		ListNode.printList(l2);
		//测试
		ListNode ret = best_solution(l1,l2);
		if(ret!=null)
		System.out.println(ret.val);
	}
	
	private static ListNode best_solution(ListNode headA,ListNode headB){
		if(headA==null || headB==null) return null;
		ListNode a=headA;
		ListNode b=headB;
		//如果链表a，b长度不同，第一轮循环后，a和b正好相距链表的长度差
		while(a!=b){
			a= a==null ? headB : a.next;
			b= b==null ? headA : b.next;
		}
		return a;
	}
	
	/**
	 * 借助HashSet记录访问的节点----使用了额外空间
	 * @param headA
	 * @param headB
	 * @return
	 */
	private static ListNode solution(ListNode headA,ListNode headB){
		HashSet<Integer> set = new HashSet<Integer>();
		ListNode p1 = headA;
		while(p1!=null){
			set.add(p1.val);
			p1 = p1.next;
		}
		ListNode p2 = headB;
		while(p2!=null){
			if(set.contains(p2.val)) return p2;
			p2=p2.next;
		}
		return null;
	}
}
