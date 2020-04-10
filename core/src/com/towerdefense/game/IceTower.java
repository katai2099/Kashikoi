package com.towerdefense.game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class IceTower extends BaseTower{

		boolean froze;

		IceTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> copyOnWriteArrayList) {
			super(texture, tile, width, height, copyOnWriteArrayList);
			this.cannon = new Texture("iceProjectile.png");
			this.damage = 10;
			this.tile = tile;
			this.exp = 95;
			ammos = new ArrayList<Ammo>();
			this.timeSinceShoot=0;
			this.attackSpeed = 3;
			this.lockOn = false;
			this.range = 600;
			dt = Gdx.graphics.getDeltaTime();
			this.cost = 50;
			this.refund = 25;
			froze = false; 
		}
		
		public void update()
		{
			if(monsters.size()!=0)
			{
			if(!lockOn)
			{
				target = aimTarget();
			}
			if(target == null || target.isAlive() == false)
			{
				lockOn = false; 
			}
			if(lockOn == false && shootOnce==true && !target.enterCastle() && this.exp <100)
			{
				this.exp += target.giveExp;
				if(exp>100) exp = 100;
				shootOnce = false;
			}
			
			dt = Gdx.graphics.getDeltaTime();
			if(dt>1.5f) dt = 1 ;
			timeSinceShoot += dt;
			if(timeSinceShoot>attackSpeed)
			{
				if(target!=null)
				{
				shoot();
				shootOnce = true ;
				}
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
			ammos.add(new Ammo(cannon,target,x,y,40,40,damage,5,this));
			target.reduceHiddenHealth(damage);
		}


		public void damageMonster(Monster monster)
		{
			monster.damage(this.damage);
		/*	if(!froze)
			monster.speed -= 2;
			froze = true ;*/
			monster.freeze();
			if(monster.getHp()<=0) 
			{
				monster.die();
				froze = false;
				Player.modifyCash(monster.giveGold);
			}
		}
		
	
}
