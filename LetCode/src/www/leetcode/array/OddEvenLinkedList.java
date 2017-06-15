package www.leetcode.array;

import java.util.Scanner;

/**
 * 给一个链表，将奇数位的节点放在一起，偶数位的节点放在一起，并让奇数位排在偶数位前面
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
		//输出
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
			//最后一个是奇数位
			pe.next=null;
		}
		//将偶数位节点的头链到奇数位尾部
		po.next = evenHead;
		return l1;
	}
}
