package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FireTower extends BaseTower{

	
	FireTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> arrayList) {
		super(texture, tile, width, height, arrayList);
		this.cannon = new Texture("fireProjectile.png");
		this.damage = 5;
		this.tile = tile;
		this.exp = 50;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 3;
		this.lockOn = false;
		this.range = 256+1;
		this.cost = 50;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 25;
	}
	
	//For testing purpose
		FireTower()
		{
			this.damage = 5;
			this.exp = 0;
			this.refund = 25;
			this.cost = 50;
			this.x=0;
			this.y=0;
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
		if(target!=null) {
			if(shootOnce==true && !target.enterCastle() && this.exp <100 && !target.isAlive() && !earnExp)
			{
				this.exp += target.giveExp;
				if(exp>100) exp = 100;
				shootOnce = false;
				earnExp = true;
			}	}
		if(target == null || target.isAlive() == false || !isInRange(target))
		{
			lockOn = false; 
			shootOnce = false; 
			earnExp = false; 
		}
		dt = Gdx.graphics.getDeltaTime();
		if(dt>1.5f) dt = 1 ;
		timeSinceShoot += dt;
		if(timeSinceShoot>attackSpeed)
		{
			if(target!=null ) {
			shoot();
			shootOnce = true ;}
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
		 
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
		
		//target.reduceHiddenHealth(damage);
		reduceHiddenhealth(target);
	}
	
	public void reduceHiddenhealth(Monster monster)
	{
		if(monster instanceof Onion) ((Onion) monster).reduceHiddenHealth();
		else
		monster.hiddenhealth -= (this.damage-monster.def);
	}

	public void damageMonster(Monster monster)
	{
		monster.damage(this.damage);
	/*	if(monster.getHp()<=0) 
		{
			monster.die();
			Player.modifyCash(monster.giveGold);
		} */
	}
	
	


}
