package com.towerdefense.game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

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
		
		
		public void shoot()
		{	
			timeSinceShoot = 0;
			ammos.add(new Ammo(cannon,target,x,y,40,40,damage,5,this));
			target.reduceHiddenHealth(damage);
		}


		public void damageMonster(Monster monster)
		{
			monster.damage(this.damage);
			if(monster.getHp()<=0) 
			{
				monster.die();
				Player.modifyCash(monster.giveGold);
			}
		}
		

		
	
}
