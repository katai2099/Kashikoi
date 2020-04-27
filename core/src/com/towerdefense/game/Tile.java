package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile extends Sprite{
	private int x ;
	private int y ;
	private int width;
	private int height; 
	private Texture texture; 
	private TileType tiletype;
	
	
	public Tile(Texture texture,TileType tiletype,int x,int y,int width,int height)

	{
		super(texture);
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height;
		this.tiletype = tiletype;
	}
	
	
	//for testing (Texture is not needed)
	
	public Tile(TileType tiletype,int x,int y,int width,int height)
	{
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height;
		this.tiletype = tiletype;
	
	}
	public TileType getTiletype() {
		return tiletype;
	}
	public void setTiletype(TileType tiletype) {
		this.tiletype = tiletype;
	}

	public float getX() {
	
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public int getmapX()
	{
		return (int) x/64;
	}
	
	public int getmapY()
	{
		return (int) (y-64)/64;
	}

	public void setY(int y) {
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}
	
}
