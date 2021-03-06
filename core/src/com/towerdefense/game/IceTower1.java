package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/*
 * IceTower1 can permanently decrease monster speed by 3 points 
 */

public class IceTower1 extends IceTower {

	IceTower1(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.cannon = new Texture("iceProjectile.png");
		this.damage = 10;
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
	
	//for Testing
	public IceTower1() {
		this.damage = 10;
		this.x=0;
		this.y=0;
		this.refund = 50;
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
	
	//add ammo to list of ammos
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null) {
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
		//target.reduceHiddenHealth(damage);
		}
	}

	//permanently decrese monster speed by 3 points 
	public void damageMonster(Monster monster)
	{
		monster.damage(this.damage);
		if(!(monster instanceof Onion)) {
		if(!monster.permanentSlow && monster.getSpeed()!=0)
		{
			monster.slow();
		}
		}
	}
	
	
}
