﻿/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue并发容器，提高并发性
 * 
 * @author 马士兵
 */
package yxxy.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TicketSeller4 {
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();//并发的链表实现的队列
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(true) {
					String s = tickets.poll();//判断呢和操作也是分离的，但这里没有出错，效率更高
					if(s == null) break;
					else System.out.println("销售了--" + s);
				}
			}).start();
		}
	}
}
