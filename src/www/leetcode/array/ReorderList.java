package www.leetcode.array;

import java.util.Scanner;
import java.util.Stack;

/**
 * 重排序链表
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * @author leetcode
 */
public class ReorderList {
	public static void main(String[] args) {
		/* -------------链表构造-----------------*/
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		ListNode head = new ListNode(scan.nextInt());
		ListNode pn = head;
		for(int i=1;i<len;i++){
			ListNode tmp = new ListNode(scan.nextInt());
			pn.next = tmp;
			pn=pn.next;
		}
		/* -------------链表构造-----------------*/
		ListNode.printList(head);
		//测试
		better_solution(head);
		//打印
		ListNode.printList(head);
	}
	
	private static void better_solution(ListNode head){
		 if(head==null||head.next==null) return;
         
         //Find the middle of the list
         ListNode p1=head;
         ListNode p2=head;
         while(p2.next!=null&&p2.next.next!=null){ 
             p1=p1.next;
             p2=p2.next.next;
         }
         
         //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
         ListNode preMiddle=p1;
         ListNode preCurrent=p1.next;
         while(preCurrent.next!=null){
             ListNode current=preCurrent.next;
             preCurrent.next=current.next;
             current.next=preMiddle.next;
             preMiddle.next=current;
         }
         
         //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
         p1=head;
         p2=preMiddle.next;
         while(p1!=preMiddle){
             preMiddle.next=p2.next;
             p2.next=p1.next;
             p1.next=p2;
             p1=p2.next;
             p2=preMiddle.next;
         }
	}
	
	/**
	 * 借助栈实现---使用了额外空间O(n),可以优化成使用O(n/2)
	 * @param head
	 * @return
	 */
	private static void solution(ListNode head){
		if(head==null || head.next==null || head.next.next==null) return;
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode slow = head;
		while(slow!=null ){
			stack.add(slow);
			slow=slow.next;
		}
		int len=stack.size();
		slow = head;
		ListNode tmp = null;
		while(stack.size()>len/2+1){
			tmp = stack.peek();
			tmp.next=slow.next;
			slow.next=tmp;
			slow = slow.next.next;
			stack.pop();
		}
		if(len%2==0){
			slow.next.next=null;
		}else{
			slow.next=null;
		}
	}
}
