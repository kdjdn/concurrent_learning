/**
 * 线程池的概念   6种线程池
 * FixedThreadPool固定个数
 * CachedThreadPool每来个任务起一个线程，有空闲的不起新的，空闲aliveTime60分钟自动销毁
 * SingleThreadExecutor一个线程，保证任务执行顺序
 * ScheduledThreadPool执行定时任务，线程是可以服用的
 * WorkStealingPool工作窃取的线程池，每个线程维护自己的队列，每个线程里可以有多个任务，当一个线程执行完自己的任务后可以在别的线程的列表里偷个任务执行，自己主动找活干
 * ForkJoinPool分叉然后合并，把一个难以完成的大任务切分成一个个小任务，如果小人物还是大，还可以继续分，最后合并join，切分方式可以指定，线程启动根据切分方式由ForkJoinPool来维护
 * 线程池维护两个队列，一个是未执行的任务列表，一个是结束任务的列表
 */
package yxxy.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5); //execute只执行Runnable方法   submit执行两种方法
		for (int i = 0; i < 6; i++) {//加入6个任务
			service.execute(new Runnable() {
				public void run(){
				    try {
					    TimeUnit.MILLISECONDS.sleep(500);
				    } catch (InterruptedException e) {
					    e.printStackTrace();
				    }
				    System.out.println(Thread.currentThread().getName());
				}
			});
		}
		System.out.println(service);
		
		service.shutdown();//关闭线程，等线程执行完再关闭
		System.out.println(service.isTerminated());//所有任务是否执行完
		System.out.println(service.isShutdown());//所有任务是否全关闭
		System.out.println(service);//打印正在关闭
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
