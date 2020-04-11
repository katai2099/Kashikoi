package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class IceTower2 extends IceTower{
		float cd;
		boolean frozen;
		
	IceTower2(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.cannon = new Texture("iceProjectile.png");
		this.damage = 5;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 2;
		this.lockOn = false;
		this.range = 256+1;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 50; 
		cd = 0;
		frozen = false ;
	}
	
	public void update()
	{
		if(monsters.size()!=0)
		{
			cd += Gdx.graphics.getDeltaTime();
		if(!lockOn)
		{
			target = aimTarget();
		}
		if(target == null || target.isAlive() == false || !isInRange(target))
		{
			lockOn = false; 
		}
		if(target!=null) {
		if(lockOn == false && shootOnce==true && !target.enterCastle() && this.exp <100 && !target.isAlive())
		{
			this.exp += target.giveExp;
			if(exp>100) exp = 100;
			shootOnce = false;
		}	}
		
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
	
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null) {
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,5,this));
	//	target.reduceHiddenHealth(damage);
		}
	}


	public void damageMonster(Monster monster)
	{
		if(!(monster instanceof Onion)) {
		if(cd>3.0f)
		{
		monster.freeze();
		cd = 0 ;
		}
		}
		monster.damage(this.damage);
		
		
	}
	
	
	
}
