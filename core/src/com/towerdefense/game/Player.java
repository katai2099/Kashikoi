package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player {
	
	private Map map;
	private boolean leftMousePressed;
	ArrayList<BaseTower>towers;
	BaseTower tmpTower;
	Boolean holdingTower;
	public static int cash;
	WaveManager waveManager;
	Boolean TowerSelected;
	BaseTower tmpSelectedTower;
	
	public Player(Map map,WaveManager waveManager)
	{
		this.map = map;
		this.leftMousePressed = false;
		towers = new ArrayList<BaseTower>();
		cash = 100;
		this.holdingTower = false;
		this.waveManager = waveManager;
		this.TowerSelected = false;
	}
	
	
	
	public static int getCash() {
		return cash;
	}

	public static boolean modifyCash(int cost)
	{
		if(cash + cost>= 0)
		{
			cash += cost;
			return true;
		}
		return false;
		
	}
	
	public void pickTower(BaseTower t)
	{
		tmpTower = t;
		holdingTower= true;
	}
	
	
	public void update()
	{
		waveManager.update();
		for(BaseTower t:towers)
		{
			t.update();
			t.updateMonsterList(waveManager.getCurrentWave().getMonsters());
		}
		
		
		if(holdingTower)
		{
			tmpTower.setX(map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)).getX());
			tmpTower.setY(map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)).getY());
		}
		if(holdingTower)
		{
			if(Gdx.input.isButtonJustPressed(0)&&!leftMousePressed)
			{
				if(modifyCash(-50)) {
				towers.add(tmpTower);
				holdingTower = false;
				tmpTower = null;}
				else 
					{
					holdingTower = false;
					tmpTower = null;
					}
			}
		}
		leftMousePressed = Gdx.input.isButtonJustPressed(0);
	}
	
	public boolean isTowerClick()
	{
		float MouseX = Gdx.input.getX();
		float MouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
		
		for(BaseTower t:towers)
		{
			if(MouseX > t.getX() && MouseX < t.getX() + t.getWidth() &&
					MouseY > t.getY() && MouseY < t.getY() + t.getHeight()) 
				{
				tmpSelectedTower = t;
				return true;
				}
		}
		return false;
	}
	
	public void draw(Batch b)
	{
		for(Monster m: waveManager.getCurrentWave().getMonsters())
		{
			m.draw(b);
		}
		for(BaseTower t : towers)
		{
			t.draw(b);
		}
		if(holdingTower)
		{
			tmpTower.draw(b);
		}
	}
	
}
