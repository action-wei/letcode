package www.xiaowei.array;

import java.util.Scanner;

import www.xiaowei.tree.TreeNode;

/**
 * ��һ���������еĵ�����ת��Ϊһ���߶�ƽ��Ķ��������
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
		//plowָ���м�ڵ�
		TreeNode root = new TreeNode(pslow.val);
		root.left = constructBST(head,pslow);
		root.right = constructBST(pslow.next,end);
		return root;
	}
	
}

