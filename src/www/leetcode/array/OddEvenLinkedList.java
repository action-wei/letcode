package www.leetcode.array;

import java.util.Scanner;

/**
 * ��һ������������λ�Ľڵ����һ��ż��λ�Ľڵ����һ�𣬲�������λ����ż��λǰ��
 * 
 * @author leetcode
 */
public class OddEvenLinkedList {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		ListNode l1 = new ListNode(scan.nextInt());
		ListNode pn = l1;
		for(int i=1;i<len;i++){
			ListNode tmp= new ListNode(scan.nextInt());
			pn.next=tmp;
			pn = pn.next;
		}
		ListNode head = solution(l1);
		//���
		pn = head;
		while(pn!=null){
			System.out.print(pn.val+"->");
			pn=pn.next;
		}
	}
	
	private static ListNode solution(ListNode l1){
		if(l1==null || l1.next==null){
			return l1;
		}
		ListNode po = l1;
		ListNode evenHead = l1.next;
		ListNode pe = evenHead;
		boolean isOdd = true;
		while(po.next!=null && pe.next!=null){
			if(isOdd){
				po.next=pe.next;
				isOdd = false;
				po = po.next;
			}
			else{
				pe.next = po.next;
				isOdd = true;
				pe=pe.next;
			}
		}
		if(!isOdd){
			//���һ��������λ
			pe.next=null;
		}
		//��ż��λ�ڵ��ͷ��������λβ��
		po.next = evenHead;
		return l1;
	}
}
