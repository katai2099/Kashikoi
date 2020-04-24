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
	boolean increase;
	public WaveManager(Monster monster,float timeBetweenMonster,int monstersPerWave,int numberOfWave)
	{
		this.numberOfWave = numberOfWave;
		this.monster = monster;
		this.timeBetweenMonster = timeBetweenMonster;
		this.monstersPerWave = monstersPerWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		newWaveSingle();
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
		increase = false;
	}
	
	public void update()
	{
		if(waveNumber == numberOfWave && currentWave.isCompleted())
		{
			Player.win = true;
			Player.end = true;
		}else {
			if(waveNumber % 3 == 0 && !increase) {monstersPerWave++; increase = true;} 
			if(waveNumber % 3 != 0) increase = false;
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
			if(monsters.length==5)
			currentWave = new Wave(0,monsters[4],1);
			else if(monsters.length==10)
				currentWave = new Wave(0,monsters[4],monsters[9],2);
		}
		else
		currentWave = new Wave(timeBetweenMonster,monsters,monstersPerWave);
		waveNumber++;
		System.out.println("wave " +waveNumber);
	}
	
	//single enemy
	public void newWaveSingle()
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
