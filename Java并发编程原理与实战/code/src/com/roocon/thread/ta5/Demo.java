package com.roocon.thread.ta5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.imageio.spi.IIOServiceProvider;

/**
 * 锁降级
 */
public class Demo {

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	private volatile boolean isUpdate = false;//判断是否要写

	public void readWrite() {
		r.lock(); // 为了保证isUpdate能够拿到最新的值，加读锁，如果当前有写锁就要等待写锁释放才能拿到锁，这样就是最新的
		if (isUpdate) {
			r.unlock();//释放读锁才能进行写
			w.lock();
			map.put("xxx", "xxx");
			r.lock();//写完后防止其他写线程进来，读和写是互斥的，写锁没有释放时获取读锁，降级
			w.unlock();
		}

		Object obj = map.get("xxx");

		System.out.println(obj);
		r.unlock();

	}

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " 读操作在执行..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " 读操执行完毕..");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " 写操作在执行..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " 写操作执行完毕..");
		}
	}

}
