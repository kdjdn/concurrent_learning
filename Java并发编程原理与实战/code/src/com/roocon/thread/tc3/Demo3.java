package com.roocon.thread.tc3;

/**
 * start����
 */
public class Demo3 {
	
	public void a  () {
		System.out.println("a"); // 1 ��������һ���̵߳��߳�
		new Thread(new Runnable() {
			//1happens-before2
			@Override
			public void run() {
				System.out.println("b"); // 2 
			}
		}).start();
	}

}

//join����
