package com.balazsholczer.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore - acquire() - release()
 */

enum Downloader {
	INSTANCE;

	private Semaphore semaphore = new Semaphore(3, true);

	public void downloadData() {
		try {
			/**
			 * only 3 threads can acquire a lock at a time. 
			 * All the rest need to wait for releasing 
			 */
			semaphore.acquire();
			download();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private void download() {
		System.out.println("Downloading data from the web...");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class App7 {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 12; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}

}
