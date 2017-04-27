package www.xiaowei.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import www.xiaowei.tree.ConstructBTreeWithArray;
import www.xiaowei.tree.TreeNode;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * 
 * @author xiaowei
 */
public class ZigzagLevelOrder {
	public static void main(String[] args) {
		Integer array[] = {0,2,4,1,-1,3,10,5,1,-1,6,-1,8};
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
		
		return result;
	}
}
