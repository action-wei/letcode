package www.xiaowei.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ʹ����������һ���������������������е�ֵΪBST��α����Ľ�����սڵ�ʹ��-1��ʾ��
 * 
 * @author xiaowei
 */
public class ConstructBTreeWithArray {
	public static void main(String[] args) {
		Integer levelOrder[] = {15,11,21,-1,13,19,27};
		//��һ������ת��һ��ArrayList
//		ArrayList<Integer> arraylist = new ArrayList<Integer>(Arrays.asList(levelOrder));
		TreeNode root = constructBST(levelOrder);
		SearchTree.preSearchTree(root);
	}
	
	
	public static TreeNode constructBST(Integer[] array){
		int size = array.length;
		if(size==0) return null;
		//������鲻Ϊ�գ�������һ�����ڵ�
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(array[0]);
		//�����ڵ���ջ
		queue.add(root);
		TreeNode cur = null;
		TreeNode tmp = null;
		for(int i=1;!queue.isEmpty() && i<size;i++){
			cur = queue.poll();
			//����������
			if(array[i]!=-1){
				tmp =new TreeNode(array[i]);
				cur.left = tmp;
				queue.add(tmp);
			}
			//����������
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
