package com.roocon.thread.ta1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 重入锁
 */
public class MyLock implements Lock {
	
	private boolean isLocked = false;
	
	private Thread lockBy = null;
	
	private int lockCount = 0;

	@Override
	public synchronized void lock() {
		// ...
		
		Thread currentThread = Thread.currentThread(); // Thread-0
		
		while (isLocked && currentThread != lockBy)//自旋+可重入(判断访问的是否是当前线程)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		isLocked = true;
		lockBy = currentThread;
		lockCount ++; // 1   2
	}
	
	@Override
	public synchronized void unlock() {
		if(lockBy == Thread.currentThread()) {
			lockCount --;  // 1  0
			
			if(lockCount == 0) {
				notify();
				isLocked = false;
			}
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
