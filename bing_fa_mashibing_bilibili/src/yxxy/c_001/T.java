/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package yxxy.c_001;

public class T {
	
	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		synchronized(o) { //任何线程要执行下面的代码，必须先拿到o的锁  锁的信息记录在堆中，而不是在引用中
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

