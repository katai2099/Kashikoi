package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	
	private Map map;
	private boolean leftMousePressed;
	ArrayList<BaseTower>towers;
	
	public Player(Map map)
	{
		this.map = map;
		this.leftMousePressed = false;
		towers = new ArrayList<BaseTower>();
	}
	
	public void update()
	{
		if(Gdx.input.isButtonJustPressed(0)&&!leftMousePressed)
		{
			towers.add(new BaseTower(new Texture("ice tower.jpg"), map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)), 64, 64));
		}
		leftMousePressed = Gdx.input.isButtonJustPressed(0);
	}
	
	public void draw(Batch b)
	{
		for(BaseTower t : towers)
		{
			b.draw(t.getTexture(),t.getX(),t.getY(),t.getWidth(),t.getHeight());
		}
	}
	
}
