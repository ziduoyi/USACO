package com.lc;


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
			try {
				mt.produce2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				mt.consume2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	Integer product = 0;
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
	    System.out.println("Produced " + this.product + " products.");	//("生产者生产第" + this.product + "个产品.");
	    notifyAll();   //通知等待区的消费者可以取出产品了	wake up waiting thread to get product  
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
	
	    System.out.println("Consumed " + this.product + " products.");//("消费者取走了第" + this.product + "个产品.");
	    this.product--;
	    notifyAll();   //通知等待去的生产者可以生产产品了	wake up producing thread to produce more products
	}
	
	public void produce2() throws InterruptedException	{
	    if(this.product >= 3){
	    	synchronized(this.product) {
	    		product.wait();
	    	}
	        System.out.println("it is full, please wait for empty space");
	    }
	    synchronized(this.product) {
	    	this.product++;
	    	this.product.notify();
	   }
	    System.out.println("Produced " + this.product + " products.");	//("生产者生产第" + this.product + "个产品.");
	    //notifyAll();   //通知等待区的消费者可以取出产品了	wake up waiting thread to get product  
	    //return ++count0;
	}
	
	
	public void consume2() throws InterruptedException{
	    if(this.product <= 0){
        	synchronized(this.product) {
        		this.product.wait(); 
        	}
            System.out.println("empty now, wait for next delivery");
	    }

	    System.out.println("Consumed " + this.product + " products.");//("消费者取走了第" + this.product + "个产品.");
	    synchronized(this.product) {
	    	this.product--;
	    	product.notify();   //通知等待去的生产者可以生产产品了	wake up producing thread to produce more products
	    }
	}
	
	public class MyDeadlock {
		 
	    String str1 = "Java";
	    String str2 = "UNIX";
	     
	    Thread trd1 = new Thread("My Thread 1"){
	        public void run(){
	            while(true){
	                synchronized(str1){
	                    synchronized(str2){
	                        System.out.println(str1 + str2);
	                    }
	                }
	            }
	        }
	    };
	     
	    Thread trd2 = new Thread("My Thread 2"){
	        public void run(){
	            while(true){
	                synchronized(str2){
	                    synchronized(str1){
	                        System.out.println(str2 + str1);
	                    }
	                }
	            }
	        }
	    };
	     
	    //public static void main(String a[]){
	  public void testDeadLock(String a[]){
	        MyDeadlock mdl = new MyDeadlock();
	        mdl.trd1.start();
	        mdl.trd2.start();
	    }
	}
}
