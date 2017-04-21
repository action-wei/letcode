package www.xiaowei.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 * 
 * @author xiaowei
 */
public class Find132pattern {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nums[] = new int[n];
		for(int i=0;i<n;i++){
			nums[i]=sc.nextInt();
		}
		boolean ret = solution(nums);
		System.out.println(ret);
		
	}
	/**
	 * IMPLEMENTATION:
	 * 1-Have a stack, each time we store a new number, we first pop out all numbers that are smaller than that number.
	 *   The numbers that are popped out becomes candidate for s3.
	 *   
	 * 2-We keep track of the maximum of such s3 (which is always the most recently popped number from the stack).
	 * 
     * 3-Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3 implies s1 < s2.
	 * @param nums
	 * @return
	 */
	private static boolean solution(int[] nums){
		Stack<Integer> stack = new Stack<Integer>();
		int s3 = Integer.MIN_VALUE;
		for(int i=nums.length-1;i>=0;i--){
			if(nums[i]<s3) return true;
			else{
				while(!stack.isEmpty() && stack.peek()<nums[i]){
					s3 = stack.peek();
					stack.pop();
				}
				stack.push(nums[i]);
			}
		}
		return false;
	}
}
