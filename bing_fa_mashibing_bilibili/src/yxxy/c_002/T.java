﻿/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package yxxy.c_002;

public class T {
	
	private int count = 10;
	
	public void m() {
		synchronized(this) { //任何线程要执行下面的代码，必须先拿到this的锁  synchronized锁定的是一个对象（当前对象），而不是代码块，锁定自身
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

