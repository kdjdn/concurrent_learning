package com.roocon.thread.t2;

public class Demo3 {
	
	public static void main(String[] args) {
		
		/*new Thread() {
			public void run() {
				System.out.println("thread start ..");
			};
		}.start();*/
		
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start ..");
			}
		}).start();*/

		/**
		 * 两种方式合体，会执行sub，因为jvm会动态分派调用
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable");
			}
		}) {
			public void run() {
				System.out.println("sub");
			};
		}.start();
		
		
	}

}
