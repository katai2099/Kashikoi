package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class Giant extends Monster{

	Giant(Texture texture, Tile startile,Map map, int height, int width, int atk, int speed) {
		super(texture, startile, map, height, width, atk, speed);
		this.hp = 10;
	}

}
