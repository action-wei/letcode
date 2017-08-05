package com.xiaowei.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import www.leetcode.tree.ConstructBTreeWithArray;
import www.leetcode.tree.TreeNode;

/**
 * 二叉搜索树的第k个节点
 * Created by zhangwei84 on 2017/8/4.
 */
public class KthNode {

  public static TreeNode solution(TreeNode root, int k) {
    if (root == null || k < 0) {
      return null;
    }
    //使用中序遍历
    TreeNode knode = midSearchTree(root, k);
    return knode;
    //    List<TreeNode> list = inOrderBtree(root);
//    return list.size() < k ? null : list.get(k-1);
  }

  //非递归中序遍历
  public static TreeNode midSearchTree(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null) {
      stack.add(cur);
      cur = cur.left;
      while (cur == null && !stack.isEmpty()) {
        TreeNode tmp = stack.pop();
        k--;
        if (k == 0) {
          return tmp;
        }
        if (tmp.right != null) {
          cur = tmp.right;
        }
      }
    }
    return null;
  }

  //中序遍历所有结果
  public static List<TreeNode> inOrderBtree(TreeNode root) {
    List<TreeNode> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;
    while (cur != null) {
      stack.add(cur);
      cur = cur.left;
      while (cur == null && !stack.isEmpty()) {
        TreeNode tmp = stack.pop();
        result.add(tmp);
        if (tmp.right != null) {
          cur = tmp.right;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    TreeNode root = ConstructBTreeWithArray.constructBST(new Integer[]{8, 6, 10, 5, 7, 9, 11});
    TreeNode node = solution(root, 1);
    if (node != null) {
      System.out.println(node.val);
    }
  }


}
