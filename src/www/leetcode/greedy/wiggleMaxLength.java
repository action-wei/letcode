package www.leetcode.greedy;

/**
 * Leetcode: 376 Wiggle Subsequence
 *
 * Created by leetcode on 2017/6/12.
 */
public class wiggleMaxLength {
    public static void main(String[] args) {
        int[] nums = {1,1};
        int ret = solution(nums);
        System.out.println(ret);
    }

    public static  int solution(int[] nums) {
        int len = nums.length;
        if(len == 0||len==1) return len;
        int pivot = nums[0];
        int k = 0;
        while(k < len && nums[k] == pivot) k++;
        if(k == len) return 1;
        boolean isSmall = pivot < nums[k];
        int count = 1;
        for(int i=k;i<len;i++) {
            if((!isSmall && pivot > nums[i]) || (isSmall && pivot < nums[i]) ){
                count++;
                isSmall = !isSmall;
            }
            pivot = nums[i];
        }
        return count;
    }
}
