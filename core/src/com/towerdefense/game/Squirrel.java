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

	
	public Squirrel() {
		this.alive = true;
		this.atk = 5;
		this.giveGold = 3;
	}


	public void damage(float amount)
	{
		if(amount==0);
		else {
		if(amount-def<=0)
		{
			this.hp-=0.5f;
		}
		else 
		this.hp -= (amount-this.def);}
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}
	

	
	
	
	
	

	
}
