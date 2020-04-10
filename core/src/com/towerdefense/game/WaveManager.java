package com.towerdefense.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WaveManager extends Sprite {
	int numberOfWave;
	float timeSinceLastWave;
	float timeBetweenMonster;
	int waveNumber;
	int monstersPerWave;
	Monster monster;
	Monster monsters[];
	Wave currentWave;
	
	public WaveManager(Monster monster,float timeBetweenMonster,int monstersPerWave,int numberOfWave)
	{
		this.numberOfWave = numberOfWave;
		this.monster = monster;
		this.timeBetweenMonster = timeBetweenMonster;
		this.monstersPerWave = monstersPerWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		newWave();
	} 
	
	public WaveManager(Monster monsters[],float timeBetweenMonster,int monstersPerWave,int numberOfWave)
	{
		this.numberOfWave = numberOfWave;
		this.monsters = monsters;
		this.timeBetweenMonster = timeBetweenMonster;
		this.monstersPerWave = monstersPerWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		newWave();
	}
	
	public void update()
	{
		if(waveNumber == numberOfWave && currentWave.isCompleted())
		{
			Player.win = true;
			Player.end = true;
		}else {
		if(!currentWave.isCompleted())
		{
			currentWave.Update();
		}
		else
		{	
			newWave();
		}
		}
	}
	//Multiple enemies
	public void newWave()
	{
		if(waveNumber==numberOfWave-1)
		{
			currentWave = new Wave(0,monsters[4],1);
		}
		else
		currentWave = new Wave(timeBetweenMonster,monsters,monstersPerWave);
		waveNumber++;
		System.out.println("wave " +waveNumber);
	}
	/*
	//single enemy
	public void newWave()
	{
		currentWave = new Wave(timeBetweenMonster,monster,monstersPerWave);
		waveNumber++;
		System.out.println("wave " +waveNumber);
	}*/
	
	public void draw(Batch b)
	{
		currentWave.draw(b);
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	
	
}
