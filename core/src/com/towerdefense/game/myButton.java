package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class myButton extends Sprite{
	
	Texture texture;
	int x;
	int y;
	int width;
	int height;
	String name;
	
	public myButton(Texture texture, int x, int y, int width, int height, String name) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public myButton(String name,Texture texture,int x,int y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.name = name;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}

	public Texture getTexture() {
		return texture;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
}
