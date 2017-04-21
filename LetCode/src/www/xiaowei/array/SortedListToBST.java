package www.xiaowei.array;

import java.util.Scanner;

import www.xiaowei.tree.TreeNode;

/**
 * 将一个升序排列的单链表转化为一个高度平衡的二叉查找树
 * 
 * @author xiaowei
 */
public class SortedListToBST {
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
		TreeNode ret = solution(head);
		System.out.println(ret.val);
		
	}
	private static TreeNode solution(ListNode head){
		if(head==null) return null;
		return constructBST(head,null);
		
	}
	
	private static TreeNode constructBST(ListNode head,ListNode end){
		if(head==end){ 
			return null;
		}
		ListNode pslow = head;
		ListNode pfast = head;
		while(pfast!=end && pfast.next!=end){
			pfast=pfast.next.next;
			pslow=pslow.next;
		}
		//plow指向中间节点
		TreeNode root = new TreeNode(pslow.val);
		root.left = constructBST(head,pslow);
		root.right = constructBST(pslow.next,end);
		return root;
	}
	
}

