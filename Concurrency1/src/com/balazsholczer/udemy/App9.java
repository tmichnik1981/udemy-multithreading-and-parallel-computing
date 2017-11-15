package com.balazsholczer.udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor9 implements Callable<String> {

	private int id;

	public Processor9(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: " + id;
	}

}

public class App9 {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();

		for (int i = 0; i < 5; ++i) {
			Future<String> future = executorService.submit(new Processor9(i + 1));
			list.add(future);
		}
		
		for(Future<String> future : list){
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
	}

}
