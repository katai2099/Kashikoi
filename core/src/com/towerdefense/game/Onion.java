package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Onion extends Monster{
	
	Onion(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		Monster.id++;
		this.idNumber = id;
		this.texture= new Texture("Onion.png");
		this.giveGold = 5;
		this.giveExp = 3;
		this.atk = 10;
		this.hp = 3;
		this.hiddenhealth = 3;
		this.speed = 10;
		this.currentTile=startile;
		this.map = map;
		this.dir = new int[2];
		this.checkpoints = new ArrayList<Checkpoint>();
		dir[0]=0;
		dir[1]=0;
	 	dir = findDirection(startile);
	 	this.currentCheckpoint = 0 ;
	 	this.alive = true;
	 	populateCheckpointList();
	}
	
	//for Testing
	Onion()
	{
		this.hp = 3;
		this.speed = 10;
		this.giveGold = 5;
		this.giveExp = 3;
	}
	
	//for testing
	Onion(Tile startile,Map map)
	{
		this.startile = startile;
		this.currentTile=startile;
		this.x = startile.getX();
		this.y = startile.getY();
		this.map = map;
		this.height=64;
		this.width = 64;
		//Monster.id++;
	//	this.idNumber = id;
		//this.texture=new Texture("seki.png");
		this.giveExp = 20;
		this.giveGold = 100;
		this.atk = 100;
		this.hp = 200;
		this.hiddenhealth = 100;
		this.def = 10;
		this.speed = 10;
		this.currentTile=startile;
		this.map = map;
		this.dir = new int[2];
		this.checkpoints = new ArrayList<Checkpoint>();
		dir[0]=0;
		dir[1]=0;
	 	dir = findDirection(startile);
	 	this.currentCheckpoint = 0 ;
	 	this.alive = true;
	 	populateCheckpointList();
	}
	
	
	@Override
	public void damage(float amount)
	{
		this.hp --;
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}
	
	protected void reduceHiddenHealth()
	{
		this.hiddenhealth --;
	}
	
	
	
	
	
	
	

	
}
