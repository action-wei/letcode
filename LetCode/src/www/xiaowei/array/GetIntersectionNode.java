package www.xiaowei.array;

import java.util.HashSet;
import java.util.Scanner;

/**
 * �ҳ�����������ĵ�һ�������
 * 
 * @author xiaowei
 */
public class GetIntersectionNode {
	public static void main(String[] args) {
		/* -------------������-----------------*/
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
		//��������ֵ
		ListNode.printList(l1);
		ListNode.printList(l2);
		//����
		ListNode ret = best_solution(l1,l2);
		if(ret!=null)
		System.out.println(ret.val);
	}
	
	private static ListNode best_solution(ListNode headA,ListNode headB){
		if(headA==null || headB==null) return null;
		ListNode a=headA;
		ListNode b=headB;
		//�������a��b���Ȳ�ͬ����һ��ѭ����a��b�����������ĳ��Ȳ�
		while(a!=b){
			a= a==null ? headB : a.next;
			b= b==null ? headA : b.next;
		}
		return a;
	}
	
	/**
	 * ����HashSet��¼���ʵĽڵ�----ʹ���˶���ռ�
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
