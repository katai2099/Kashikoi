package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FireTower2 extends FireTower{

		ArrayList<Monster>targets;
		
	
	FireTower2(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.x = tile.getX();
		this.y = tile.getY();
		this.cannon = new Texture("fireProjectile.png");
		this.damage = 10;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 2f;
		this.lockOn = false;
		this.range = 1000;
		this.refund = 50;
		dt = Gdx.graphics.getDeltaTime();
		targets = new ArrayList<Monster>();
		
	}
	
	@Override
	Monster aimTarget()
	{
		Monster closest = null;
		float closestDistance = 10000;
		ArrayList<Monster> tmp = new ArrayList<Monster>();
		for(int i=0;i<monsters.size();i++)
		{
			tmp.add(monsters.get(i));
		}
		
		for(int i=0;i<targets.size();i++)
		{
			for(int j=0;j<tmp.size();j++)
			{
				if(targets.get(i).idNumber == tmp.get(j).idNumber)
				{
					//System.out.println("i have remove " +tmp.get(j).idNumber);
					tmp.remove(j);
				}
			}
		}
		for(int i=0;i<tmp.size();i++)
		{
				if	(isInRange(tmp.get(i)) 
						&& findDistance(tmp.get(i)) < closestDistance && tmp.get(i).getHiddenHealth() > 0)
					{
						closestDistance = findDistance(tmp.get(i));
						closest = tmp.get(i);						
					}
			//	System.out.println(tmp.get(i).idNumber);
		}
		//if (closest != null) // System.out.println("I have take this ID " +closest.idNumber +" into targets lists");
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
		if(monsters.size()!=0)
		{
			
		if(targets.size()<3)
		targets.add(aimTarget());
			
		for(int i=0;i<targets.size();i++)
		{
			if(targets.get(i) == null || targets.get(i).isAlive() == false )
			{
				targets.remove(i);
			}
		}
	/*	if(lockOn == false && shootOnce==true && !target.enterCastle() && this.exp <100)
		{
			this.exp += target.giveExp;
			if(exp>100) exp = 100;
			shootOnce = false;
		}	*/
		
		dt = Gdx.graphics.getDeltaTime();
		if(dt>1.5f) dt = 1 ;
		timeSinceShoot += dt;
		if(timeSinceShoot>attackSpeed)
		{	
		System.out.println(targets.size());
		/*
		for(int i=0;i<targets.size();i++)
		{
			if(targets!=null)
			System.out.println("This is targets id: " +targets.get(i).idNumber);
		} */
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
	
	public void shoot()
	{	
		timeSinceShoot = 0;
		
		for(int i=0;i<targets.size();i++)
		{
			if(targets.get(i)!=null)
			{ 
			ammos.add(new Ammo(cannon,targets.get(i),x,y,40,40,damage,5,this));
			targets.get(i).reduceHiddenHealth(damage);
			}
		} 
		
	}
	
	public void damageMonster(Monster monster)
	{
		monster.damage(this.damage);
		monster.burn();
		if(monster.getHp()<=0) 
		{
			monster.die();
			Player.modifyCash(monster.giveGold);
		}
	}
	
}
	
	