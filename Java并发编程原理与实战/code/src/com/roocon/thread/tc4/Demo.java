package com.roocon.thread.tc4;

/**
 * �����ͷ����ȡ��������happens-before��ϵ��
 * ���������� 1happens-before2happens-before3happens-before4happens-before5happens-before6
 * ���������� 3happens-before4
 * ������  1happens-before2happens-before3happens-before4happens-before5happens-before6
 * @author worker
 *
 */
public class Demo {
	
	private int value;
	
	public synchronized void a() { // 1  ��ȡ��
		value ++; // 2
	} // 3 �ͷ���
	
	public synchronized void b() { // 4 ��ȡ��
		int a = value; // 5
		// ���������Ĳ��� 
	} // 6 �ͷ���
	

}
