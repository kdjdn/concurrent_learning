package yxxy.c_025;
/**
 * LinkedTransferQueue
 * 消费者先启动，生产者找消费者，如果有消费者就不生产了，效率高，用于更高的并发情况
 * 没有消费者时候，如果调用transfer就会阻塞
 */
import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		//strs.transfer("aaa");//没有消费者时会阻塞
		
		strs.put("aaa");//不会阻塞
		

		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
