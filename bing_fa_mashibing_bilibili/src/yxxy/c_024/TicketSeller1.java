﻿/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 
 * 
 * @author 马士兵
 */
package yxxy.c_024;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
	static List<String> tickets = new ArrayList<>();//ArrayList不是同步的
	
	static {
		for(int i=1; i<=10000; i++) tickets.add("票编号：" + i);
	}
	
	
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(tickets.size() > 0) {//判断和操作分离 ，当只剩1张票时每个线程看到size()>0，都在remove，造成异常   也可能多个线程买同一张票
					System.out.println("销售了--" + tickets.remove(0));//remove不是原子性
				}
			}).start();
		}
	}
}
