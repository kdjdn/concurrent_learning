/**
 * 线程安全的单例模式
 * 
 * 阅读文章ttp://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 
 * 更好的是使用下面的方式，既不用加锁，也能实现懒加锁
 * 
 * @author 椹＋鍏�
 */
package yxxy.c_023;

import java.util.Arrays;

public class Singleton {
	
	private Singleton() {
		System.out.println("single");
	}
	
	private static class Inner {
		private static Singleton s = new Singleton();
	}
	
	public static Singleton getSingle() {
		return Inner.s;//多个线程拿到的是同一个对象，返回内部类的静态对象
	}
	
	public static void main(String[] args) {
		Thread[] ths = new Thread[200];
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				System.out.println(Singleton.getSingle());
			});
		}
		
		Arrays.asList(ths).forEach(o->o.start());
	}
	
}
