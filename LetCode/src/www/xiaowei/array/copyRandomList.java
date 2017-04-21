package www.xiaowei.array;
/**
 * 有一个复杂链表，链表中含有指针随机指向某个节点或者指向null
 * 复制该链表
 * 
 * @author xiaowei
 */
public class copyRandomList {
	public static void main(String[] args) {
		
	}
	/**
	 * The algorithm is composed of the follow three steps which are also 3 iteration rounds.
	 * 1.Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
     * 2.Iterate the new list and assign the random pointer for each duplicated node.
	 * 3.Restore the original list and extract the duplicated nodes.
	 */
	private static RandomListNode solution(RandomListNode head){
		if(head==null) return head;
		RandomListNode copyHead = null;
		RandomListNode ptr = head;
		RandomListNode tmp = null;
		//step 1:copy each node
		while(ptr!=null){
			tmp = new RandomListNode(ptr.val);
			tmp.next = ptr.next;
			ptr.next = tmp;
			ptr = ptr.next.next;
		}
		//step 2:assign the random pointer
		ptr = head;
		while(ptr!=null){
			if(ptr.random!=null){
				ptr.next.random = ptr.random.next;
			}
			ptr = ptr.next.next;
		}
		//step 3:restore the original list and extract the duplicated nodes
		ptr = head;
		copyHead = ptr.next;
		RandomListNode pc = copyHead;
		while(ptr!=null){
			ptr.next = pc.next;
			ptr = ptr.next;
			if(ptr!=null){
				pc.next = ptr.next;
				pc = pc.next;
			}else{
				//原始链表到达尾部，则需要将复制链表的尾部置空
				pc.next = null;
			}
		}
		return copyHead;
	}
	
}
 class RandomListNode{
	 int val;
	 RandomListNode next,random;
	 RandomListNode(int value){
		 this.val = value;
		 this.next=null;
		 this.random=null;
	 }
 }
