package www.bupt.nowCoder;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 360笔试题
 * 现在现在有一台机器，这台机器可以接收两种形式任务：
 * （1）任务列表，任务列表里面有N个任务，对于第i个任务，机器在Ti时间开始执行，并在1个单位时间内做完。
 * （2）临时任务，机器可以在任意时间接收一个临时任务，但任务列表里面的任务优先级要高于临时任务，也就是说当机器空闲的时候才会执行临时任务。
 *  现在机器已经接收一个任务列表。接下来会有M个临时任务，我们想知道每个临时任务何时被执行。
 *  为了简化问题我们可以认为这M个临时任务是独立无关即任务是可以同时执行的，互不影响的。
 * 
 * @author xiaowei
 */
public class TaskList {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		solution();
	}
	
	 public void solution2() {
			 int N = sc.nextInt();
	         int M = sc.nextInt();
	         int[] array = new int[N];
	         int[] tmpArray = new int[M];
	         for (int i = 0; i < N; i ++) {
	             array[i] = sc.nextInt();
	         }
	         for (int i  =0; i < M; i ++) {
	             tmpArray[i] = sc.nextInt();
	         }
		 
	        ArrayList<Integer> list = new ArrayList<>(); //保存可以使用的值
	        for (int i = 1; i < array[0]; i ++) {
	            list.add(i);
	        }

	        ArrayList<Integer> numberInArray = new ArrayList<>();
	        for (int val : array) {
	            numberInArray.add(val);
	        }

	        for (int i = array[0]; i < array[array.length - 1]; i ++) {
	            if (!numberInArray.contains(i)) {
	                list.add(i);
	            }
	        }
	        list.add(array[array.length - 1] + 1);

	        ArrayList<Integer> result = new ArrayList<>();

	        for (int j = 0; j < tmpArray.length; j ++) {
	            for (int i = 0; i < list.size(); i ++) {
	                if (tmpArray[j] <= list.get(i)) {
	                    result.add(list.get(i));
	                    break;
	                }
	            }
	        }
	        for (int val : result) {
	            System.out.println(val);
	        }
	    }
	
	
	/**
	 * 赛码网上不能通过全部测试用例
	 */
	private static void solution(){
		//任务列表中的数
		int N = sc.nextInt();
		//临时任务中的任务数
		int M = sc.nextInt();
		long maxTime = 0; //记录最大的开始时间
		//任务列表数组
		PriorityQueue<Integer> taskQueue = new PriorityQueue<Integer>(N);
		for(int i=0;i<N;i++){
			int beginTime = sc.nextInt();
			taskQueue.add(beginTime);
			if(beginTime>maxTime) maxTime=beginTime;
		}
		//临时任务数组
		PriorityQueue<Integer> interimQue = new PriorityQueue<Integer>(M);
		for(int i=0;i<M;i++){
			int beginTime = sc.nextInt();
			interimQue.add(beginTime);
			if(beginTime>maxTime) maxTime=beginTime;
		}
		//优先安排任务列表中的任务
		int task[] = new int[M]; 
		while(!taskQueue.isEmpty()){
			int t = taskQueue.peek();
			task[t]=1;
			taskQueue.poll();
		}
		//安排临时任务
		for(int i=1;i<=task.length;i++){
			if(task[i]==1) continue;
			while(task[i]==0 && !interimQue.isEmpty() && i>=interimQue.peek()){
				System.out.println(i);
				interimQue.poll();
			}
			if(interimQue.isEmpty()) break;
		}
	}
}
