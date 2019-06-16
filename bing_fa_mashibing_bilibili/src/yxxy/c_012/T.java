/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * 
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * 
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值,线程之间这个值可见
 * 
 * java内存模型jmm里有个主内存，每个线程有块自己的内存和缓存区，线程执行时会把主内存的running拷贝到自己的缓存区，cpu一直读取缓存的running
 * 另一个cpu在执行主线程，把running读到自己的缓存区，再把running改为false，发现已经改了再写回到主内存，主内存running此时是false，第一个线程也没有发现主内存的running变为false而一直运行
 * 加了volatile后，发现变量的值变了会通知其它线程变量以过期，其他线程重新从主内存里读取这个变量，这叫线程之间可见，一个线程改了通知另一个线程，可用于线程间通信
 * cpu并不一定只读缓存区，在不忙的时候还是会读主内存，volatile效率比synchronize高得多，能用volatile就用
 * 
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * 
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized，synchronized即保证可见性有保证原子性，volatile只有可见性
 * @author mashibing
 */
package yxxy.c_012;

import java.util.concurrent.TimeUnit;

public class T {
	/*volatile*/ boolean running = true; //对比一下有无volatile的情况下，整个程序运行结果的区别
	void m() {
		System.out.println("m start");
		while(running) {
			/*
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		T t = new T();
		
		new Thread(t::m, "t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.running = false;
		
		
	}
	
}


