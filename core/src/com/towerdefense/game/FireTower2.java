package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FireTower2 extends FireTower{

		ArrayList<Monster>targets;
		int cnt;
	
	FireTower2(Texture texture, Tile tile, int width, int height, ArrayList<Monster> monsters) {
		super(texture, tile, width, height, monsters);
		this.x = tile.getX();
		this.y = tile.getY();
		this.cannon = new Texture("fireProjectile.png");
		this.damage = 0;
		this.tile = tile;
		this.exp = 0;
		ammos = new ArrayList<Ammo>();
		this.timeSinceShoot=0;
		this.attackSpeed = 3f;
		this.lockOn = false;
		this.range = 256+1;
		this.refund = 50;
		dt = Gdx.graphics.getDeltaTime();
		targets = new ArrayList<Monster>();
		cnt ++;
	}
	//for Testing
	public FireTower2() {
		this.refund = 50;
		this.damage = 7;
		this.x=0;
		this.y=0;
	}

	@Override
	Monster aimTarget()
	{
		Monster closest = null;
		float closestDistance = 10000;
		ArrayList<Monster> tmp = new ArrayList<Monster>();
		for(int i=0;i<monsters.size();i++)
		{
			tmp.add(monsters.get(i));
		}
		
		if(targets.size()>0) {
		for(int i=0;i<targets.size();i++)
		{
			if(targets.size()!=0 && tmp.size()!=0) {
			for(int j=0;j<tmp.size();j++)
			{
				if(targets.get(i).idNumber == tmp.get(j).idNumber )
				{
					tmp.remove(j);
				}
			}
			}
		}
		}
		for(int i=0;i<tmp.size();i++)
		{
				if	(isInRange(tmp.get(i)) 
						&& findDistance(tmp.get(i)) < closestDistance && tmp.get(i).getHiddenHealth() > 0)
					{
						closestDistance = findDistance(tmp.get(i));
						closest = tmp.get(i);						
					}
		}
		return closest;
	}
		
	
	
	public boolean isInRange(Monster m)
	{
		float xDistance = Math.abs(m.getX()-this.x);
		float yDistance = Math.abs(m.getY()-this.y);
		if(xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	public float findDistance(Monster m)
	{
		float xDistance = Math.abs(m.getX()-this.x);
		float yDistance = Math.abs(m.getY()-this.y);
		return xDistance + yDistance;
	}
	
	public void updateMonsterList(ArrayList<Monster>list)
	{
		monsters = list;
	}
	
	
	
	public void update()
	{
		if(monsters.size()!=0)
		{
			
		if(targets.size()<3) {
			
			for(int i=0;i<targets.size();i++)
			{
				
				if(targets.get(i)!=null) {
				if( !isInRange(targets.get(i)) )
					{
					
					targets.remove(i);
					}
				}
				else targets.remove(i);
			} 
		targets.add(aimTarget());
		if(cnt<4) cnt++;
		}
		if(first && cnt==4) {
			shoot();
			first = false;
		}
		for(int i=0;i<targets.size();i++)
		{
			if(targets.get(i) == null || targets.get(i).isAlive() == false || !isInRange(targets.get(i)) )
			{
				targets.remove(i);
			}
		}
	
		dt = Gdx.graphics.getDeltaTime();
		if(dt>1.5f) dt = 1 ;
		timeSinceShoot += dt;
		if(timeSinceShoot>attackSpeed)
		{	
		if(!allnull())
			shoot();
		
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
		
		for(int i=0;i<targets.size();i++)
		{
			if(targets.get(i)!=null)
			{ 
			ammos.add(new Ammo(cannon,targets.get(i),x,y,40,40,damage,5,this));
			}
		} 
		
	}
	
	public boolean allnull()
	{
		for(Monster m:targets)
		{
			if(m!=null)
				return false;
		}
		return true;
	}
	
	public void damageMonster(Monster monster)
	{
		monster.damage(this.damage);
		if(!(monster instanceof Onion))
		monster.burn();
	}
	
}
	
	