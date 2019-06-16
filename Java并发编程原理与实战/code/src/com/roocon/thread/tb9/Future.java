package com.roocon.thread.tb9;

/**
 * 订单，订单通过set和产品管理
 */
public class Future {
	
	private Product product;
	
	private boolean down;
	
	public synchronized void setProduct (Product product) {
		if(down) {
			return;
		}
		
		this.product = product;
		this.down = true;
		notifyAll();
	}
	
	public synchronized Product get () {
		while(!down) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

}
