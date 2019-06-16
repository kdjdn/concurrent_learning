package yxxy.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {

	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);//已经加满了
		}
		
		//可以使用多个方式加入数据
		strs.put("aaa"); //满了就会等待，程序阻塞
		//strs.add("aaa");//会出异常
		//strs.offer("aaa");//不会报异常，但加不进去，返回boolean，
		//strs.offer("aaa", 1, TimeUnit.SECONDS);//一段时间加不进去就不加了
		
		System.out.println(strs);
	}
}
