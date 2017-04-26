package www.bupt.nowCoder;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 360������
 * ����������һ̨��������̨�������Խ���������ʽ����
 * ��1�������б������б�������N�����񣬶��ڵ�i�����񣬻�����Tiʱ�俪ʼִ�У�����1����λʱ�������ꡣ
 * ��2����ʱ���񣬻�������������ʱ�����һ����ʱ���񣬵������б�������������ȼ�Ҫ������ʱ����Ҳ����˵���������е�ʱ��Ż�ִ����ʱ����
 *  ���ڻ����Ѿ�����һ�������б�����������M����ʱ����������֪��ÿ����ʱ�����ʱ��ִ�С�
 *  Ϊ�˼��������ǿ�����Ϊ��M����ʱ�����Ƕ����޹ؼ������ǿ���ͬʱִ�еģ�����Ӱ��ġ�
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
		 
	        ArrayList<Integer> list = new ArrayList<>(); //�������ʹ�õ�ֵ
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
	 * �������ϲ���ͨ��ȫ����������
	 */
	private static void solution(){
		//�����б��е���
		int N = sc.nextInt();
		//��ʱ�����е�������
		int M = sc.nextInt();
		long maxTime = 0; //��¼���Ŀ�ʼʱ��
		//�����б�����
		PriorityQueue<Integer> taskQueue = new PriorityQueue<Integer>(N);
		for(int i=0;i<N;i++){
			int beginTime = sc.nextInt();
			taskQueue.add(beginTime);
			if(beginTime>maxTime) maxTime=beginTime;
		}
		//��ʱ��������
		PriorityQueue<Integer> interimQue = new PriorityQueue<Integer>(M);
		for(int i=0;i<M;i++){
			int beginTime = sc.nextInt();
			interimQue.add(beginTime);
			if(beginTime>maxTime) maxTime=beginTime;
		}
		//���Ȱ��������б��е�����
		int task[] = new int[M]; 
		while(!taskQueue.isEmpty()){
			int t = taskQueue.peek();
			task[t]=1;
			taskQueue.poll();
		}
		//������ʱ����
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
