package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class SekiroDieTwice extends Monster{

	SekiroDieTwice(Texture texture, Tile startile,Map map, int height, int width, int atk, int speed) {
		super(texture, startile, map, height, width, atk, speed);
		this.hp = 10;
	}
	
	public void regen()
	{
		hp+=50;
	}

}
