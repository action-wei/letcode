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
		ListNode pre = null; //记录反转区间的前一个节点
		ListNode pnext = null;//记录反转区间后的第一个节点
		ListNode pfrom = null;//指向第一个反转节点
		ListNode pto = null; //指向最后一个反转节点
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
		//在区间(from,to)中进行反转操作
		ListNode next = null;
		while(pfrom!=pto){
			next = pfrom.next;
			pfrom.next = pnext;//将第三部分和第二部分连接起来
			pnext = pfrom;
			pfrom = next;
		}
		pto.next = pnext;
		pre.next = pto;//将第一部分和第二部分连接起来
		return newHead.next;
	}
}
