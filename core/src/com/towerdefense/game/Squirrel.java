package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Squirrel extends Monster{
	

	
	Squirrel(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		Monster.id++;
		this.idNumber = id;
		this.texture= new Texture("squirel.png");
		this.giveExp = 2; 
		this.giveGold = 3;
		this.atk = 5;
		this.hp = 10;
		this.speed = 10;
		this.originalSpeed = this.speed;
		this.def = 2;
		this.hiddenhealth = 10;
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

	//for testing 
	public Squirrel() {
		this.alive = true;
		this.atk = 5;
		this.giveGold = 3;
	}
	
	//for testing
	Squirrel(Tile startile,Map map)
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


	public void damage(float amount)
	{
		if(amount==0);
		else if (amount ==1) this.hp-= amount ;
		else {
		if(amount-def<=0)
		{
			this.hp-=0.5f;
		}
		else 
		this.hp -= (amount-this.def);
		}
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}
	

	
	
	
	
	

	
}
