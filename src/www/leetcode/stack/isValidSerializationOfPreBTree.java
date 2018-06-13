package www.leetcode.stack;

import java.util.Stack;

/**
 * 验证一个B树的前序遍历的编码是否正确
 * One way to serialize a binary tree is to use pre-order traversal. 
 * When we encounter a non-null node, we record the node's value. 
 * If it is a null node, we record using a sentinel value such as #.
 * 
 * @author leetcode
 */
public class isValidSerializationOfPreBTree {
	
	public static void main(String[] args) {
		String preStr = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		boolean ret = solutionWithStack(preStr);
		System.out.println(ret);
	}
	/**
	 * 从树的出度和入度角度考虑问题
	 * 1.如果是叶子节点，只有一个入度
	 * 2.如果是非叶子节点，有一个入度和两个出度，根节点只有两个出度
	 * 出度outDegree比入度inDegree的值大1
	 * 
	 * 算法思路：1.定义diff = outDegree - inDegree,
	 * 2.每遇到一个节点，diff-1;每遇到一个非叶子节点，diff+2
	 * 3.遍历完所有节点后，diff的值应该为0
	 * @param preOrder
	 * @return
	 */
	private static boolean isVilidSerialization(String preOrder){
		String[] nodes = preOrder.split(",");
		int diff = 1;
		for(String s : nodes){
			if(--diff<0) return false;  //注意此处必须是先--了再判断是否小于0！从而可以保证根节点不能为null
			if(!s.equals("#")) diff+=2;
		}
		return diff==0;
	}
	
	/**
	 * 借助栈实现,算法思想
	 * when you iterate through the preorder traversal string, for each char:
	 * case 1: you see a number c, means you begin to expand a new tree rooted with c, you push it to stack
	 * case 2.1: you see a #, while top of stack is a number, you know this # is a left null child, put it there as a mark for next coming node k to know it is being the right child.
	 * case 2.2: you see a #, while top of stack is #, you know you meet this # as right null child, you now cancel the sub tree (rooted as t, for example) with these two-# children. 
	 * But wait, after the cancellation, you continue to check top of stack is whether # or a number:
	 * --if a number, say u, you know you just cancelled a node t which is left child of u. You need to leave a # mark to the top of stack. So that the next node know it is a right child.
	 * --if a #, you know you just cancelled a tree whose root, is the right child of u. So you continue to cancel sub tree of u, and the process goes on and on.
	 * 
	 * 简要描叙：当遍历完一个子树时，将其节点出栈，然后入栈一个#号代表这个子树，然后判断刚出栈的子树时父节点p的右子树还是左子树，如果是左子树则继续入栈，如果是右子树，则继续判断p这棵子树是否遍历完，如果是，继续将这棵子树出栈，然后入栈一个#表示该子树遍历完成;
	 * 即遍历完一个子树，使用一个#号代替，然后继续，如果是一个正确的树，则栈中最后只剩一个#号
	 * 
	 */
	private static boolean solutionWithStack(String preOrder){
		Stack<Character> stack = new Stack<Character>();
		int len = preOrder.length();
		for(int i=0;i<len;i++){
			char cur = preOrder.charAt(i);
			if(cur==',') continue;
			while(cur=='#' && !stack.isEmpty() && cur==stack.peek()){
				stack.pop();
				if(stack.isEmpty()){
					return false;
				}
				stack.pop();
			}
			stack.add(cur);
		}
		return stack.size()==1 && stack.peek()=='#';
	}
	
}
