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
	
	public Ammo(Texture texture,Monster target,float x,float y,int width,int height)
	{
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height=height;
		this.target = target;
		this.xVelocity=0f;
		this.xVelocity=0f;
		calculateDirection() ;
	}

	public void calculateDirection() 
	{
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX()-x);
		float yDistanceFromTarget = Math.abs(target.getY()-y);
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

	public void setTexture(Texture texture) {
		this.texture = texture;
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
	
	public void move(float speed,float timeSinceShoot)
	{
		x += xVelocity*speed*timeSinceShoot;
		y += yVelocity*speed*timeSinceShoot;
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

	public void setTarget(Monster target) {
		this.target = target;
	}
	
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth()/2+5,getHeight()/2+5);
	}

}
