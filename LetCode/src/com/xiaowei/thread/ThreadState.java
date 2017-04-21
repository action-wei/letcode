package com.xiaowei.thread;

import java.util.concurrent.TimeUnit;

public class ThreadState {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting(),"WaitingThread").start();
		new Thread(new Blocked(),"BlockedThread-1").start();
		new Thread(new Blocked(),"BlockedThread-2").start();
	}
	
	//���̲߳��ϵؽ���˯��
	static class TimeWaiting implements Runnable{

		@Override
		public void run() {
			while(true){
				SleepUtils.second(100);
			}
		}
		
	}
	//���߳���Waiting.classʵ���ϵȴ�
	static class Waiting implements Runnable{

		@Override
		public void run() {
			while(true){
				synchronized(Waiting.class){
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	//���߳���Blocked.classʵ���ϼ����󣬲����ͷŸ���
	static class Blocked implements Runnable{

		@Override
		public void run() {
			synchronized(Blocked.class){
				while(true){
					SleepUtils.second(100);
				}
			}
		}
		
	}
}

class SleepUtils{
	public static final void second(long seconds){
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
