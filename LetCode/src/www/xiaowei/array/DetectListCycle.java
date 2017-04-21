package www.xiaowei.array;
/**
 * Given a linked list, return the node where the cycle begins. 
 * �ж�һ�������Ƿ��л�������У��ҳ��������
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
				//����л�
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
