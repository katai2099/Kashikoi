package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

/*
 * SekiroDieTwice is boss monster it can revive with half
 * of its original HP
 */

public class SekiroDieTwice extends Monster{
	
	boolean reviveYet;

	
	SekiroDieTwice(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		Monster.id++;
		this.idNumber = id;
		this.texture=new Texture("seki.png");
		this.giveExp = 20;
		this.giveGold = 100;
		this.atk = 100;
		this.hp = 150;
		this.hiddenhealth = 100;
		this.def = 8;
		this.speed = 8;
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
	 	reviveYet = false;
	}
	
	//for testing
	public SekiroDieTwice()
	{
		this.hp = 10;
		this.alive = true;
		reviveYet = false;
		this.def = 0;
	}
	
	//for testing
	SekiroDieTwice(Tile startile,Map map)
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
	 	reviveYet = false;
	}
	
	//receive damage from external source 
	public void damage(float amount)
	{
		if(amount==0);
		else if(amount==1) this.hp -= amount;
		else {
		if(amount-def<=0)
		{
			this.hp-=0.5f;
		}
		else
		this.hp -= (amount-def);
		}
		if(hp<=0) 
		{
			if(!isRevive())
			{
				revive();
			}
			else {die();
			Player.modifyCash(this.giveGold);}
		}
	}
	
	//check whether it has been revived once or not
	private boolean isRevive()
	{
		return reviveYet;
	}
	//call when it die and has not been revive once
	private void revive()
	{
		hp = 100;
	    reviveYet = true;
	}
	
	@Override
	protected void reduceHiddenHealth(float amount)
	{
		this.hiddenhealth -= (amount-def);
		if(hiddenhealth<=0)
		{
			if(!isRevive())
			{
				hiddenhealth = 100;
			}
		}
	}
	
	
	
	
	
	

	
}
