package www.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * �ݹ�ͷǵݹ�ʵ�����ı���
 * 
 * @author leetcode
 */
public class SearchTree {
	public static void main(String[] args) {
		Integer array[] = {15,11,21,-1,13,19,27};
		TreeNode root = ConstructBTreeWithArray.constructBST(array);
//		List<Integer> list = postOrderBTree(root);
//		List<Integer> list = preOrderBTree(root);
		List<Integer> list = inOrderBtree(root);
		System.out.println("print the list data");
		for(Integer i : list){
			System.out.print(i+" ");
		}
	}
	/**
	 * �ݹ�ʵ��  ǰ������������
	 * @param head
	 */
	public static void  preSearchBTree(TreeNode head){
		if(head==null) return ;
		System.out.print("" + head.val + " ");
		preSearchBTree(head.left);
		preSearchBTree(head.right);
	}
	
	/**
	 * �ݹ�ʵ��  �������������
	 */
	public static void midSearchBTree(TreeNode head,List<Integer> list){
		if(head==null) return ;
		midSearchBTree(head.left,list);
		System.out.print(""+head.val+" ");
		list.add(head.val);
		midSearchBTree(head.right,list);
	}
	
	/**
	 * �ݹ�ʵ��  �������������
	 */
	public static void postSearchBTree(TreeNode head){
		if(head==null) return ;
		postSearchBTree(head.left);
		postSearchBTree(head.right);
		System.out.print(""+head.val+" ");
	}
	
	/***--------------------------����Ϊ�ǵݹ�ʵ�ֵĶ���������-------------------***/
	
	/**
	 * �������������
	 * @param root
	 */
	public static List<Integer> postOrderBTree(TreeNode root){
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode lastVisit = null;
		TreeNode cur = root;
		while(cur!=null){
			stack.add(cur);
			cur=cur.left;
			while(cur==null && !stack.isEmpty()){
				TreeNode tmp = stack.pop();
				if(tmp.right==null || lastVisit==tmp.right){
					//������Ϊ�ջ����Ѿ����ʹ�������
					lastVisit = tmp;
					result.add(tmp.val);
				}else{
					//������������û�з��ʹ����򽫸��ڵ��ٴ���ջ
					stack.add(tmp);
					cur=tmp.right;
				}
			}
		}
		return result;
	}
	/**
	 * �������������
	 * @param root
	 * @return
	 */
	public static List<Integer> inOrderBtree(TreeNode root){
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur=root;
		while(cur!=null){
			stack.add(cur);
			cur=cur.left;
			while(cur==null && !stack.isEmpty()){
				TreeNode tmp=stack.pop();
				result.add(tmp.val);
				if(tmp.right!=null){
					cur=tmp.right;
				}
			}
		}
		return result;
	}
	
	/**
	 * ǰ�����������
	 * @param root
	 * @return
	 */
	public static List<Integer> preOrderBTree(TreeNode root){
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while(cur!=null){
			result.add(cur.val);
			stack.add(cur);
			cur = cur.left;
			while(cur==null && !stack.isEmpty()){
				TreeNode tmp = stack.pop();
				if(tmp.right!=null){
					cur=tmp.right;
				}
			}
		}
		return result;
	}
}
