/**
 * 对比上面一个小程序，分析一下这个程序的输出
 * @author mashibing
 */

package yxxy.c_006;

public class T implements Runnable {

	private int count = 10;
	
	public synchronized void run() { //synchronize代码块是一个原子操作，原子操作是不可分的 
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<5; i++) {
			T t = new T();
			new Thread(t, "THREAD" + i).start();
		}
	}
	
}
