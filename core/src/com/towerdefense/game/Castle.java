package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Castle extends Sprite{
	int hp = 100;
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

	public Castle(Texture texture, int x, int y, int width, int height) {
		this.texture=texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		
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
	
	public boolean gotattacked(Monster monster)
	{
		/*return this.x <= monster.getX()
				&& monster.getX()+monster.getWidth() < this.x+this.width 
				&& this.y <= monster.getY()
				&& monster.getY()+monster.getHeight() < this.y+this.height;*/
	/*	return (int)(this.x/64)-1 == monster.getCurrentTile().getmapX()&&(int)(this.y-64)/64==monster.getCurrentTile().getmapY()
				|| (int) ((this.y/64)-64)-1 == monster.getCurrentTile().getmapY()  && (int) (this.x/64) == monster.getCurrentTile().getmapX() 
				|| (int) (this.x/64)+1 == monster.getCurrentTile().getmapX() && (int) ((this.y)-64/64) == monster.getCurrentTile().getmapX() 
				|| (int)((this.y-64)/64)+1 == monster.getCurrentTile().getmapY() && (int) (this.x/64) == monster.getCurrentTile().getmapX() ;*/
		return this.x/64==monster.getCurrentTile().getmapX() && (this.y-64)/64 == monster.getCurrentTile().getmapY();
	}
	
	public void decreasehp(Monster monster)
	{
		this.hp -= monster.getAtk();
	}
	
	public boolean isDestroy()
	{
		return hp <= 0;
	}
	
	
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

	
	
}
