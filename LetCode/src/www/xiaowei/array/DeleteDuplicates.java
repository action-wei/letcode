package www.xiaowei.array;

import java.util.Scanner;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * 一个有序链表,删除数值重复的所有节点,如 1->2->2->2->3 ,结果为: 1->3
 * 
 * @author xiaowei
 */
public class DeleteDuplicates {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn= pn.next;
		}
		//测试
		ListNode ret = solution(head);
		ListNode.printList(ret);
	}
	private static ListNode solution(ListNode head){
		if(head==null || head.next==null) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode pn = head;
		while(pn!=null){
			ListNode pb = pn;
			while(pb!=null && pb.val==pn.val){
				pb=pb.next;
			}
			if(pb==null) pre.next=null;
			if(pb==pn.next){
				pre.next=pn;
				pre=pre.next;
				pn=pn.next;
			}else{
				pn=pb;
			}
		}
		return dummy.next;
	}
}
