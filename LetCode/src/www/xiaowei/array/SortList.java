package www.xiaowei.array;

import java.util.Scanner;

/**
 * ����һ������
 *
 * @author xiaowei
 */
public class SortList {
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
		//��ӡԭʼ����
		ListNode.printList(head);
		//����
		ListNode ret = sortList(head);
		//��ӡ���
		ListNode.printList(ret);
		
	}
	
	/**
	 * �ݹ�ؽ�һ�������з�Ϊ���Σ�Ȼ��ϲ���������������
	 * @param head
	 * @return
	 */
	private static ListNode sortList(ListNode head){
		if(head==null || head.next==null) return head;
		ListNode low=head,fast=head,pre=null;
		//1.cut the list into halves
		while(fast!=null && fast.next!=null){
			pre=low;
			low=low.next;
			fast=fast.next.next;
		}
		pre.next=null;
		//2. sort each half
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(low);
		//3. merge two lists l1 and l2
		return mergeSortedList(l1,l2);
	}
	
	private static ListNode mergeSortedList(ListNode la,ListNode lb){
		if(la==null || lb==null) return la==null ? lb : la;
		ListNode pa = la;
		ListNode pb = lb;
		ListNode newHead= new ListNode(-1);
		ListNode pn=newHead;
		while(pa!=null && pb!=null){
			if(pa.val>pb.val){
				pn.next = pb;
				pb=pb.next;
			}else{
				pn.next = pa;
				pa = pa.next;
			}
			pn = pn.next;
		}
		while(pa!=null){
			pn.next = pa;
			pn = pn.next;
			pa = pa.next;
		}
		while(pb!=null){
			pn.next = pb;
			pn=pn.next;
			pb = pb.next;
		}
		return newHead.next;
	}
}
