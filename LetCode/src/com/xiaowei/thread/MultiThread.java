package com.xiaowei.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadinfos = threadMXBean.dumpAllThreads(false,false);
		for(ThreadInfo info:threadinfos){
			System.out.println("["+info.getThreadId()+"]"+ info.getThreadName());
		}
	}
}
