/**
 * 并发容器
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */
package yxxy.c_025;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
	public static void main(String[] args) {
		//比较几个效率
		//Map<String, String> map = new ConcurrentHashMap<>();
		//Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发并且排序，默认把容器分16段，每次只锁其中一段，可以多个线程并发插入
		
		Map<String, String> map = new Hashtable<>();//默认加锁，所有实现都加锁了，对整个容器加锁，现在很少用，因为效率很低
		//Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX加锁
		//TreeMap/sortedMap默认排序,并发时效率低
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);//门栓，计数器是100
		long start = System.currentTimeMillis();
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				for(int j=0; j<10000; j++) 
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
