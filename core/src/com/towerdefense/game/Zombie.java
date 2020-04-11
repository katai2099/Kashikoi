package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Zombie extends Monster{
	
	boolean isRevive ;
	int originalHp;
	Zombie(Texture texture, Tile startile, int height, int width, int atk, int speed) {
		super(texture, startile, height, width, atk, speed);
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
