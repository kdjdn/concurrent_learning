﻿/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package yxxy.c_003;

public class T {

	private int count = 10;
	
	public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)，锁定当前对象，代码结束释放对象
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

}
