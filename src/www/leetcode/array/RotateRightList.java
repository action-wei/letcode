package www.leetcode.array;

import java.util.Scanner;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * 
 * @author leetcode
 */
public class RotateRightList {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int k = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn= pn.next;
		}
		//²âÊÔ
		ListNode ret= solution(head,k);
		ListNode.printList(ret);
		
	}
	private static ListNode solution(ListNode head,int k){
		if(head==null || head.next==null || k==0) return head;
		int len = ListNode.getLength(head);
		k = k%len;
		ListNode dummy = new ListNode(0);
		dummy.next=head;
		head = dummy;
		for(int i=0;i<k;i++){
			head=head.next;
		}
		ListNode tail = dummy;
		while(head.next!=null){
			head=head.next;
			tail=tail.next;
		}
		head.next=dummy.next;
		dummy.next=tail.next;
		tail.next=null;
		return dummy.next;
	}
	
}
