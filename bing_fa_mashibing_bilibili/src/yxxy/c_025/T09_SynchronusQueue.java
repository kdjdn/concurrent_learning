﻿package yxxy.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue { //容量为0,加入的内容必须给消费者处理，脸不存储内容
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.put("aaa"); //阻塞等待消费者消费，内容必须要消费，然后才能生产,内部调用的是transfer
		//strs.add("aaa");//报错
		System.out.println(strs.size());
	}
}
