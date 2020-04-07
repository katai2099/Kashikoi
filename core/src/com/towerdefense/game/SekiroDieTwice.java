package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class SekiroDieTwice extends Monster{

	boolean isRevive;
	SekiroDieTwice(Texture texture, Tile startile,Map map, int height, int width, int atk, int speed) {
		super(texture, startile, map, height, width, atk, speed);
		this.hp = 50;
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
