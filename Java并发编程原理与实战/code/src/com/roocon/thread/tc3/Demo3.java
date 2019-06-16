package com.roocon.thread.tc3;

/**
 * start规则
 */
public class Demo3 {
	
	public void a  () {
		System.out.println("a"); // 1 启动另外一个线程的线程
		new Thread(new Runnable() {
			//1happens-before2
			@Override
			public void run() {
				System.out.println("b"); // 2 
			}
		}).start();
	}

}

//join规则：
