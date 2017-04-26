package www.xiaowei.tree;
/**
 * 递归和非递归实现树的遍历
 * 
 * @author xiaowei
 */
public class SearchTree {
	public static void main(String[] args) {
		
	}
	/**
	 * 递归实现
	 * @param head
	 */
	public static void  preSearchTree(TreeNode head){
		if(head==null) return ;
		System.out.print("" + head.val + " ");
		preSearchTree(head.left);
		preSearchTree(head.right);
	}
}
