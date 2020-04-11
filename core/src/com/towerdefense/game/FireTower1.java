package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FireTower1 extends FireTower{

	FireTower1(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.x = tile.getX();
		this.y = tile.getY();
		this.cannon = new Texture("fireProjectile.png");
		this.damage = 0.5f;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 0.3f;
		this.lockOn = false;
		this.range = 320+1;
		this.refund = 50;
		dt = Gdx.graphics.getDeltaTime();
	}
	
	public void update()
	{
		super.update();
	}
	
	public void shoot()
	{	
		timeSinceShoot = 0;
		if(target!=null) {
		ammos.add(new Ammo(cannon,target,x,y,40,40,damage,5,this));
	//	target.reduceHiddenHealth(damage);
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
