package com.towerdefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Ammo {
	
	protected Texture texture;
	protected float x;
	protected float y; 
	protected float xVelocity , yVelocity;
	int width;
	int height;
	Monster target;
	boolean alive;
	int damage;
	
	public Ammo(Texture texture,Monster target,float x,float y,int width,int height,int damage)
	{
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height=height;
		this.target = target;
		this.damage = damage;
		this.xVelocity=0f;
		this.xVelocity=0f;
		calculateDirection() ;
		this.alive = true;
	}

	public void calculateDirection() 
	{
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX()-x+25);
		float yDistanceFromTarget = Math.abs(target.getY()-y+25);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		xVelocity = xDistanceFromTarget / totalDistanceFromTarget;
		yVelocity = totalAllowedMovement - (xDistanceFromTarget / totalDistanceFromTarget);
		if(target.getX() < x)
		{
			xVelocity *= -1;
		}
		if(target.getY() < y)
		{
			yVelocity *= -1;
		}
	}
	
	public Texture getTexture() {
		return texture;
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

	public int getHeight() {
		return height;
	}
	
	public void update(float speed,float timeSinceShoot)
	{
		x += xVelocity*speed*timeSinceShoot;
		y += yVelocity*speed*timeSinceShoot;
		
		if(this.x + this.width >= target.getX()
				&& target.getX()+target.getWidth() >= this.x
				&& this.y + this.height >= target.getY()
				&& target.getY()+target.getHeight() > this.y )
		{
			target.damage(damage);
			this.alive = false;
		}
		
	}

	public float getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public float getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	public Monster getTarget() {
		return target;
	}

		
	public void draw(Batch b)
	{
		if(this.alive)
		b.draw(getTexture(),getX(),getY(),getWidth()/2+8,getHeight()/2+8);
	}

}
