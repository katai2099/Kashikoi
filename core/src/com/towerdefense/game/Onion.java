package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
	
	@Override
	public void damage(int amount)
	{
		this.hp --;
	/*	if(hp<=0) 
		{
			die();
			Player.modifyCash(5);
		} */
	}
	
	@Override
	protected void reduceHiddenHealth(int amount)
	{
		this.hiddenhealth --;
	}
	
	
	
	
	
	
	

	
}
