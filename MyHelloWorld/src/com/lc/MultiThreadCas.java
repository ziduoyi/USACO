package com.lc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//aa这个你理解的有点问题，不管i++是是不是原子操作，只要synchronized锁住的关键字是同一个对象就可以，而Integer判断两个对象是不是相等的方法是比较两个对象的int值，所以i每增加一次，对象就变了，所以锁也就换成新的对象了
//aa基本类型的包装类型跟String是类似的，内部是不可修改的,  一旦修改对象就变了
public class MultiThreadCas {

	public static void main(String[] args) throws InterruptedException {
		
		Anode[] nodes = new Anode[10];
		for(int i=0;i<10;i++) nodes[i]=new Anode();
		for(int i=0;i<10;i++) {
			if(i-1>-1) nodes[i].left=nodes[i-1];
			if(i+1<10) nodes[i].right=nodes[i+1];
		}
		Anode an = new Anode();
		nodes[9].right = an;
		an.left = nodes[9];
		for(int i=0;i<10;i++) 
			System.out.println(nodes[i].count());
		
		if(nodes[7]!=null) return;
		
			
		//producer & consumer with blockingqueue --- start ---
		final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
		new Thread(()->produce(blockingQueue)).start();
		new Thread(()->consume(blockingQueue)).start();
		//producer & consumer with blockingqueue --- end ---		
		
		
		Thread t1 = new Thread(()->testAll());
		Thread t2 = new Thread(()->testAll());
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println("i >>>>> "+i);
		System.out.println("vi >>>>> "+vi);
		System.out.println("ai >>>>> "+ai);
		System.out.println("si >>>>> "+si);
		System.out.println("svi>>>>> "+svi);
		System.out.println("ri> >>>> "+ri);
		System.out.println("sstr> >>>> "+sstr);

	}

	static void produce(BlockingQueue<Integer> blockingQueue) {
		try {
			for(int i=0;i<100;i++) {
				blockingQueue.put(i);					//put(): wait if it is full; 			add(): throw an exception if full
				System.out.println("produced # " + i);
			}
		}catch(Exception ex) {
			
		}
	}
	static void consume(BlockingQueue<Integer> blockingQueue) {
		try {
			for(int i=0;i<100;i++) {
				int x = blockingQueue.take();		//take(): wait if empty; 		poll();  poll() return null if empty
				System.out.println("consumed # " + x);
			}
		}catch(Exception ex) {
			
		}
	}
	
	
	static void testThreadPool(){
		final ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<Integer, String>();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		newFixedThreadPool.submit(new Runnable() {
			public void run() {
				for(int i = 0;i <1000000;i++){
					chm.put(123, "asd"+i);
				}
			}
		});
		newFixedThreadPool.submit(new Runnable() {
			public void run() {
				System.out.println(chm.get(123));
			}
		});
		newFixedThreadPool.shutdown();
		
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(100);
		Future ft = newFixedThreadPool.submit(()->produce(bq));
		try {
			ft.get();
		} catch (Exception e) {
			;
		}
	}

	static int i=0;
	static volatile Integer vi=0;
	static AtomicInteger ai = new AtomicInteger();
	static Integer si = 0;
	static volatile Integer svi = 0;
	static String sstr = "";
	static int ri;
	static Lock lock = new ReentrantLock();
	
	static void testAll() {
		for(int k=0;k<200000;k++) {

			ai.incrementAndGet();
			synchronized(si) {
				si++;
			}
			synchronized(svi) {
				svi++;
			}
			synchronized(sstr) {
				sstr=""+k;
			}
			lock.lock();
			try {
				ri++;
			}finally {
				lock.unlock();
			}
			i++;
			vi++;
		}
		
	}

	//aa这个你理解的有点问题，不管i++是是不是原子操作，只要synchronized锁住的关键字是同一个对象就可以，而Integer判断两个对象是不是相等的方法是比较两个对象的int值，所以i每增加一次，对象就变了，所以锁也就换成新的对象了
	//aa基本类型的包装类型跟String是类似的，内部是不可修改的,  一旦修改对象就变了
	
}

/*	
一堆node 可以call一些api:
sendLeft, sendRight, receivedFromLeft, receivedFromRight 
node是horizontally相连的 比如 A<->B<->C
计算一共有多少个node
实现的code可以从任何一个node被trigger. 
我说这是类似双链表吗。面试官说是 但这是在不同node上都能call的一个api
另外node之间只能用那些callback即sendXXX receivedxXX来通讯

反正不是算法题，偏多线程和api的一些东西。
最后也写的不好 拖了一个半小时 面试官还是很有耐心的等我code。。。其实都不想continue了
他又给了一堆提示 最后算马马虎虎写了一个大概	
*/

class Counter{	//use volatile
	AtomicInteger num = null;
	volatile boolean isLeftCounted=false, isRightCounted=false;
	Counter(){ num = new AtomicInteger(0);}
	void increase() { num.addAndGet(1);}
	void setLeftDone() { isLeftCounted = true;}
	void setRightDone() { isRightCounted = true;}
	int getTotalCount()  {
		while(!isLeftCounted|| !isRightCounted) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return num.get();
	}
}

class CounterII{	//use countdownlatch
	AtomicInteger num = null;
	CounterII(){ num = new AtomicInteger(0);}
	void increase() { num.addAndGet(1);}
	
	CountDownLatch latch = new CountDownLatch(2);
	void setLeftDone() { latch.countDown();}
	void setRightDone(){ latch.countDown();}

	int getTotalCount()  {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return num.get();
	}
}
class Anode{
	Anode left=null, right=null;
	void sendLeft(Counter c) {if(left!=null) left.receivedFromRight(c); else c.setLeftDone();}
	void sendRight(Counter c) {if(right!=null) right.receivedFromLeft(c); else c.setRightDone();}
	void receivedFromRight(Counter c) {	c.increase();sendLeft(c);}
	void receivedFromLeft(Counter c) {	c.increase();sendRight(c);}

	public int count() {
		Counter cntr = new Counter();
		sendLeft(cntr);
		sendRight(cntr);
		cntr.increase();
		return cntr.getTotalCount();
	}
}