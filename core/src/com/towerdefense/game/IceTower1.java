package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
		this.range = 1000;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 50; 
	}

	
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null) {
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,5,this));
		//target.reduceHiddenHealth(damage);
		}
	}


	public void damageMonster(Monster monster)
	{
		if(!(monster instanceof Onion)) {
		if(!monster.permanentSlow && monster.getSpeed()!=0)
		{
			monster.slow();
		}
		}
		monster.damage(this.damage);
		//monster.hp -= damage;
		/*if(monster.getHp()<=0) 
		{
			monster.die();
			Player.modifyCash(monster.giveGold);
		}*/
	}
	
	
}
