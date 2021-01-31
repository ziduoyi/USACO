package com;


class Thread1 extends Thread{
	public void run() {
		System.out.println("From extends Thread ");
		//sleep((int) Math.random() * 10);
	}
}

class Thread2 implements Runnable{
	public void run() {
		System.out.println("From Runnable Thread ");
	}
}

class Thread3 implements Runnable{
	MultiThread mt=null;
	public void run(){
		System.out.println("producer... ");
		for(int i=0;i<10;i++)
			mt.produce();
	}
	public Thread3(MultiThread m1){
		this.mt=m1;
	}
}
class Thread4 implements Runnable{
	MultiThread mt=null;
	public void run(){
		System.out.println("consumer... ");
		for(int i=0;i<10;i++)
			mt.consume();
	}
	public Thread4(MultiThread m1){
		this.mt=m1;
	}
}


public class MultiThread {
	volatile int count1=0;
	int count2=0;
	
	static int count0 = 0;
	
	int data;
	public static void main(String[] args) throws InterruptedException {
	//Ex1: extends Thread class
		Thread1 te1=new Thread1();
		Thread1 te2=new Thread1();
		te1.start();
		te2.start();
		
		te1.join();	//wait for Thread te1 to finish
		
	//Ex2: implement runnable
		Thread2 t2= new Thread2();
		new Thread(t2).start();
		
	//consume / produce
		MultiThread mt=new MultiThread();
		new Thread(new Thread3(mt)).start();
		new Thread(new Thread4(mt)).start();
		
		
 	}

	//synchronized method	-- instance level
	public synchronized void mAA(){	//
		count2++;
	}

	public void mAAEquivalent(){	//equivalent to mAA()
		synchronized (this){
			count2++;
		}
	}

	public void method3(byte[] so){
		synchronized(so){
			count2++;	//
		}
	}


	//static synchronized method	-- class level
	public synchronized static void msAAA(){
		count0++;
	}
	
	public void msBBB(){					//equivalent to msAAA()
		synchronized(MultiThread.class){
			count0++;
		}
	}
	
	int product = 0;
	public synchronized void produce()	{
	    if(this.product >= 3){
	        try{
	            wait();  
	            System.out.println("it is full, please wait for empty space");
	        }catch(InterruptedException e){
	            e.printStackTrace();
	        }
	        return;
	    }

	    this.product++;
	    System.out.println("生产者生产第" + this.product + "个产品.");
	    notifyAll();   //通知等待区的消费者可以取出产品了
	    //return ++count0;
	}
	
	
	public synchronized void consume(){
	    if(this.product <= 0){
	        try{
	            wait(); 
	            System.out.println("empty now, wait for next delivery");
	        }catch (InterruptedException e){
	            e.printStackTrace();
	        }
	        return;
	    }
	
	    System.out.println("消费者取走了第" + this.product + "个产品.");
	    this.product--;
	    notifyAll();   //通知等待去的生产者可以生产产品了
	}
}
