package www.xiaowei.array;
/**
 * Given a linked list, return the node where the cycle begins. 
 * 判断一个链表是否有环，如果有，找出环的入口
 * 
 * @author xiaowei
 */
public class DetectListCycle {
	
	private static ListNode solution(ListNode head){
		if(head==null || head.next==null)return null;
		ListNode slow=head;
		ListNode fast=head;
		while(slow!=null && fast!=null){
			slow=slow.next;
			if(fast.next==null) return null;
			fast=fast.next.next;
			if(fast==slow){
				//如果有环
				break;
			}else return null;
		}
		slow = head;
		while(slow!=fast){
			slow=slow.next;
			fast=fast.next;
		}
		return fast;
	}
}
