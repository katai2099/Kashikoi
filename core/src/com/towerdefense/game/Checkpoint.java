package com.towerdefense.game;

public class Checkpoint {
	
	private Tile tile;
	private int x ;
	private int y;
	
	public Checkpoint(Tile tile,int x,int y)
	{
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	public Tile getTile() {
		return tile;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
