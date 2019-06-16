/**
 * 认识Executor接口，里面只定义了一个方法，执行器，最顶层接口
 */
package yxxy.c_026;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor{

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();
		
	}

}

