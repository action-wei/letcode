package www.leetcode.nowCoder;

import www.leetcode.tree.ConstructBTreeWithArray;
import www.leetcode.tree.SearchTree;
import www.leetcode.tree.TreeNode;

/**
 * �ڶ����������Ѱ��С�ڵ��ڸ���ֵ�����ڵ�
 * 
 * @author leetcode
 */
public class FindFloorInBST {
	public static void main(String[] args) {
		Integer array[] = {15,11,21,-1,13,19,27};
		TreeNode root = ConstructBTreeWithArray.constructBST(array);
		System.out.println("print the BST nodes ");
		SearchTree.preSearchBTree(root);
		System.out.println();
		System.out.println("-----test result------");
		//����
//		TreeNode ret = solution(root,22);
		TreeNode ret = recursive_solution(root,22);
		if(ret!=null){
			System.out.println(ret.val);
		}else{
			System.out.println("the result is NULL");
		}
	}
	
	/**
	 * �ǵݹ�ⷨ
	 * @param root
	 * @param value
	 * @return
	 */
	public static TreeNode solution(TreeNode root,int value){
		if(root==null) return null;
		TreeNode flooring = null;
		TreeNode cur = root	;
		while(cur!=null	){
			if(cur.val>value){
				cur = cur.left;
			}else{
				flooring = cur;
				cur = cur.right;
			}
		}
		return flooring;
	}
	
	/**
	 * �ݹ�ⷨ
	 * @param root
	 * @param value
	 * @return
	 */
	public static TreeNode recursive_solution(TreeNode root,int value){
		if(root==null) return null;
		if(root.val > value){
			//��ǰ�ڵ��ֵ����value,�����������м�������
			return recursive_solution(root.left,value);
		}else{
			TreeNode flooring = recursive_solution(root.right,value);
			return flooring!=null ? flooring : root;
		}
	}
}
