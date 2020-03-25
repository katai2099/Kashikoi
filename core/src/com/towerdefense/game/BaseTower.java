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
	protected String attackType;
	protected int exp;
	protected int level; 
	protected int cost; 
	protected int range; 
	protected int attackSpeed;
	protected ArrayList<Ammo> ammos;
	protected Tile tile;
	protected float timeSinceShoot;
	protected float previousShootTime;
	long start = System.currentTimeMillis();
	protected Wave target;
	public boolean shot=false;
	BaseTower(Texture texture,Wave target,Tile tile,int width,int height)
	{
		this.texture = texture ;
		this.x = tile.getX();
		this.y = tile.getY();
		this.width = width;
		this.height = height;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.target = target;
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

	public void setAmmos(ArrayList<Ammo> ammos) {
		this.ammos = ammos;
	}
		
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public String getAttackType() {
		return attackType;
	}
	public void setAttackType(String attackType) {
		this.attackType = attackType;
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
	public void setAttckSpeed(int attckSpeed) {
		this.attackSpeed = attckSpeed;
	}
	
	public void update()
	{
		
	}
	
	public void update(Wave e)
	{
		
	}
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}
	
	public void shoot()
	{
		
	}
	public void shoot(Wave m)
	{
		m.getMonsters().get(0).gotShot(this.attack);
	}
	

}
