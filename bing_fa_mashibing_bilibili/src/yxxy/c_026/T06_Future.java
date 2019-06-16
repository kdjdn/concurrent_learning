/**
 * 认识future  未来，拿到未来的返回值
 */
package yxxy.c_026;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		FutureTask<Integer> task = new FutureTask<>(()->{//里面可以装入Callable类型，有返回值的，包装成FutureTask，这样就可以使用FutureTask里的方法
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); //这里相当于匿名类new Callable () { Integer call();}重写call方法，将来会返回Integer
		
		new Thread(task).start();
		
		System.out.println(task.get()); //阻塞   等待任务执行完成   get拿到返回值1000
		
		//*******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(f.get());
		System.out.println(f.isDone());
		
	}
}
