package com.balazsholczer.udemy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker14 implements Runnable{

	private BlockingQueue<String> blockingQueue;
	
	public FirstWorker14(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put("B");
			blockingQueue.put("H");
			blockingQueue.put("F");
			Thread.sleep(1000);
			blockingQueue.put("A");
			Thread.sleep(1000);
			blockingQueue.put("E");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}

class SecondWorker14 implements Runnable{

	private BlockingQueue<String> blockingQueue;
	
	public SecondWorker14(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}


public class App14 {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		
		new Thread(new FirstWorker14(queue)).start();
		
		new Thread(new SecondWorker14(queue)).start();


	}

}
