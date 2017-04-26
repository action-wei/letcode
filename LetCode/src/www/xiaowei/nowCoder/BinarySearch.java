package www.bupt.nowCoder;

import java.util.Scanner;

public class BinarySearch {
	public static int getPos(int[] A, int n, int val) {
     	int left = 0;
     	int right = n-1;
     	int mid = 0;
     	while(left<=right){
     		mid = (left+right)/2;
     		if(A[mid]==val){
     			int index = mid;
     			while(index>left&&A[index-1]==val){
     				index-=1;
     			}
     			return index;
     		}
     		else if(A[mid]>val){
     			right = mid-1;
     		}else{
     			left = mid+1;
     		}
     	}
     	return -1;
    }

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int nums[] = new int[5];
		for(int i=0;i<n;i++){
			nums[i] = scan.nextInt();
			}
		int val =scan.nextInt();
		int ret = getPos(nums,n,val);
		System.out.println(ret);
	}
}
