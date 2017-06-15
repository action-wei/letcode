package www.leetcode.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import www.leetcode.tree.ConstructBTreeWithArray;
import www.leetcode.tree.TreeNode;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * 
 * @author leetcode
 */
public class ZigzagLevelOrder {
	public static void main(String[] args) {
		Integer array[] = {3,9,20,-1,-1,15,7};
		TreeNode root = ConstructBTreeWithArray.constructBST(array);
		System.out.println("print the BST nodes ");
		List<List<Integer>> ret = solution(root);
		for(List<Integer> list: ret){
			for(Integer i: list){
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	
	public static List<List<Integer>> solution(TreeNode root){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode cur = root;
		if(cur!=null) queue.add(cur);
		boolean isEven = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			List<Integer> list = new LinkedList<Integer>();
			while(size-->0){
				TreeNode tmp = queue.poll();
				if(tmp.left!=null) queue.add(tmp.left);
				if(tmp.right!=null) queue.add(tmp.right);
				if(isEven){
					list.add(tmp.val);
				}else{
					list.add(0,tmp.val);
				}
			}
			result.add(list);
			isEven = isEven?false:true;
		}
		return result;
	}
}
