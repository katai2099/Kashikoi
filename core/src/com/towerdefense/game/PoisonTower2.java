package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/*
 * PoisonTower 2 can attack without calculate the difference 
 * between monster defense and its atkpoint
 */

public class PoisonTower2 extends PoisonTower {

	PoisonTower2(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.cannon = new Texture("poisonProjectile.png");
		this.damage = 8;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 3;
		this.lockOn = false;
		this.range = 320+1;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 50; 
	}
	
	//For testing purpose
	PoisonTower2()
	{
		this.damage = 8;
		this.refund = 50; 
		this.x=0;
		this.y=0;
	}
	//add ammo to list of ammo	
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null)
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
		
	}
	
	public void update()
	{
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
		
		
		dt = Gdx.graphics.getDeltaTime();
		if(dt>1.5f) dt = 1 ;
		timeSinceShoot += dt;
		if(timeSinceShoot>attackSpeed)
		{
			if(target!=null)
			shoot();
		
		}
		}
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).update();
			if(ammos.get(i).alive==false) ammos.remove(i);
		}
	}

	//attack without calculate the difference between its attackpoint and monster defense
	public void damageMonster(Monster monster)
	{
		if(monster instanceof Onion)
			monster.damage(this.damage);
		else {
		monster.pureDamage(this.damage);
		monster.damage(0);
		}
	}
	
	
}
