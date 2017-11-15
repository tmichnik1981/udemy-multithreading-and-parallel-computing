package com.balazsholczer.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App8 {

	public static void main(String[] args) {
		/**
		 * 5 tasks but only 3 threads so only 3 workers can work simultaneously
		 */
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Worker8());
		}
	}

}

class Worker8 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
