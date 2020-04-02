package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Onion extends Monster{
	
	boolean isRevive ;
	int originalHp;
	Onion(Texture texture, Tile startile,Map map, int height, int width, int atk, int speed) {
		super(texture, startile, map, height, width, atk, speed);
		isRevive = false; 
		originalHp = this.hp;
		this.hp = 10;
	}

	public void revive()
	{
		if(isDead()&&!(isRevive))
		{
			hp = originalHp/2;
			isRevive = true;
		}
	}
	
}
