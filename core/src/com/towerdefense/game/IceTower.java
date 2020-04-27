package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

//IceTower has slow effect, decrease monster speed by 1 point for 1 second

public class IceTower extends BaseTower{


		IceTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> copyOnWriteArrayList) {
			super(texture, tile, width, height, copyOnWriteArrayList);
			this.cannon = new Texture("iceProjectile.png");
			this.damage = 10;
			this.tile = tile;
			this.exp = 0;
			ammos = new ArrayList<Ammo>();
			this.timeSinceShoot=0;
			this.attackSpeed = 3;
			this.lockOn = false;
			this.range = 256+1;
			dt = Gdx.graphics.getDeltaTime();
			this.cost = 50;
			this.refund = 25;
		}
		
		//for testing purpose 
		
		public IceTower() {
				this.damage = 5;
				this.exp = 0;
				this.cost = 50;
				this.refund = 25;
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
			/*
			check if it has been placed on the map on for the first time
			if the target is not null then shoot */
			if(first && target!=null)
			{
				shoot();
				first = false;
			}
			//only check if target is not null 
			if(target!=null) {
				/*
				check if monster is alive and been shot by tower once and monster is not entering castle
				if so increase its exp by monster giveEXP
				*/
			if(shootOnce==true && !target.enterCastle && this.exp <100 && !target.isAlive())
			{
				this.exp += target.giveExp;
				if(exp>100) exp = 100;
				shootOnce = false;
			}
			}
			//check if target is null,dead,not in range of attack if so need to aimNewTarget by changing lockOn to false
			if(target == null || target.isAlive() == false || !isInRange(target))
			{
				lockOn = false; 	
				shootOnce = false;
			}
			
			dt = Gdx.graphics.getDeltaTime();
			if(dt>1.5f) dt = 1 ;
			timeSinceShoot += dt;
			if(timeSinceShoot>attackSpeed)
			{
				shoot();
			}
			}
			for(int i=0;i<ammos.size();i++)
			{
				ammos.get(i).update();
				if(ammos.get(i).alive==false) ammos.remove(i);
			}
		}
		
		//change timeSinceshoot to 0 and add ammo to list of ammo
		public void shoot()
		{	
			timeSinceShoot = 0;
			if(target!=null) {
			ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
			}
		}
		
		//Damage monster when ammo position reached monster position  
		public void damageMonster(Monster monster)
		{
			shootOnce = true;
			monster.damage(this.damage);
			if(!(monster instanceof Onion)) {
			if(!monster.slow)
			monster.tmpSlow();}
	
		}
		
	
}
