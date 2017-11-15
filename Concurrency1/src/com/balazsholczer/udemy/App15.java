package com.balazsholczer.udemy;

import java.util.concurrent.ConcurrentHashMap;

class FirstWorker15 implements Runnable{

	private ConcurrentHashMap<String, Integer> map;
	
	public FirstWorker15(ConcurrentHashMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {
		try {
			map.put("B", 1);
			map.put("H", 2);
			Thread.sleep(1000);
			map.put("F", 3);
			map.put("A", 4);
			Thread.sleep(1000);
			map.put("E", 5);

						
		}catch(InterruptedException e) {
			e.printStackTrace();
		}		
	}
}

class SecondWorker15 implements Runnable{

	private ConcurrentHashMap<String, Integer> map;
	
	public SecondWorker15(ConcurrentHashMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(map.get("A"));		
			Thread.sleep(1000);
			System.out.println(map.get("E"));		
			System.out.println(map.get("F"));		

		}catch(InterruptedException e) {
			e.printStackTrace();
		}		
	}
}

public class App15 {

	public static void main(String[] args) {
		
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		
		new Thread(new FirstWorker15(map)).start();
		new Thread(new SecondWorker15(map)).start();

	}

}
