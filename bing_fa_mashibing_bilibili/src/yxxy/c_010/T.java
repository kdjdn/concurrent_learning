/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法，不会造成死锁，遇到异常会释放锁
 * @author mashibing
 */
package yxxy.c_010;

import java.util.concurrent.TimeUnit;

public class T {
	synchronized void m() {
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}
	
	public static void main(String[] args) {
		new TT().m();//创建子类对象之前创建父类对象
	}
	
}

class TT extends T {
	@Override
	synchronized void m() {//重写
		System.out.println("child m start");
		super.m();//子类的同步方法调用父类的同步方法
		System.out.println("child m end");
	}
}
