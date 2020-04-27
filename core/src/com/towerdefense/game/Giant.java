package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

/*
 * Giant has most HP in the game but has slow speed
 */

public class Giant extends Monster{
	
	Giant(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		Monster.id++;
		this.idNumber = id;
		this.texture= new Texture("Giant.png");
		this.giveExp = 10;
		this.giveGold = 10;
		this.atk = 20;
		this.def = 5;
		this.hp = 30;
		this.hiddenhealth = 20;
		this.speed = 7;
		this.originalSpeed=this.speed;
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

	//For Testing Purpose
	public Giant() {
		this.atk = 20;
		this.def = 5;
		this.hp = 20;
		this.speed = 8;
		this.alive = true;
		this.giveGold = 5;
	}
	
	//for testing
	Giant(Tile startile,Map map)
	{
		this.startile = startile;
		this.currentTile=startile;
		this.x = startile.getX();
		this.y = startile.getY();
		this.map = map;
		this.height=64;
		this.width = 64;
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


	//Basic damage monster (defense - attackDamage)
	public void damage(float amount)
	{
		if(amount==0);
		else if(amount == 1) this.hp-=amount;
		else {
		if(amount-def<=0)
		{
			this.hp-=0.5f;
		}else 
		this.hp -= (amount-this.def);
		}
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}
	
	
	
	
	
	
	
	

	
}
