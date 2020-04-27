package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Castle extends Sprite{
	int hp;
	Texture texture;
	float x;
	float y;
	int width;
	int height;
	Tile startTile;
	float barWidth ;
	
	//for testing
	public Castle()
	{
		this.hp = 100;
		this.barWidth = 2680;
	}
	
	//for map testing
	public Castle(int x, int y, int width,int height)
	{
		this.hp = 100;
		this.barWidth = 2680;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
	} 

	//Program Constructor
	public Castle(Texture texture, int x, int y, int width, int height) {	
		this.hp = 100;
		this.barWidth = 2680;
		this.texture=texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		
	}


	
	public boolean gotattacked(Monster monster)
	{
		return this.x/64==monster.getCurrentTile().getmapX() && (this.y-64)/64 == monster.getCurrentTile().getmapY();
	}
	
	//decreaseHp of castle base on monster atkpoint 
	public void decreasehp(int atk)
	{
		this.hp -= atk;
		barWidth -= (atk*2680)/100;
	}
	
	//check whether castle is destroy or not
	public boolean isDestroy()
	{
		return hp <= 0;
	}
	
	//draw method 
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public float getBarWidth()
	{
		return barWidth;
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

	
	
}
