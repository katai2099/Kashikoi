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
			//every 3rd wave we will increase number of monster per wave by one (2 in two entry map)
			if(waveNumber % 3 == 0 && !increase) {
				if(monsters.length==5) {
				monstersPerWave++; increase = true;}
				else if(monsters.length==10) {
					monstersPerWave+=2; increase = true;}
				} 
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
		/*check if waveNumber is the last wave 
		if so we spawn boss 
		else we spawn normal monster
		*/
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
	}
	
	//single enemy used to spawn boss
	public void newWaveSingle()
	{
		currentWave = new Wave(timeBetweenMonster,monster,monstersPerWave);
		waveNumber++;
	}
	
	public void draw(Batch b)
	{
		currentWave.draw(b);
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	
	
}
