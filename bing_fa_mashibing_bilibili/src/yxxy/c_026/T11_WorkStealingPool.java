/**
 *
 */
package yxxy.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());//四核，默认四个线程

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000)); //daemon，永远运行，直到虚拟机停止
		service.execute(new R(2000));//多了一个任务，会被第一个线程拿走任务
		
		//产生的是精灵线程，主线程不阻塞的话看不到输出
		System.in.read();//使主函数阻塞 
	}

	static class R implements Runnable {

		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(time  + " " + Thread.currentThread().getName());
			
		}

	}
}
