package www.xiaowei.nowCoder;

import www.xiaowei.tree.ConstructBTreeWithArray;
import www.xiaowei.tree.SearchTree;
import www.xiaowei.tree.TreeNode;

/**
 * 在二叉搜索树中寻找最小的大于某个给定值的节点
 * 
 * @author xiaowei
 */
public class FindCeilingInBST {
	public static void main(String[] args) {
		Integer array[] = {15,11,21,-1,13,19,27};
		TreeNode root = ConstructBTreeWithArray.constructBST(array);
		System.out.println("print the BST nodes ");
		SearchTree.preSearchBTree(root);
		System.out.println();
		System.out.println("-----test result------");
		//测试
//		TreeNode ret = solution(root,28);
		TreeNode ret = recursive_solution(root,21);
		if(ret!=null){
			System.out.println(ret.val);
		}else{
			System.out.println("the result is NULL");
		}
	}
	
	/**
	 * 非递归解法
	 * @param root
	 * @param value
	 * @return
	 */
	public static TreeNode solution(TreeNode root,int value){
		if(root==null) return null;
		TreeNode ceiling = null;
		TreeNode cur = root;
		while(cur!=null){
			if(cur.val < value){
				cur = cur.right;
			}else{
				ceiling = cur;
				cur = cur.left;
			}
		}
		return ceiling;
	}
	/**
	 * 递归的解法
	 * @param root
	 * @param value
	 * @return
	 */
	public static TreeNode recursive_solution(TreeNode root,int value){
		if(root==null) return null;
		if(root.val < value){
			//当前根节点的值大于value，则在其右子树上继续搜索
			return recursive_solution(root.right,value);
		}else{
			TreeNode ceiling = recursive_solution(root.left,value);
			return ceiling!=null ? ceiling : root;
		}
	}
}
