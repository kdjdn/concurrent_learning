/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间（自己现成的变量其他线程不能访问），synchronized是使用时间换空间（不能同时访问）
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * 运行下面的程序，理解ThreadLocal
 * 
 * @author 马士兵
 */
package yxxy.c_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
	//volatile static Person p = new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());//自己线程里的变量只能自己用
		}).start(); 
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


