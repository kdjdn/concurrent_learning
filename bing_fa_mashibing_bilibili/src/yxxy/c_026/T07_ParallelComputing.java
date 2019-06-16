/**
 * 线程池的概念
 * nasa并行计算，多个线程完成一个任务
 * 找到1--20000范围里质数
 */
package yxxy.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T07_ParallelComputing {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		getPrime(1, 200000); //方式一：一个线程
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		final int cpuCoreNum = 4;//与cpu核数相符
		
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);//方式二：4个线程
		
		MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);
		
		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;
		
		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
		
	}
	
	static boolean isPrime(int num) {//判断是否是质数
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	static List<Integer> getPrime(int start, int end) {//在一个范围里找出所有质数，放到list里
		List<Integer> results = new ArrayList<>();
		for(int i=start; i<=end; i++) {
			if(isPrime(i)) results.add(i);
		}
		
		return results;
	}
}
