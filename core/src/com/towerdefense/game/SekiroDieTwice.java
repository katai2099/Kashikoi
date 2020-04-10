package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
		this.atk = 50;
		this.hp = 200;
		this.hiddenhealth = 100;
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
		
	public void damage(int amount)
	{
		this.hp -= amount;
		if(hp<=0) 
		{
			if(!isRevive())
			{
				revive();
			}
		//	else {die();
		//	Player.modifyCash(300);}
		}
	}
	

	
	private boolean isRevive()
	{
		return reviveYet;
	}
	
	private void revive()
	{
		hp = 100;
	    reviveYet = true;
	}
	
	@Override
	protected void reduceHiddenHealth(int amount)
	{
		this.hiddenhealth -= amount;
		if(hiddenhealth<=0)
		{
			if(!isRevive())
			{
				hiddenhealth = 100;
			}
		}
	}
	
	
	
	
	
	

	
}
