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
		
	public void damage(float amount)
	{
		if(amount==0);
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
