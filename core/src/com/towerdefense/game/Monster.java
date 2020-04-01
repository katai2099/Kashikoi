package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Monster extends Sprite{
	float x;
	float y;
	float height;
	float width;
	int speed;
	int atk;
	int armor;
	int hp;
	Texture texture;
	Tile startile;
	Tile currentTile;
	boolean attack;
	Map map;
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setCurrentTile(Tile currentTile) {
		this.currentTile = currentTile;
	}

	public Tile getStartile() {
		return startile;
	}

	public void setStartile(Tile startile) {
		this.startile = startile;
	}
	public void updateCurrentTile()
	{
		currentTile = map.getTile((int)this.x/64, (int)this.y/64);
	}
	
	Monster(Texture texture,Tile startile,Map map,float height,float width,int atk,int speed)
	{
		this.texture=texture;
		this.x = startile.getX();
		this.y = startile.getY();
		this.height = height;
		this.width = width;
		this.atk = atk;
		this.speed = speed;
		this.startile=startile;
		this.hp = 10;
		this.map = map;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void move()
	{
		if(x<=1280 && y<=1024 && y>=64)
		{
			x = x+speed;
		}
		updateCurrentTile();
	}
	
	public boolean attackYet(Castle castle)
	{
		return castle.getX()==this.getX() && castle.getY()==this.getY();
	}

	public void moveUp() {
		{
			y = y+speed;
		}
		
	}
	
	public boolean isDead()
	{
		return hp<=0;
	}
	
	public void gotShot(int atk)
	{
		this.hp -= atk;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void attack(Castle castle)
	{
		
	}
	
	public Boolean isAttack()
	{
		return attack;
	}
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}

	public void current()
	{
		System.out.println(currentTile.getX() + " " + currentTile.getY() );
	}

	
}
