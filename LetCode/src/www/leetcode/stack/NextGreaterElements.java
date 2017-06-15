package www.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * ����һ�����飬����ÿ�������ҳ���һ�����������������·��Ϊ���Ӹĵ㿪ʼ�����ң�����ĩβ�����´�����ͷ����ʼ���ң����û���ҵ����򷵻�-1
 * 
 * @author leetcode
 */
public class NextGreaterElements {
	public static void main(String[] args) {
		int nums[]={1,2,1};
		int[] result = better_solution(nums);
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
	}
	/**
	 * ����ջ
	 * @param nums
	 * @return
	 */
	public static int[] better_solution(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n]; 
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return next;
    }
	
	/**
	 * ʱ�临�Ӷ�ΪO(n2)
	 * @param nums
	 * @return
	 */
	private static int[] solution(int[] nums){
		int result[] = new int[nums.length];
		for(int i=0;i<nums.length;i++){
			boolean flag = true;
			for(int j=i+1;j<nums.length;j++){
				
				if(nums[j]>nums[i]){
					result[i]=nums[j];
					flag = false;
					break;
				}
			}
			if(flag)
				for(int k=0;k<i;k++){
					if(nums[k]>nums[i]) {
						result[i]=nums[k];
						flag = false;
						break;
					}
				}
			if(flag)
				result[i]=-1;
		}
		return result;
	}
}
