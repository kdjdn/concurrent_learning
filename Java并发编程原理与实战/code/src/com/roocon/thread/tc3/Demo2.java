package com.roocon.thread.tc3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 监视器规则
 */
public class Demo2 {
	
	private Lock lock = new ReentrantLock();
	
	public void a() {
		lock.lock();
		System.out.println("...");
		lock.unlock(); // 1 解锁
	}
	//1happens-before2
	public void b() {
		lock.lock(); // 2  加锁
		System.out.println("...");
		lock.unlock();
	}

}
