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
		this.hp = 50;
		this.hiddenhealth = 50;
		this.def=0;
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
		if(amount == 0)
		{
			if(def<12 && !piercethrough)
			{
				this.def+=3;
			}
		}
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
