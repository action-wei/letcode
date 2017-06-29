package com.xiaowei.sort;

/**
 * ������
 * Created by xiaowei on 2017/6/15.
 */
public class heapSort {
    /**
     *  ������
     * @param nums
     * @param i
     * @param len
     */
    public void adjustHeap(int nums[],int i,int len){
        int child_index = i*2+1;
        int currentP = nums[i];
        while(child_index < len){
            if(child_index+1<len && nums[child_index]<nums[child_index+1]){
                child_index++;
            }
            if (currentP < nums[child_index]) {
                nums[i] = nums[child_index];
                i = child_index;
                child_index = child_index*2+1;
            }else{
                break;
            }
        }
        nums[i] = currentP;
    }

    /**
     * ����
     * @param nums
     */
    public void sort(int nums[],int len) {
        //������
        for(int i=(len-1)/2;i>=0;i--) {
            adjustHeap(nums, i,len);
            print(nums);
        }
        //ÿ��ȡ��һ��������������ŵ�δ������������һλ
        for(int i=len-1;i>0;i--) {
            int tem = nums[i];
            nums[i] = nums[0];
            nums[0] = tem;
            adjustHeap(nums,0,i);
        }
    }

    public static void print(int nums[]) {
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
