/**
 * synchronized优化
 * 同步代码块中的语句越少越好
 * 比较m1和2
 * @author mashibing
 */
package yxxy.c_016;

import java.util.concurrent.TimeUnit;


public class T {
	
	int count = 0;

	synchronized void m1() {
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//业务逻辑中只有下面语句需要sync，这时不应该给整个方法上锁
		count ++;
		
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void m2() {
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//涓氬姟閫昏緫涓彧鏈変笅闈㈣繖鍙ラ渶瑕乻ync锛岃繖鏃朵笉搴旇缁欐暣涓柟娉曚笂閿�
		//采用细粒度的锁，可以使线程争夺的时间变短，从而提高效率
		synchronized(this) {
			count ++;
		}
		//do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}
