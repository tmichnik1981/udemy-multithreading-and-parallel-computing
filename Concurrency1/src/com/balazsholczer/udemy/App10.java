package com.balazsholczer.udemy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App10 {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		CountDownLatch latch = new CountDownLatch(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Worker10(1 + i, latch));
		}

		try {
			/**
			 * waiting for other tasks threads to finish their job (countdown() must be
			 * executed 5x)
			 */
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All the prerequisites are done...");
		executorService.shutdown();
	}
}

class Worker10 implements Runnable {

	private int id;
	private CountDownLatch countDownLatch;

	public Worker10(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		countDownLatch.countDown();
	}

	private void doWork() {
		System.out.println("Thread with id " + this.id + " starts working...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
