package com.towerdefense.game;

//this class use to store checkpoint of monster 

public class Checkpoint {
	
	private Tile tile;
	private int x ;
	private int y;
	
	//x is x direction
	//y is y direction
	
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
