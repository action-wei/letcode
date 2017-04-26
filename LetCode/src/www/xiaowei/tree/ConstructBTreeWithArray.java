package www.xiaowei.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用数组生成一个二叉搜索树，该数组中的值为BST层次遍历的结果（空节点使用-1表示）
 * 
 * @author xiaowei
 */
public class ConstructBTreeWithArray {
	public static void main(String[] args) {
		Integer levelOrder[] = {15,11,21,-1,13,19,27};
		//将一个数组转成一个ArrayList
//		ArrayList<Integer> arraylist = new ArrayList<Integer>(Arrays.asList(levelOrder));
		TreeNode root = constructBST(levelOrder);
		SearchTree.preSearchTree(root);
	}
	
	
	public static TreeNode constructBST(Integer[] array){
		int size = array.length;
		if(size==0) return null;
		//如果数组不为空，先生成一个根节点
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(array[0]);
		//将根节点入栈
		queue.add(root);
		TreeNode cur = null;
		TreeNode tmp = null;
		for(int i=1;!queue.isEmpty() && i<size;i++){
			cur = queue.poll();
			//构造左子树
			if(array[i]!=-1){
				tmp =new TreeNode(array[i]);
				cur.left = tmp;
				queue.add(tmp);
			}
			//构造右子树
			i++;
			if(i<size && array[i]!=-1){
				tmp = new TreeNode(array[i]);
				cur.right = tmp;
				queue.add(tmp);
			}
		}
		return root;
	}
}
