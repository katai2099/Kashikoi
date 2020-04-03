package com.towerdefense.game;

public class WaveManager {
	
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
	}
	
	
	
}
