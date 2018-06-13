package www.leetcode.nowCoder;

/**
 * �����������������
 * ��̬�滮�ⷨ
 */
public class FindGreatestSumOfSubArray {
	public static void main(String[] args) {
		int nums[]={1,2,4,5,6,7,-2,3,-5};
		int ret = solve(nums);
		System.out.println(ret);
	}
	
	public static int solve(int[] nums){
		int len = nums.length;
		if(len<=0) return -1;
		//���飬��¼�м���
		int dp[] = new int[len];
		dp[0] = nums[0];
		int maxSum = nums[0];
		for(int i=1;i<len;i++){
			if(dp[i-1]<0) {
				dp[i] = nums[i]; 
			}
			if(dp[i-1]>0){
				dp[i] = dp[i-1]+nums[i];
			}
			if(dp[i]>maxSum) maxSum = dp[i];
		}
		return maxSum;
	}
}
