package com.roocon.thread.tc6;

/**
 * Final��Ϊ��������
 */
public class Demo2 {

    private final Object obj;//������������

    public Demo2(){
        obj = new Object();
    }

    private Demo2 demo2;

    public void w(){
        demo2 = new Demo2();
    }
    public void r(){
        Demo2 d = demo2;
    }
}
