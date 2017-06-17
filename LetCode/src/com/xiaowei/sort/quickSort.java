package com.xiaowei.sort;

/**
 * 快排算法v1
 * Created by xiaowei on 2017/6/14.
 */
public class quickSort {
    public static <T extends Comparable<T>> void sort(T[] nums,int begin,int end) {
        //健壮性校验
        if(null == nums || nums.length==0 || nums.length==1) return;
        if(begin<0 || end<=0 || begin>=end || end>nums.length-1) return;
        int len = nums.length;
        //将第一位数据定义为基准
        T pivot = nums[begin];
        int left = begin, right = end;
        while (left < right) {
            while(left < right  && nums[right].compareTo(pivot)>=0 )right--;
            if(left == right) break;
            //交换
            T tem = nums[left];
            nums[left] = nums[right];
            nums[right] = tem;
            while(left<right && nums[left].compareTo(pivot)<=0) left++;
            if(left == right) break;
            tem = nums[right];
            nums[right] = nums[left];
            nums[left] = tem;
        }
        nums[left] = pivot;
        //迭代处理[0,left),(left,len-1]
        if(left==end){
            sort(nums, begin, left - 1);
        } else if (left == begin) {
            sort(nums, left + 1, end);
        }else{
            sort(nums, begin, left - 1);
            sort(nums, left + 1, end);
        }
    }

    /**
     * 快排方法二
     * @param num
     * @param begin
     * @param end
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort2(T num[], int begin, int end) {
        if(num==null || end<0 || begin<0 || begin>end || end==begin) return;
        //取最后一位数作为基准
        T pivot = num[end];
        int n = begin; //n标记比基准pivot值小的下标
        int i= n;
        while (i < end) {
            if (pivot.compareTo(num[i])>0) {
                if(i!=n){
                    swap(num,i,n);
                }
                n++;
            }
            i++;
        }
        //将基准数归位
        swap(num,n,end);
        //迭代处理[begin,i) ,(i,end]
        if(n==end){
            sort2(num,begin,n-1);
        } else if (n == begin) {
            sort2(num,n+1,end);
        }else{
            sort2(num,begin,n-1);
            sort2(num, n+1, end);
        }
    }

    /**
     * 交换两个值
     * @param num
     * @param i
     * @param t
     * @param <T>
     */
    private static <T> void swap(T num[], int i, int t) {
        T tem = num[i];
        num[i] = num[t];
        num[t] = tem;
    }



    public static <T extends Comparable<T>> void  print(T[] nums) {
        if(null == nums || nums.length==1) return;
        int len = nums.length, i = 0;
        while (i < len) {
            System.out.println(nums[i++]+" ");
        }
    }

    public static void main(String[] args) {
        Integer nums[] = {8, 2, 1, 4};
//        System.out.println("test sort function");
//        sort(nums,0,nums.length-1);
        System.out.println("test function sort2:");
        sort2(nums,0,nums.length-1);
        print(nums);
    }
}
