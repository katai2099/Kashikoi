package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PoisonTower1 extends PoisonTower{

	PoisonTower1(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.cannon = new Texture("poisonProjectile.png");
		this.damage = 10;
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
		//target.reduceHiddenHealth(damage);
	}


	public void damageMonster(Monster monster)
	{
		monster.damage(this.damage);
		if(!(monster instanceof Onion)) {
			if(!monster.piercethrough)
			{
				monster.reduceArmor(3);
			}
			}
		//monster.damage(this.damage);
		
	}
	
	
}
