package com.minerapp.workers;

import com.minerapp.view.Board;

public class MineLayer implements Runnable {

	private int id;
	private Board board;
	private volatile boolean layerRunning;
	
	
	public MineLayer(int id, Board board) {
		this.id = id;
		this.board = board;
		this.layerRunning = true;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void setLayerRunning(boolean b) {
	
		
	}

}
