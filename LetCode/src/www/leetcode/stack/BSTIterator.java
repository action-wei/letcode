package www.leetcode.stack;

import java.util.Stack;

import www.leetcode.tree.ConstructBTreeWithArray;
import www.leetcode.tree.SearchTree;
import www.leetcode.tree.TreeNode;

/**
 * Implement an iterator over a binary search tree (BST). 
 * Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * 
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * @author leetcode
 */
public class BSTIterator {
	
	/*** -------------------方案1 next()和hashNext()时间复杂度为O(1),空间复杂度为O(n)---------***/
	//使用队列，将中序遍历的结果入队列，则队列从头到尾，值依次增大，不过空间复杂度为O(n)
/*	private Queue<TreeNode> queue = new LinkedList<TreeNode>();
	
    public BSTIterator(TreeNode root) {
        if(root==null) return ;
        MidSearchBTree(root);
    }
    *//**
     * 中序遍历
     * @param root
     *//*
    private void MidSearchBTree(TreeNode root){
    	if(root==null) return ;
		MidSearchBTree(root.left);
		queue.add(root);
		MidSearchBTree(root.right);
    }

    *//** @return whether we have a next smallest number *//*
    public boolean hasNext() {
    	return !queue.isEmpty();
    }

    *//** @return the next smallest number *//*
    public int next() {
        return queue.poll().val;
    }
*/
    /**--------------------------------------方案1 ---------------------------------***/
	
	/****-------------------------------------方案2 满足条件---------------------------***/
	
	Stack<TreeNode> stack = new Stack<TreeNode>();
	
	public BSTIterator(TreeNode root){
		if(root==null) return ;
		TreeNode cur = root;
		while(cur!=null){
			stack.add(cur);
			cur = cur.left;
		}
	}
	
	public boolean hasNext(){
		return !stack.isEmpty();
	}
	
	public int next(){
		TreeNode node = stack.pop();
		TreeNode cur = node;
		if(cur.right==null) return node.val;
		else{
			cur = cur.right;
			while(cur!=null){
				stack.add(cur);
				cur=cur.left;
			}
			return node.val;
		}
	}
	
    
    //测试
    public static void main(String[] args) {
    	Integer array[] = {15,11,21,-1,13,19,27};
		TreeNode root = ConstructBTreeWithArray.constructBST(array);
		System.out.println("print the BST nodes ");
		SearchTree.preSearchBTree(root);
		System.out.println();
		System.out.println("-----test result------");
		//测试
		BSTIterator bt = new BSTIterator(root);
		while(bt.hasNext()){
			int ret = bt.next();
			System.out.print(ret+" ");
		}
		System.out.println();
	}
}
