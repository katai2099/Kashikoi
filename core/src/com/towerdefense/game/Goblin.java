package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Goblin extends Monster{

	Goblin(Texture texture, Tile startile, int height, int width, int atk, int speed) {
		super(texture, startile, height, width, atk, speed);
		// TODO Auto-generated constructor stub
		this.hp = 10;
	}

}
