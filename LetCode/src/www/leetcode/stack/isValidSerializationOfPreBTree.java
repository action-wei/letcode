package www.leetcode.stack;

import java.util.Stack;

/**
 * ��֤һ��B����ǰ������ı����Ƿ���ȷ
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
	 * �����ĳ��Ⱥ���ȽǶȿ�������
	 * 1.�����Ҷ�ӽڵ㣬ֻ��һ�����
	 * 2.����Ƿ�Ҷ�ӽڵ㣬��һ����Ⱥ��������ȣ����ڵ�ֻ����������
	 * ����outDegree�����inDegree��ֵ��1
	 * 
	 * �㷨˼·��1.����diff = outDegree - inDegree,
	 * 2.ÿ����һ���ڵ㣬diff-1;ÿ����һ����Ҷ�ӽڵ㣬diff+2
	 * 3.���������нڵ��diff��ֵӦ��Ϊ0
	 * @param preOrder
	 * @return
	 */
	private static boolean isVilidSerialization(String preOrder){
		String[] nodes = preOrder.split(",");
		int diff = 1;
		for(String s : nodes){
			if(--diff<0) return false;  //ע��˴���������--�����ж��Ƿ�С��0���Ӷ����Ա�֤���ڵ㲻��Ϊnull
			if(!s.equals("#")) diff+=2;
		}
		return diff==0;
	}
	
	/**
	 * ����ջʵ��,�㷨˼��
	 * when you iterate through the preorder traversal string, for each char:
	 * case 1: you see a number c, means you begin to expand a new tree rooted with c, you push it to stack
	 * case 2.1: you see a #, while top of stack is a number, you know this # is a left null child, put it there as a mark for next coming node k to know it is being the right child.
	 * case 2.2: you see a #, while top of stack is #, you know you meet this # as right null child, you now cancel the sub tree (rooted as t, for example) with these two-# children. 
	 * But wait, after the cancellation, you continue to check top of stack is whether # or a number:
	 * --if a number, say u, you know you just cancelled a node t which is left child of u. You need to leave a # mark to the top of stack. So that the next node know it is a right child.
	 * --if a #, you know you just cancelled a tree whose root, is the right child of u. So you continue to cancel sub tree of u, and the process goes on and on.
	 * 
	 * ��Ҫ���𣺵�������һ������ʱ������ڵ��ջ��Ȼ����ջһ��#�Ŵ������������Ȼ���жϸճ�ջ������ʱ���ڵ�p��������������������������������������ջ���������������������ж�p��������Ƿ�����꣬����ǣ����������������ջ��Ȼ����ջһ��#��ʾ�������������;
	 * ��������һ��������ʹ��һ��#�Ŵ��棬Ȼ������������һ����ȷ��������ջ�����ֻʣһ��#��
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
