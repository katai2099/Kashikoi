package com.towerdefense.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WaveManager extends Sprite {
	
	float timeSinceLastWave;
	float timeBetweenMonster;
	int waveNumber;
	int monstersPerWave;
	Monster monster;
	Wave currentWave;
	
	public WaveManager(Monster monster,float timeBetweenMonster,int monstersPerWave)
	{
		
		this.monster = monster;
		this.timeBetweenMonster = timeBetweenMonster;
		this.monstersPerWave = monstersPerWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		newWave();
	}
	
	public void update()
	{
		if(!currentWave.isCompleted())
		{
			currentWave.Update();
		}
		else 
			newWave();
	}
	
	public void newWave()
	{
		currentWave = new Wave(timeBetweenMonster,monster,monstersPerWave);
		waveNumber++;
		System.out.println("wave " +waveNumber);
	}
	
	public void draw(Batch b)
	{
		currentWave.draw(b);
	}
	

	public Wave getCurrentWave() {
		return currentWave;
	}
	
	
	
}
