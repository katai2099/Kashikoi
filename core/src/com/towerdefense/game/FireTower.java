package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FireTower extends BaseTower{

	FireTower(Texture texture, Tile tile, int width, int height, ArrayList<Monster> arrayList) {
		super(texture, tile, width, height, arrayList);
		this.cannon = new Texture("fireProjectile.png");
		this.damage = 30;
		this.tile = tile;
		this.exp = 98;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 3;
		this.lockOn = false;
		this.range = 256+1;
		this.cost = 50;
		dt = Gdx.graphics.getDeltaTime();
		this.refund = 25;
	}
	
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null) {
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,10,this));
		
		//target.reduceHiddenHealth(damage);
		}
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
