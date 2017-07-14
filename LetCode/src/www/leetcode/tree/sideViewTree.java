package www.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 一棵二叉树，输出左侧视图中的节点
 * 解题思路：使用层次遍历的思路，取出每层最左节点组成集合，即是最终结果
 *
 * Created by zhangwei84 on 2017/7/14.
 */
public class sideViewTree {

  public static List<TreeNode> solution(TreeNode root){
    List<TreeNode> result = new ArrayList<>();
    if (null == root) {
      return result;
    }

    Queue<TreeNode> que1 = new LinkedList<TreeNode>();
    Queue<TreeNode> que2 = new LinkedList<TreeNode>();

    que1.add(root);
    while(!que1.isEmpty() || !que2.isEmpty()){
      TreeNode node = null;
      Boolean isOven = true;
      while(!que1.isEmpty() && isOven){
        node = que1.poll();
        if(que1.size()==0){
          result.add(node);
          isOven=false;
        }
        if (node.right != null) {
          que2.add(node.right);
        }
        if (node.left != null) {
          que2.add(node.left);
        }
      }

       while (!que2.isEmpty() && !isOven) {
        node = que2.poll();
        if(que2.size()==0){
          result.add(node);
          isOven = true;
        }
        if (node.right != null) {
          que1.add(node.right);
        }
        if (node.left != null) {
          que1.add(node.left);
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Integer[] array = {1, 2, 3, 4, -1, 5, 6, -1, -1, 7};
    TreeNode root = ConstructBTreeWithArray.constructBST(array);
    List<TreeNode> ret = solution(root);
    for (TreeNode node : ret) {
      System.out.print(node.val+" ");
    }
  }

}
