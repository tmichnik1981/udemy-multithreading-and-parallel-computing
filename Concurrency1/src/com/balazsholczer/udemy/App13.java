package com.balazsholczer.udemy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class App13 {

	public static void main(String[] args) {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		
		try {
			queue.put(new DelayedWorker(1000, "This is the first message..."));
			queue.put(new DelayedWorker(10000, "This is the second message..."));
			queue.put(new DelayedWorker(4000, "This is the third message..."));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while(!queue.isEmpty()) {
			try {
				//DelayedWorkers will be returned after 1s, 10s, 4s 
				System.out.println(queue.take());
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class DelayedWorker implements Delayed {

	private long duration;
	private String message;

	public DelayedWorker(long duration, String message) {
		this.duration = System.currentTimeMillis() + duration;
		this.message = message;
	}

	public long getDuration() {
		return duration;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int compareTo(Delayed otherDelayed) {
		if (this.duration < ((DelayedWorker) otherDelayed).getDuration()) {
			return -1;
		}
		if (this.duration > ((DelayedWorker) otherDelayed).getDuration()) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.message;
	}
}
