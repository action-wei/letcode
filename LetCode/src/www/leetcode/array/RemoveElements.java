package www.leetcode.array;

import java.util.Scanner;

/**
 * remove all elements from list that have value VAL
 * 
 * @author leetcode
 */
public class RemoveElements {
	public static void main(String[] args) {
		/* -------------链表构造-----------------*/
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int val = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn=pn.next;
		}
		/* -------------链表构造-----------------*/
		ListNode.printList(head);
		System.out.println();
		ListNode ret = solution(head,val);
		//打印链表
		ListNode.printList(ret);
		
	}
	private static ListNode solution(ListNode head,int val){
		while(head!=null&&head.val == val){
			head=head.next;
		}
		if(head==null) return head;
		ListNode pn = head;
		ListNode pre = head;
		pn = pn.next;
		while(pn!=null){
			if(pn.val==val){
				pre.next=pn.next;
				pn = pre.next;
			}else{
				pn = pn.next;
				pre = pre.next;
			}
		}
		return head;
	}
}
