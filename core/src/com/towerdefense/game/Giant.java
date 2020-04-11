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
		this.def = 5;
		this.hp = 20;
		this.hiddenhealth = 30;
		this.speed = 8;
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
		if(amount-def<=0)
		{
			this.hp-=0.5f;
		}else 
		this.hp -= (amount-this.def);
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		} 
	}
	
	public void die()
	{
		alive = false;
	}
	
	
	
	
	
	

	
}
