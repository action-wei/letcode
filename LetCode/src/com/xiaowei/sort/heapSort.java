package com.xiaowei.sort;

/**
 * 堆排序
 * Created by xiaowei on 2017/6/15.
 */
public class heapSort {
    /**
     *  调整堆
     * @param nums
     * @param i
     * @param len
     */
    public void adjustHeap(int nums[],int i,int len){
        int child_index = i*2+1;
        int currentP = nums[i];
        while(child_index < len){
            //如果存在孩子节点，则找出值较大的孩子节点
            if(child_index+1<len && nums[child_index]<nums[child_index+1]){
                child_index++;
            }
            if (currentP < nums[child_index]) {
                nums[i] = nums[child_index];
                i = child_index;
                child_index = child_index*2+1;
            }else{
                //当前待调整节点大于其孩子节点，则直接退出
                break;
            }
        }
        nums[i] = currentP;
    }

    /**
     * 堆排序
     * @param nums
     */
    public void sort(int nums[],int len) {
        //构建堆
        for(int i=(len-1)/2;i>=0;i--) {
            adjustHeap(nums, i,len);
            print(nums);
        }
        //排序过程
        for(int i=len-1;i>0;i--) {
            //交换第一位和最后一位
            int tem = nums[i];
            nums[i] = nums[0];
            nums[0] = tem;
            adjustHeap(nums,0,i);
        }
    }

    public static void print(int nums[]) {
        //打印结果
        for(int i=0;i<nums.length;i++) {
            System.out.print(nums[i]+ " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int nums[] = {0,4,5,8,1,9,6};
        print(nums);
        System.out.println("\n");
        heapSort heap = new heapSort();
        heap.sort(nums,7);
        System.out.println("");
        print(nums);
    }
}
