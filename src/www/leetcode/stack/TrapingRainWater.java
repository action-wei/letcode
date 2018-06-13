package www.leetcode.stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * @author leetcode
 */
public class TrapingRainWater {
	public static void main(String[] args) {
		int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};
		int ret = trap(height);
		System.out.println(ret);
	}
	public static int trap(int[] height){
		int result = 0;
		int len = height.length;
		int left=0,right=len-1;
		int maxLeft=0,maxRight=0;
		while(left<=right){
			if(height[left]<height[right]){
				//如果当前左边的值小于右边的值
				if(height[left]>=maxLeft) maxLeft=height[left];
				else result+=maxLeft-height[left];
				left++;
			}else{
				//如果当前右边值小于左边值
				if(height[right]>=maxRight) maxRight=height[right];
				else result+=maxRight-height[right];
				right--;
			}
		}
		return result;
	}

}
