/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 *  
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * @author 马士兵
 */
package yxxy.c_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
	static Vector<String> tickets = new Vector<>();//Vector是同步的
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(tickets.size() > 0) {//判断和remove是分离的，到remove中间部分被打断，造成remove异常  只剩1张票时，多个线程都判断票数大于0
					
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
