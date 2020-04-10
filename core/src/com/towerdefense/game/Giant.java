package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Giant extends Monster{
	
	Giant(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		Monster.id++;
		this.idNumber = id;
		this.texture= new Texture("Giant.png");
		this.giveExp = 5;
		this.giveGold = 5;
		this.atk = 20;
		this.hp = 30;
		this.hiddenhealth = 30;
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

	
	public void damage(int amount)
	{
		this.hp -= amount;
	/*	if(hp<=0) 
		{
			die();
			Player.modifyCash(10);
		} */
	}
	
	public void die()
	{
		alive = false;
	}
	
	
	
	
	
	

	
}
