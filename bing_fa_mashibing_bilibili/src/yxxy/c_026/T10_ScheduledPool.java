package yxxy.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_ScheduledPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		service.scheduleAtFixedRate(()->{//最起始任务执行时间，每隔500ms任务重复执行
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
	}
}
