package com.roocon.thread.tc4;

/**
 * 锁的释放与获取所建立的happens-before关系：
 * 程序次序规则 1happens-before2happens-before3happens-before4happens-before5happens-before6
 * 监视器规则 3happens-before4
 * 传递性  1happens-before2happens-before3happens-before4happens-before5happens-before6
 * @author worker
 *
 */
public class Demo {
	
	private int value;
	
	public synchronized void a() { // 1  获取锁
		value ++; // 2
	} // 3 释放锁
	
	public synchronized void b() { // 4 获取锁
		int a = value; // 5
		// 处理其他的操作 
	} // 6 释放锁
	

}
