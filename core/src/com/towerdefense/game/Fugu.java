package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Fugu extends Monster{
		
	Fugu(Tile startile,Map map,float height,float width)
	{
		super(startile, map, height, width);
		Monster.id++;
		this.idNumber = id;
		this.texture = new Texture("fugu.png");
		this.giveExp = 8; 
		this.giveGold = 10;
		this.atk = 10;
		this.hp = 20;
		this.hiddenhealth = 50;
		this.def=0;
		this.speed = 10;
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

	//for testing
	public Fugu() {
		this.def=0;
		this.piercethrough=false;
		this.hp=20;
		this.alive = true;
		this.giveGold = 10;
	}
	
	//for testing
	Fugu(Tile startile,Map map)
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

	//Damage monster increase armor of this monster
	public void damage(float amount)
	{
		if(amount == 0)
		{
			if(def<12 && !piercethrough)
			{
				this.def+=3;
			}
		}
		else if(amount==1)this.hp-=amount;
		else {
		if(def<12) {
			if(amount-def<=0)
			{
				this.hp-=0.5f;
			}
			else
		this.hp -= (amount-def);
		if(!piercethrough) this.def += 3;
		}
		else
		{
			if(amount-def<=0)
			{
				this.hp-=0.5f;
			}
			else	
			this.hp -= (amount-def);
		}
		}
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}

	
	
	
	
	
	

	
}
