package com.towerdefense.game;

import java.util.ArrayList;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
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
	float dt;
	float previousSpawnTime=0;
	Random random = new Random();
	Map map;
	boolean waveCompleted;
	int cnt;
	int cntEnemySpawn;
	

	public Wave(float spawnTime,Monster[] monster,int monstersPerWave)
	{
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		monsters = new ArrayList<Monster>();
		this.monstersPerWave = monstersPerWave;
		this.waveCompleted = false;
		this.monster = monster;
		cnt = monstersPerWave;
		cntEnemySpawn = monstersPerWave-1;
		dt = Gdx.graphics.getDeltaTime();
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
		cnt = monstersPerWave;
		cntEnemySpawn = monstersPerWave-1;
		dt = Gdx.graphics.getDeltaTime();
		Spawn();
		
	}
	
	
	public void Update()
	{
		dt = Gdx.graphics.getDeltaTime();
		if(dt > 1.2f) dt = 1;
		boolean allDead = true;
		
		timeSinceLastSpawn += dt;
		if(monsters.size()<monstersPerWave && cntEnemySpawn>0) 
		{
			if(timeSinceLastSpawn > spawnTime )
			{
				cntEnemySpawn--;
				Spawn();
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
			else
				{monsters.remove(i);cnt--;}
		
		}
		if(cnt==0 && allDead) waveCompleted = true;
	}
	
	//multiple enemies
	public void Spawn()
	{
		if(monstersPerWave==1)
		{
			monsters.add(new SekiroDieTwice(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		}
		else {
		int n=random.nextInt(4);
		
		if(n==0)  	  monsters.add(new Giant(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==1) monsters.add(new Fugu(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==2) monsters.add(new Squirrel(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==3) monsters.add(new Onion(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));}
	//	else if(n==4) monsters.add(new SekiroDieTwice(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
	}
	/*
	//single enemy
	public void Spawn()
	{
		if(monster1 instanceof Giant) monsters.add(new Giant(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		else if(monster1 instanceof Fugu ) monsters.add(new Fugu(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		else if(monster1 instanceof Squirrel) monsters.add(new Squirrel(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		else if(monster1 instanceof Onion) monsters.add(new Onion(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		else if(monster1 instanceof SekiroDieTwice) monsters.add(new SekiroDieTwice(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
	}*/
	
	
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
