package com.roocon.thread.tc6;

/**
 * Final域为抽象类型
 */
public class Demo2 {

    private final Object obj;//抽象数据类型

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
