package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;

public class FireTower extends BaseTower{

	private Texture firetexture;

	FireTower(Texture texture, Wave target ,Tile tile, int width, int height) {
		super(texture, target,tile, width, height);
		firetexture = new Texture("fire1.png");
		this.attackSpeed = 4;
		this.target = target;
		this.attack = 5;
	}
	
	public void update(Wave m)
	{
		timeSinceShoot = ((System.currentTimeMillis()-start)/1000);
		if(timeSinceShoot-previousShootTime > attackSpeed)
		{
			shoot();
			previousShootTime = timeSinceShoot;
			//System.out.println(previousSpawnTime);
			timeSinceShoot = 0;
			m.getMonsters().get(0).gotShot(5);
		}
		for(int i=0;i<ammos.size();i++)
		{
			ammos.get(i).update(attackSpeed,previousShootTime);
		}
		
	
	}
	
	public void shoot()
	{
		//ammos.add(new Ammo(firetexture,target.getMonsters().get(0),x,y,width,height));
	}
//	public void shoot(Wave m)
//	{
//		m.getMonsters().get(0).gotShot(this.attack);
//	}

	
	
	

}
