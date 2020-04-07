package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Onion extends Monster{
	
	Onion(Tile startile,Map map,float height,float width)
	{
		super(startile,map,height,width);
		this.texture= new Texture("Onion.png");
		this.x = startile.getX();
		this.y = startile.getY();
		this.height = height;
		this.width = width;
		this.atk = 10;
		this.hp = 3;
		this.speed = 2;
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
		this.hp --;
		if(hp<=0) 
		{
			die();
			Player.modifyCash(5);
		}
	}
	
	
	
	
	
	
	

	
}
