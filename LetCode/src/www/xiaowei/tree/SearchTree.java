package www.xiaowei.tree;
/**
 * �ݹ�ͷǵݹ�ʵ�����ı���
 * 
 * @author xiaowei
 */
public class SearchTree {
	public static void main(String[] args) {
		
	}
	/**
	 * �ݹ�ʵ��
	 * @param head
	 */
	public static void  preSearchTree(TreeNode head){
		if(head==null) return ;
		System.out.print("" + head.val + " ");
		preSearchTree(head.left);
		preSearchTree(head.right);
	}
}
