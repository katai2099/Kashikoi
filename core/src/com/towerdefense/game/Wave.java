package com.towerdefense.game;

import java.util.ArrayList;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Wave extends Sprite {
	private float timeSinceLastSpawn,spawnTime;
	private Monster[] monster;
	private ArrayList<Monster> monsters;
	 long start = System.currentTimeMillis();
	float previousSpawnTime=0;
	Random random = new Random();
	Map map;
	
	
	public float getTimeSinceLastSpawn() {
		return timeSinceLastSpawn;
	}

	public Wave(float spawnTime,Monster[] monster,Map map)
	{
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		monsters = new ArrayList<Monster>();
		this.monster = monster;
		this.map = map;
	}
	
	
	public void Update()
	{
		timeSinceLastSpawn = ((System.currentTimeMillis()-start)/1000);
		if(timeSinceLastSpawn-previousSpawnTime > spawnTime)
		{
			Spawn();
			previousSpawnTime = timeSinceLastSpawn;
			//System.out.println(previousSpawnTime);
			timeSinceLastSpawn = 0;
		}
		for(int i=0;i<monsters.size();i++)
		{
			monsters.get(i).move();
		}
	//	System.out.println(timeSinceLastSpawn);
	}
	
	public void Spawn()
	{
		int n=random.nextInt(2);
		monsters.add(new Monster(monster[n].getTexture(),monster[n].getStartile(),monster[n].getMap(),monster[n].getHeight(),monster[n].getWidth(),monster[n].getAtk(),monster[n].getSpeed()));
	}
	
	public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
		this.timeSinceLastSpawn = timeSinceLastSpawn;
	}

	public float getSpawnTime() {
		return spawnTime;
	}

	public void setSpawnTime(float spawnTime) {
		this.spawnTime = spawnTime;
	}

	public Monster[] getMonster() {
		return monster;
	}

	public void setMonster(Monster[] monster) {
		this.monster = monster;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}
	
	public void draw(Batch b)
	{
		for(int i=0;i<monsters.size();i++)
		{
			monsters.get(i).draw(b);
		} 
	}
	
}
