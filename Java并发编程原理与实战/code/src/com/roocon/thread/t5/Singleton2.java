package com.roocon.thread.t5;

public class Singleton2 {
	
	private Singleton2() {}
	
	private static volatile Singleton2 instance;
	
	/**
	 * 双重检查加锁，判断两次null
	 * 
	 * @return
	 */
	public static Singleton2 getInstance () {
		// 自旋   while(true)
		if(instance == null) {
			synchronized (Singleton2.class) {
				if(instance == null) {//避免两个线程进来，两个线程都判断为null，第一个释放锁后，第二个直接拿到了，就会再次创建实例
					instance = new Singleton2();  // 指令重排序，可能会先执行这行，在执行上面行，也就不会保证单例，volatile会避免指令重排序
					
					// 申请一块内存空间   // 1
					// 在这块空间里实例化对象  // 2
					// instance的引用指向这块空间地址   // 3
				}
			}
		}
		return instance;
	}
	
	// 多线程的环境下
	// 必须有共享资源
	// 对资源进行非原子性操作

	/**
	 * 两个线程都判断instance为null，就都会创建instance，就不是单例了
	 */
}
