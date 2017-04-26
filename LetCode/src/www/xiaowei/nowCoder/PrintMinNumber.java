package www.xiaowei.nowCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * �������ų���С�������������
 * ��Ҫ�Ż�--���Ǵ������⣨long��ʾ���˵�������
 */
public class PrintMinNumber {
	public static void main(String[] args) {
		int nums[] = {321,32,3};
		Long ret = solve(nums);
		System.out.println();
		
		System.out.println(ret);
	}

	private static Long solve(int[] nums) {
		List<Long> list = new ArrayList<Long>();
		 permutation(nums,0,list);
		 Long MinSum = Long.MAX_VALUE;
		 for(int i=0;i<list.size();i++){
			 System.out.print(list.get(i));
			 System.out.print(" ");
			 if(list.get(i)<MinSum) MinSum = list.get(i);
		 }
		return MinSum;
	}

	private static void permutation(int[] nums, int begin,List<Long> list) {
		
		int len = nums.length;
		if(begin==len-1) connectArray(nums,list) ;
		for(int i=begin;i<nums.length;i++){
			int temp = nums[i];
			nums[i] = nums[begin];
			nums[begin] = temp;
			
			permutation(nums,begin+1,list);
			
			temp = nums[i];
			nums[i] = nums[begin];
			nums[begin] = temp;
		}
	}

	private static void connectArray(int[] nums,List<Long> list) {
		String str = "";
		for(int i=0;i<nums.length;i++){
			str+=nums[i];
		}
		list.add(Long.valueOf(str));
	}
}
