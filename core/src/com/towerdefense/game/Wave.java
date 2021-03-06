package com.towerdefense.game;

import java.util.ArrayList;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Wave extends Sprite {
	private float timeSinceLastSpawn;
	float spawnTime;
	int monstersPerWave;
	private Monster[] monster;
	private Monster monster1;
	private Monster boss2;
	private ArrayList<Monster> monsters;
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
		if(monster.length==5)
		cntEnemySpawn = monstersPerWave-1;
		else if(monster.length==10)
			cntEnemySpawn = monstersPerWave -2;
		dt = Gdx.graphics.getDeltaTime();
		Spawn();
		
		
	} 
	//use to spawn boss type of monster
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
	//use to spawn boss type of monster in map with 2 entry
	public Wave(float spawnTime,Monster boss1,Monster boss2,int monstersPerWave)
	{
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		monsters = new ArrayList<Monster>();
		this.monster1 = boss1;
		this.boss2 = boss2;
		this.monstersPerWave = monstersPerWave;
		this.waveCompleted = false;
		cnt = monstersPerWave;
		cntEnemySpawn = monstersPerWave-2;
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
			//spawn enemy and reduce number of enemy needed per wave
			if(timeSinceLastSpawn > spawnTime )
			{
			if(monster.length==5)	cntEnemySpawn--;
			else if(monster.length==10) cntEnemySpawn -= 2;
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
		//if all monsters are dead and number of monster needed per wave is reached then wave is completed
		if(cnt==0 && allDead) waveCompleted = true;
	}
	
	//spawn multiple enemies randomly
	public void Spawn()
	{
		
		if(monstersPerWave==1)
		{
			monsters.add(new SekiroDieTwice(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
		}
		else if(monstersPerWave==2)
		{
			monsters.add(new SekiroDieTwice(monster1.getStartile(),monster1.getMap(),monster1.getHeight(),monster1.getWidth()));
			monsters.add(new SekiroDieTwice(boss2.getStartile(),boss2.getMap(),boss2.getHeight(),boss2.getWidth()));
		}
		else {
			if(monster.length==5) 
			{
		int n=random.nextInt(4);
		
		if(n==0)  	  monsters.add(new Giant(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==1) monsters.add(new Fugu(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==2) monsters.add(new Squirrel(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		else if(n==3) monsters.add(new Onion(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
		}	
		else if(monster.length==10)
		{
			
			int n=random.nextInt(4);
			
			if(n==0)  	  monsters.add(new Giant(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
			else if(n==1) monsters.add(new Fugu(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
			else if(n==2) monsters.add(new Squirrel(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
			else if(n==3) monsters.add(new Onion(monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth()));
			
			int m=random.nextInt(4)+5;
			
			if(m==5)  	  monsters.add(new Giant(monster[m].getStartile(),monster[m].getMap(),monster[m].getHeight(),monster[m].getWidth()));
			else if(m==6) monsters.add(new Fugu(monster[m].getStartile(),monster[m].getMap(),monster[m].getHeight(),monster[m].getWidth()));
			else if(m==7) monsters.add(new Squirrel(monster[m].getStartile(),monster[m].getMap(),monster[m].getHeight(),monster[m].getWidth()));
			else if(m==8) monsters.add(new Onion(monster[m].getStartile(),monster[m].getMap(),monster[m].getHeight(),monster[m].getWidth()));
			
		}
		}	
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
