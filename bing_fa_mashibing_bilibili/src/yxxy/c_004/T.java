/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package yxxy.c_004;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {//静态方法，没有this，锁定静态方法相当于锁定当前这个类的class对象
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？  不可以，静态方法没有this
			count --;
		}
	}

}
