/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题（dirtyRead）：读到写没有完成的数据，写入时暂停一段时间，另一个线程在这段时间读取，读出的数据不对
 * 解决：copyOrWrite,解决脏读问题，性能还很高
 */

package yxxy.c_008;

import java.util.concurrent.TimeUnit;

public class Account {
	String name;
	double balance;
	
	public synchronized void set(String name, double balance) {
		this.name = name;
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		this.balance = balance;
	}
	
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	
	public static void main(String[] args) {
		Account a = new Account();
		new Thread(()->a.set("zhangsan", 100.0)).start();
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				a.set("zhangsan", 100.0);
			}
			
		});*/
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
	}
}
