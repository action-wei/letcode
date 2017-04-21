package www.xiaowei.array;

import java.util.Scanner;

import www.xiaowei.tree.TreeNode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * @author xiaowei
 */
public class ReverseBetween {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int from = scan.nextInt();
		int to = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn= pn.next;
		}
		ListNode ret = solution(head,from,to);
		ListNode.printList(ret);
		
	}
	
	private static ListNode solution(ListNode head,int from,int to){
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode pre = null; //��¼��ת�����ǰһ���ڵ�
		ListNode pnext = null;//��¼��ת�����ĵ�һ���ڵ�
		ListNode pfrom = null;//ָ���һ����ת�ڵ�
		ListNode pto = null; //ָ�����һ����ת�ڵ�
		ListNode pn = newHead;
		int len = to-from;
		while(from-->0){
			pre = pn;
			pn=pn.next;
		}
		pfrom = pn;
		while(len-->0){
			pn=pn.next;
		}
		pto=pn;
		pnext = pto.next;
		//������(from,to)�н��з�ת����
		ListNode next = null;
		while(pfrom!=pto){
			next = pfrom.next;
			pfrom.next = pnext;//���������ֺ͵ڶ�������������
			pnext = pfrom;
			pfrom = next;
		}
		pto.next = pnext;
		pre.next = pto;//����һ���ֺ͵ڶ�������������
		return newHead.next;
	}
}
