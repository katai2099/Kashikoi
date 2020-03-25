package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Castle extends Sprite{
	int hp = 2;
	Texture texture;
	float x;
	float y;
	int width;
	int height;
	Tile startTile;
	
	
	public Castle(Texture texture,Tile startTile,int width,int height)
	{
		this.texture = texture;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}
	
	public void gotattacked(Monster monster)
	{
		if(monster.getX()==this.getX() && monster.getY()==this.getY())
		{
			hp -= monster.getAtk();
			System.out.println("I am dead " + hp +" "+ x + " " + y );
		}
	}
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}
	
}
