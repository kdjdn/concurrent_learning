﻿/**
 * 生产者消费者
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 使用wait和notify/notifyAll来实现
 * 
 * @author mashibing
 */
package yxxy.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; //最多10个元素
	private int count = 0;
	
	
	public synchronized void put(T t) {
		while(lists.size() == MAX) { //想想为什么用while而不是用if？两个线程一块执行， 这个线程判断没满，准备写入了，另一个线程也在写入，这个线程么有判断继续向下执行写入，可能会超容量，if只判断一次，while会重新判断
			try {
				this.wait(); //effective java   wait往往和while一起使用
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		this.notifyAll(); //通知消费者线程进行消费 notify叫醒一个，有可能叫醒生产者，这样会一直等
	}
	
	public synchronized T get() {
		T t = null;
		while(lists.size() == 0) 
			try {
				this.wait();//wait大多数和while一起用
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		t = lists.removeFirst();
		count --;
		this.notifyAll(); //通知生产者进行生产
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		//启动消费者线程
		for(int i=0; i<10; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) System.out.println(c.get());
			}, "c" + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//启动生产者线程
		for(int i=0; i<2; i++) {
			new Thread(()->{
				for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
			}, "p" + i).start();
		}
	}
}
