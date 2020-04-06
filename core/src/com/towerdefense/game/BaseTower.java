package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseTower {
	
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Texture texture;
	protected int attack;
	protected int exp;
	protected int level; 
	protected int cost; 
	protected int range; 
	protected int damage;
	protected int attackSpeed;
	protected ArrayList<Ammo> ammos;
	protected ArrayList<Monster>monsters;
	protected Tile tile;
	protected float timeSinceShoot;
	protected float previousShootTime;
	long start = System.currentTimeMillis();
	protected Monster target;
	protected Boolean lockOn;
	protected Texture cannon;
	
	
	
	BaseTower(Texture texture,Tile tile,int width,int height,int damage,ArrayList<Monster>monsters)
	{
		this.texture = texture ;
		this.x = tile.getX();
		this.y = tile.getY();
		this.width = width;
		this.height = height;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.monsters = monsters;
		this.attackSpeed = 2;
		this.cannon = new Texture("fire1.png");
		this.lockOn = false;
		this.range = 1000;
		this.damage = damage;
		
	}
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public ArrayList<Ammo> getAmmos() {
		return ammos;
	}
	
		
	public int getAttack() {
		return attack;
	}
	
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getAttckSpeed() {
		return attackSpeed;
	}
	
	public int getmapX()
	{
		return (int) x/64;
	}
	
	public int getmapY()
	{
		return (int) (y-64)/64;
	}
	
	Monster aimTarget()
	{
		Monster closest = null;
		float closestDistance = 10000;
		for(Monster m:monsters)
		{
			if (isInRange(m)&&findDistance(m) < closestDistance && m.isAlive())
			{
				closestDistance = findDistance(m);
				closest = m;
			}
		}
		if(closest != null)
			lockOn = true;
		return closest;
	}
	
	
	public boolean isInRange(Monster m)
	{
		float xDistance = Math.abs(m.getX()-this.x);
		float yDistance = Math.abs(m.getY()-this.y);
		if(xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	public float findDistance(Monster m)
	{
		float xDistance = Math.abs(m.getX()-this.x);
		float yDistance = Math.abs(m.getY()-this.y);
		return xDistance + yDistance;
	}
	
	public void updateMonsterList(ArrayList<Monster>list)
	{
		monsters = list;
	}
	
	
	
	public void update()
	{
		if(!lockOn)
		{
			target = aimTarget();
		}
		if(target == null || target.isAlive() == false)
		{
			lockOn = false; 
		}
		timeSinceShoot = ((System.currentTimeMillis()-start)/1000);
		if(timeSinceShoot-previousShootTime > attackSpeed)
		{
			shoot();
			previousShootTime = timeSinceShoot;
			timeSinceShoot = 0;
		
		}
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).update(attackSpeed,previousShootTime);
			if(ammos.get(i).alive==false) ammos.remove(i);
		}
		
	
	}
	
	public void shoot()
	{
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage));
	}

	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).draw(b);
		}
	}
	
	

}
