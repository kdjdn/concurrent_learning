总结：
1：对于map/set(map换成set)的选择使用
HashMap不加锁时使用
TreeMap不加锁,有序
LinkedHashMap不加锁

Hashtable加锁，并发不高时使用
Collections.sychronizedXXX加锁，并发不高时使用，传入不加锁的map，对容器进行包装，返回加锁的map

ConcurrentHashMap加锁，并发高时使用
ConcurrentSkipListMap 加锁，并发高，排序时使用

2：队列
ArrayList不同步，并发低，不加锁
LinkedList不同步，并发低，不加锁
Collections.synchronizedXXX同步,并发低，加锁
CopyOnWriteList写的少，读的多时使用，监听器使用多，读的时候不加锁，不会出现脏读，操作都在新的拷贝里，然后引用指向拷贝
Queue高并发时可以使用两种队列
	CocurrentLinkedQueue内部加锁的 ，非阻塞式并发//concurrentArrayQueue
	BlockingQueue阻塞式队列（如生产者消费者），自动实现阻塞：
		LinkedBlockingQueue阻塞式队列，无界
		ArrayBlockingQueue阻塞式队列，有界
		TransferQueue有消费者在执行时生产者就不生产，生产者生产一个直接交给消费者，游戏服务器转发消息时使用
		SynchronusQueue同步队列，一种特殊的TransferQueue，容量为0，里面的存储必须要消费，先启动消费者，生产者直接找消费者，没有消费者就等着
	DelayQueue执行定时任务，无界
		
	