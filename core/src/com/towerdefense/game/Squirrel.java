package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Squirrel extends Monster{

	Squirrel(Texture texture, Tile startile,Map map, int height, int width, int atk, int speed) {
		super(texture, startile, map, height, width, atk, speed);
		// TODO Auto-generated constructor stub
		this.hp = 10;
	}

}
