package yxxy.c_025;
/**
 * 队列在并发里使用的最多
 */
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		Queue<String> strs = new ConcurrentLinkedQueue<>();//无界队列，没有最大容量，加数据失败返回false
		
		for(int i=0; i<10; i++) {
			strs.offer("a" + i);  //类似add
		}
		
		System.out.println(strs);
		
		System.out.println(strs.size());
		
		System.out.println(strs.poll());
		System.out.println(strs.size());
		
		System.out.println(strs.peek());
		System.out.println(strs.size());
		
		//双端队列Deque
	}
}
