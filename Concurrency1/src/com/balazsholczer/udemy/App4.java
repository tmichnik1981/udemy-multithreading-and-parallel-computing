package com.balazsholczer.udemy;

import java.util.ArrayList;
import java.util.List;

class Processor2 {

	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private final Object lock = new Object();
	private int value = 0;

	public void producer() throws InterruptedException {
		//we have to synchronize on lock object. If locked on this, Processor2 would not work
		synchronized (lock) {
			while (true) {
				if (list.size() == LIMIT) {
					System.out.println("Waiting for removing items from the list...");
					//producer thread stops
					lock.wait();
				}else{
					System.out.println("Adding: " + value);
					list.add(value);
					value++;
					//producer thread informs consumer thread (which subscribed lock object) to continue
					//consumer however will wake up effectively when producer stops executing  'lock.wait()'
					lock.notify();
				}
			}
		}

	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {
			
			while (true) {
				if (list.size() == BOTTOM) {
					System.out.println("Waiting for adding items to the list...");
					lock.wait();
				}else{
					System.out.println("Removed: " + list.remove(--value));
					lock.notify();
				}
				
				Thread.sleep(500);
			}
		}
	}
}

public class App4 {

	public static void main(String[] args) {

		Processor2 processor = new Processor2();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
