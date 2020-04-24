package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class IceTower extends BaseTower{


		IceTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> copyOnWriteArrayList) {
			super(texture, tile, width, height, copyOnWriteArrayList);
			this.cannon = new Texture("iceProjectile.png");
			this.damage = 50;
			this.tile = tile;
			this.exp = 50;
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
			if(first && target!=null)
			{
				shoot();
				first = false;
			}
			if(target == null || target.isAlive() == false || !isInRange(target))
			{
				lockOn = false; 	
				
			}
			if(target!=null) {
			if(shootOnce==true && !target.enterCastle() && this.exp <100 && !target.isAlive())
			{
				this.exp += target.giveExp;
				if(exp>100) exp = 100;
				shootOnce = false;
			}
			}
			
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
			ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
		//	target.reduceHiddenHealth(damage);
			}
		}


		public void damageMonster(Monster monster)
		{
			monster.damage(this.damage);
			if(!(monster instanceof Onion)) {
			if(!monster.slow)
			monster.tmpSlow();}
			//monster.damage(this.damage);
			//monster.freeze();
		/*	if(monster.getHp()<=0) 
			{
				monster.die();
				froze = false;
				Player.modifyCash(monster.giveGold);
			} */
		}
		
	
}
