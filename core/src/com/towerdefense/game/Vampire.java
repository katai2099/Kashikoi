package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Vampire extends Monster{

	Vampire(Texture texture, Tile startile, int height, int width, int atk, int speed) {
		super(texture, startile, height, width, atk, speed);
		this.hp = 10;
	}
	
	public void regen()
	{
		hp+=50;
	}

}
