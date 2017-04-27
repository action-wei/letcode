package www.xiaowei.nowCoder;

import www.xiaowei.tree.ConstructBTreeWithArray;
import www.xiaowei.tree.SearchTree;
import www.xiaowei.tree.TreeNode;

/**
 * �ڶ�����������Ѱ����С�Ĵ���ĳ������ֵ�Ľڵ�
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
		//����
//		TreeNode ret = solution(root,28);
		TreeNode ret = recursive_solution(root,21);
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
	 * �ݹ�Ľⷨ
	 * @param root
	 * @param value
	 * @return
	 */
	public static TreeNode recursive_solution(TreeNode root,int value){
		if(root==null) return null;
		if(root.val < value){
			//��ǰ���ڵ��ֵ����value���������������ϼ�������
			return recursive_solution(root.right,value);
		}else{
			TreeNode ceiling = recursive_solution(root.left,value);
			return ceiling!=null ? ceiling : root;
		}
	}
}
