package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
		this.range = 500;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 50; 
	}
			
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null)
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,12,this));
	//	target.reduceHiddenHealth(damage);
	}


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
