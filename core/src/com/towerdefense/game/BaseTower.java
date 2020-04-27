package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseTower extends Sprite{
	
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Texture texture;
	protected int exp;
	protected int level; 
	protected int cost; 
	protected int range; 
	protected float damage;
	protected int refund;
	protected float attackSpeed;
	protected ArrayList<Ammo> ammos;
	protected ArrayList<Monster>monsters;
	protected Tile tile;
	protected float timeSinceShoot;
	protected float previousShootTime;
	protected Monster target;
	protected boolean lockOn;
	protected Texture cannon;
	protected float dt;
	protected boolean shootOnce;
	protected BitmapFont attackValue;
	protected boolean earnExp;
	protected boolean first;
	
	BaseTower()
	{
		monsters = new ArrayList<Monster>();
		lockOn = false;
		this.x=0;
		this.y=0;
		this.exp=0;
		shootOnce = false;
	}
	
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
		this.attackSpeed = 3;
		this.cannon = new Texture("fire1.png");
		this.lockOn = false;
		this.range = 1000;
		this.damage = damage;
		dt = Gdx.graphics.getDeltaTime();
	}
	
	BaseTower(Texture texture,Tile tile,int width,int height,ArrayList<Monster>monsters)
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
		this.attackSpeed = 3;
		this.cannon = new Texture("fire1.png");
		this.lockOn = false;
		this.range = 1000;
		dt = Gdx.graphics.getDeltaTime();
		this.shootOnce = false; 
		attackValue = new BitmapFont();
		attackValue.setColor(Color.RED);
		earnExp = false;
		first = true;
		
	}
	
	//aim for closest monster in the wave
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
	
	
	//check whether the monster is in range of attack
	public boolean isInRange(Monster m)
	{
		float xDistance = Math.abs((m.getX()+32)-(this.x+32));
		float yDistance = Math.abs((m.getY()+32)-(this.y+32));
		if(xDistance <= range && yDistance <= range)
			return true;
		return false;
	}
	
	//calculate distance from tower to monster 
	public float findDistance(Monster m)
	{
		float xDistance = Math.abs(m.getX()-this.x);
		float yDistance = Math.abs(m.getY()-this.y);
		return xDistance + yDistance;
	}
	
	//use to update list of monster in the wave
	public void updateMonsterList(ArrayList<Monster>list)
	{
		monsters = list;
	}
	
	public void update()
	{
		//update only when there is monster in the wave 
		if(monsters.size()!=0)
		{
		if(!lockOn)
		{
			target = aimTarget();
		}
		if(first && target!=null)
		{
			shoot();
			first = false;
		}
		if(target == null || target.isAlive() == false || !isInRange(target))
		{
			lockOn = false; 
		}
		if(target!=null) {
		if( shootOnce==true && !target.enterCastle && this.exp <100 && !target.isAlive())
		{
			increaseExp(target.giveExp);
		}	
		}
		
		dt = Gdx.graphics.getDeltaTime();
		if(dt>1.5f) dt = 1 ;
		timeSinceShoot += dt;
		if(timeSinceShoot>attackSpeed)
		{
			shoot();
			shootOnce = true ;
		}
		}
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).update();
			if(ammos.get(i).alive==false) ammos.remove(i);
		}
	}
	
	//add ammo to list of ammos
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null)
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12));
	}

	//Draw method
	public void draw(Batch b)
	{
		b.draw(this.getTexture(),getX(),getY(),getWidth(),getHeight());
		attackValue.draw(b,"Dm: " +  String.valueOf(this.damage),getX()+25,getY()+80);
		attackValue.draw(b, "Exp: " + String.valueOf(this.exp),getX()-30,getY()+40);
		attackValue.draw(b,"L: " +  String.valueOf(this.lockOn),getX()-26,getY()+12);
		attackValue.draw(b,"S:" + String.valueOf(this.shootOnce),getX()+80,getY()+12);
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).draw(b);
		}
	}
	
	//getXposition on the map (not Screen)
	public int getmapX()
	{
		return (int) x/64;
	}
	//getYposition on the map (not Screen)
	public int getmapY()
	{
		return (int) (y-64)/64;
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public void damageMonster(Monster monster)
	{
		
	}
	
	public void reduceHiddenhealth(Monster monster)
	{
		
	}
	
	public int getRefund() {
		return refund;
	}
	
	public void increaseExp(int exp)
	{
		if(this.exp<100) this.exp+=exp;
		if(this.exp>100) this.exp=100;
		shootOnce=false ;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
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
	public float getAttckSpeed() {
		return attackSpeed;
	}

	

}
