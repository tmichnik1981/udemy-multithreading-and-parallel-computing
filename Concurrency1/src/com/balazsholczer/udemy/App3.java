package com.balazsholczer.udemy;

class Processor{
	public void produce() throws InterruptedException{
		
		synchronized (this) {
			System.out.println("We are in the producer method...");
			//stop processing and release a lock
			wait(10000);
			System.out.println("Again producer method...");
		}
	}
	
	public void consume() throws InterruptedException{
		Thread.sleep(1000);
		
		//stop if lock was acquired
		synchronized (this) {
			System.out.println("Consumer method...");
			/**
			 * wake up producer however producer's thread will continue when 
			 * a consumer leaves a synchronized block 
			 */
			notify();
			Thread.sleep(3000);
		}
	}
}

public class App3 {

	public static void main(String[] args) {
		
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
					try {
						processor.consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		t1.start();
		t2.start();

		try{
			t1.join();
			t2.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
