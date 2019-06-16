/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 运行下面的程序，并分析结果
 * 一个线程加到5000,放回主内存，其它线程读取这个5000,并加到6000放回主内存，第一个线程又从5000加到6000再放回主内存，覆盖了上一个结果，导致最终结果出错
 * synchronized即保证可见性有保证原子性，volatile只有可见性，效率不同
 * @author mashibing
 */
package yxxy.c_013;

import java.util.ArrayList;
import java.util.List;

public class T {
	volatile int count = 0; 
	void m() {
		for(int i=0; i<10000; i++) count++;
	}
	
	public static void main(String[] args) {
		T t = new T();
		
		List<Thread> threads = new ArrayList<Thread>();
		
		for(int i=0; i<10; i++) {
			threads.add(new Thread(t::m, "thread-"+i));
		}
		
		threads.forEach((o)->o.start());
		
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);//count正常结果是100000
		
		
	}
	
}


