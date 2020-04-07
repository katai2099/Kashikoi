package com.towerdefense.game;

import java.util.ArrayList;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Wave extends Sprite {
	private float timeSinceLastSpawn;
	float spawnTime;
	int monstersPerWave;
	private Monster[] monster;
	private Monster monster1;
	private ArrayList<Monster> monsters;
	 long start = System.currentTimeMillis();
	float previousSpawnTime=0;
	Random random = new Random();
	Map map;
	boolean waveCompleted;
	

	public Wave(float spawnTime,Monster[] monster,int monstersPerWave)
	{
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		monsters = new ArrayList<Monster>();
		this.monstersPerWave = monstersPerWave;
		this.waveCompleted = false;
		this.monster = monster;
		Spawn();
		
		
	} 
	
	public Wave(float spawnTime,Monster monster,int monstersPerWave)
	{
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		monsters = new ArrayList<Monster>();
		this.monster1 = monster;
		this.monstersPerWave = monstersPerWave;
		this.waveCompleted = false;
		Spawn();
	}
	
	
	public void Update()
	{
		boolean allDead = true;
		timeSinceLastSpawn = ((System.currentTimeMillis()-start)/1000);
		if(monsters.size()<monstersPerWave) {
		if(timeSinceLastSpawn-previousSpawnTime > spawnTime)
		{
			Spawn();
			previousSpawnTime = timeSinceLastSpawn;
			timeSinceLastSpawn = 0;
		}
		}
		
		for(int i=0;i<monsters.size();i++)
		{
			
			if(monsters.get(i).isAlive())
			{
				allDead = false;
				monsters.get(i).update();
			}
		}
		if(allDead) waveCompleted = true;

	}
	
	public void Spawn()
	{
		int n=random.nextInt(5);
	//	monsters.add(new Monster(monster[n].getTexture(),monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth(),monster[n].getAtk(),monster[n].getSpeed()));
		//monsters.add(new Monster(monster1.getTexture(),monster1.getStartile(),monster1.getMap(),64,64,monster1.getAtk(),monster1.getSpeed()));
		if(n==0)  	  monsters.add(new Giant(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==1) monsters.add(new Fugu(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==2) monsters.add(new Squirrel(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==3) monsters.add(new Onion(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==4) monsters.add(new SekiroDieTwice(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		
		
	}
	
	public float getTimeSinceLastSpawn() {
		return timeSinceLastSpawn;
	}
	
	public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
		this.timeSinceLastSpawn = timeSinceLastSpawn;
	}

	public float getSpawnTime() {
		return spawnTime;
	}

	public Monster[] getMonster() {
		return monster;
	}
	
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	
	public boolean isCompleted()
	{
		return waveCompleted;
	}
	
	public void draw(Batch b)
	{
		for(int i=0;i<monsters.size();i++)
		{
			monsters.get(i).draw(b);
		} 
	}
	
	
	
}
