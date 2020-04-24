package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PoisonTower extends BaseTower{


		PoisonTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> copyOnWriteArrayList) {
			super(texture, tile, width, height, copyOnWriteArrayList);
			this.cannon = new Texture("poisonProjectile.png");
			this.damage = 10;
			this.tile = tile;
			this.exp = 95;
			ammos = new ArrayList<Ammo>();
			this.timeSinceShoot=0;
			this.attackSpeed = 3;
			this.lockOn = false;
			this.range = 1000;
			dt = Gdx.graphics.getDeltaTime();
			this.cost = 50; 
			this.refund = 25;
		}
		
		//for testing
		public PoisonTower()
		{
			this.exp = 0;
			this.damage = 10;
			this.cost = 50; 
			this.refund = 25;
			this.x=0;
			this.y=0;
		}
		
		public void shoot()
		{	
			timeSinceShoot = 0;
			if(target!=null)
			ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
		}


		public void damageMonster(Monster monster)
		{
			monster.damage(this.damage);
		/*	if(monster.getHp()<=0) 
			{
				monster.die();
				Player.modifyCash(monster.giveGold);
			}*/
		}
		

		
	
}
