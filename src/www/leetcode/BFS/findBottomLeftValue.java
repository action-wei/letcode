package www.leetcode.BFS;

import java.util.LinkedList;
import java.util.Queue;
import www.leetcode.tree.ConstructBTreeWithArray;
import www.leetcode.tree.TreeNode;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Created by zhangwei84 on 2017/7/14.
 */
public class findBottomLeftValue {

  public static int solution(TreeNode root) {
    if (null == root) {
      return -1;
    }

    int ret = -1;
    //使用层次遍历的方式
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    while(!queue.isEmpty()){
      TreeNode node = queue.poll();
      if (queue.isEmpty()) {
        ret = node.val;
      }
      if (node.right != null) {
        queue.add(node.right);
      }
      if (node.left != null) {
        queue.add(node.left);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    Integer[] array = {1, 2, 3, 4, -1, 5, 6, -1, -1, 7};
    TreeNode root = ConstructBTreeWithArray.constructBST(array);
    int ret = solution(root);
    System.out.println(ret);
  }

}
