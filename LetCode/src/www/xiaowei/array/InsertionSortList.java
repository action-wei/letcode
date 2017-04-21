package www.xiaowei.array;

import java.util.Scanner;

/**
 * sort a list using insertion sort
 * 
 * @author xiaowei
 */
public class InsertionSortList {
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
		//打印原始链表
		ListNode.printList(head);
		//测试
		ListNode ret = solution(head);
		//输出结果
		ListNode.printList(ret);
	}
	
	private static ListNode solution(ListNode head){
		if(head==null || head.next==null) return head;
		ListNode helper = new ListNode(0);
		ListNode pre = helper;
		ListNode pcur = head;
		ListNode pnext = null;
		while(pcur!=null){
			pnext = pcur.next;
			while(pre.next!=null && pre.next.val<pcur.val){
				pre=pre.next;
			}
			pcur.next=pre.next;
			pre.next=pcur;
			pre=helper;
			pcur=pnext;
		}
		return helper.next;
	}
}
