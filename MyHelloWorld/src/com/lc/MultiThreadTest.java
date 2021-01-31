package com.lc;

import java.util.*;


public class MultiThreadTest {

	static ArrayList<Integer> sv = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		
		
		// TODO Auto-generated method stub

		Thread t1 = new Thread(new ProducerThread(sv));
		t1.start();

		new Thread(()->consume()).start();
		//Thread t2 = new Thread(new ConsumerThread(sv));
		//t2.start();
		
//		Runnable ra = new Runnable() {
//			public void run(){
//				consume();
//			}
//		};
//		Thread t2 = new Thread(ra);
//		t2.start();
		
//		Runnable task = ()->consume();
//		Thread t2 = new Thread(task);
//		t2.start();
		
		

	}
	static void consume() {
		try {
			for(int i=0;i<100;i++) {
				if(sv.size()<1) {
					synchronized(sv){
						if(sv.size()<1)
							sv.wait();
					}
				}
				if(sv.size()>0) {
					synchronized(sv) {
						if(sv.size()>0) {
							System.out.println("consumed #" + sv.remove(0));
							sv.notify();
						}
					}
				}
			}
		}catch(Exception ex) {
			;
		}
	}

}

class ProducerThread implements Runnable{
	ArrayList<Integer> sv;
	int x=0;
	public ProducerThread(ArrayList<Integer> v){
		sv=v;
	}
	
	public void run() {
		try {
			for(int i=0;i<100;i++) {
				produce();
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void produce() throws InterruptedException {
		if(sv.size()>9) {
			synchronized(sv) {
				if(sv.size()>9)
					sv.wait();
			}
		}
		synchronized(sv) {
			if(sv.size()<10) {
				sv.add(++x);
				System.out.println("Produced #" + x);
				sv.notify();
			}
		}
	}
}

class ConsumerThread implements Runnable{
	ArrayList<Integer> sv;
	
	public ConsumerThread(ArrayList<Integer> v) {
		sv = v;
	}
	
	public void run() {
		try {
			while(true)
				consume();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void consume() throws InterruptedException {
		if(sv.size()<1) {
			synchronized(sv){
				if(sv.size()<1)
					sv.wait();
			}
		}
		if(sv.size()>0) {
			synchronized(sv) {
				if(sv.size()>0) {
					System.out.println("consumed #" + sv.remove(0));
					sv.notify();
				}
			}
		}
	}
}