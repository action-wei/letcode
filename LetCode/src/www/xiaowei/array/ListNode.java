package www.xiaowei.array;

/**
 * 链表节点类定义
 * 
 * @author xiaowei
 */
public class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
		next = null;
	}
	
	public static void printList(ListNode head){
		ListNode pn = head;
		while(pn != null){
			System.out.print(pn.val+"-->");
			pn = pn.next;
		}
		System.out.println();
	}
	
	public static int getLength(ListNode head){
		ListNode ptr = head;
		int len =0;
		while(ptr!=null){
			ptr=ptr.next;
			len++;
		}
		return len;
	}
	
}