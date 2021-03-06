package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

	//SuperClass of All Monster

public class Monster extends Sprite{
	public static int id;
	public static float dt ;
	protected int giveExp;
	protected int giveGold;
	int idNumber;
	float x;
	float y;
	float height;
	float width;
	float speed;
	float originalSpeed;
	int atk;
	float def;
	float hp;
	int hiddenhealth;
	Texture texture;
	Tile startile;
	Tile currentTile;
	boolean attack;
	boolean alive;
	Map map;
	int dir[] ;
	ArrayList<Checkpoint> checkpoints;
	int currentCheckpoint;
	BitmapFont hpNumber;
	boolean poison;
	boolean freeze;
	boolean burn;
	boolean slow;
	boolean permanentSlow;
	float tmpTimeBurn;
	float tmpTimeFreeze;
	float tmpTimePoison;
	float tmpSlowTime;
	boolean piercethrough;
	boolean enterCastle;
	boolean Twice ;
	
	public Monster()
	{
		
	}
	
	public Monster(int atk)
	{
		this.atk = atk;
	}
	
	public Monster(Texture texture, Tile startile, int height, int width, int atk, int speed) {
	} 
	
	Monster(Texture texture,Tile startile,Map map,float height,float width,int atk,float speed)
	{
		id = 0 ;
		this.texture=texture;
		this.x = startile.getX();
		this.y = startile.getY();
		this.height = height;
		this.width = width;
		this.atk = atk;
		this.hp = 30;
		this.speed = speed;
		this.startile=startile;
		this.currentTile=startile;
		this.map = map;
		this.dir = new int[2];
		this.checkpoints = new ArrayList<Checkpoint>();
		dir[0]=0;
		dir[1]=0;
	 	dir = findDirection(startile);
	 	this.currentCheckpoint = 0 ;
	 	this.alive = true;
	 	populateCheckpointList();
	 	dt = Gdx.graphics.getDeltaTime();
	 	hpNumber = new BitmapFont();
	 	hpNumber.setColor(Color.BLACK);
	}
	
	Monster(Tile startile,Map map,float height,float width)
	{
		this.x = startile.getX();
		this.y = startile.getY();
		this.height = height;
		this.width = width;
		this.hp = 30;
		this.startile=startile;
		this.currentTile=startile;
		this.map = map;
		this.dir = new int[2];
		this.checkpoints = new ArrayList<Checkpoint>();
		dir[0]=0;
		dir[1]=0;
	 	dir = findDirection(startile);
	 	this.currentCheckpoint = 0 ;
	 	this.alive = true;
	 	populateCheckpointList();
	 	hpNumber = new BitmapFont();
	 	hpNumber.setColor(Color.BLACK);
	 	poison = false;
	 	freeze = false;
	 	burn = false;
	 	 tmpTimeBurn=0;
	 	 tmpTimeFreeze=0;
	 	 tmpTimePoison=0;
	 	 tmpSlowTime = 0 ;
	 	 slow = false;
	 	 Twice = false;
	 	 permanentSlow = false;
	 	 piercethrough = false;
	 	 enterCastle = false;
	 	 this.speed = 10;
	 	 this.originalSpeed = this.speed;
	}
	
	public void update()
	{
		if(x<=1280 && y<=1024 && x>=0 && y >=64)
		{
			
			if(checkpointReached()) 
			{
				//check if there are more checkpoint 
				if(currentCheckpoint+1==checkpoints.size())
				{
					map.castle.decreasehp(atk);
					die();
					enterCastle = true;
				}
				else
					currentCheckpoint++;
			}else {
				//if checkpoint is not reached then update monster movement in current direction
				dt = Gdx.graphics.getDeltaTime()*10;
				if(dt > 0.18f) dt = 0.16f;
			
			if(checkpoints.get(currentCheckpoint).getX()==1
					&&checkpoints.get(currentCheckpoint).getY()==0)x+= speed*dt;//x=x+speed;
			else if (checkpoints.get(currentCheckpoint).getX()==-1
					&&checkpoints.get(currentCheckpoint).getY()==0) x-=speed*dt;//x=x-speed;
			else if (checkpoints.get(currentCheckpoint).getX()==0
					&&checkpoints.get(currentCheckpoint).getY()==1)y+=speed*dt;//y=y+speed;
			else if (checkpoints.get(currentCheckpoint).getX()==0
					&&checkpoints.get(currentCheckpoint).getY()==-1) y-=speed*dt; //y=y-speed;
			}
		}
		updateCurrentTile();
		//burn effect reduce monster hp by one point per second max 2 sec
		if(burn)
		{
			hpNumber.setColor(Color.RED);
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				
			timeincaseScreenFreeze = 0.016f;}
			tmpTimeBurn += timeincaseScreenFreeze;
		
			if(tmpTimeBurn>1 && !Twice)
			{
				damage(1);
				Twice = true; 
				
			}
			if(tmpTimeBurn>2 && Twice && alive)
			{
				damage(1);
				burn = false;
				Twice = false;
				tmpTimeBurn = 0 ;
				hpNumber.setColor(Color.BLACK);
			}
		}
		//freeze effect change monster speed to 0 
		if(freeze)
		{
			hpNumber.setColor(Color.BLUE);
			this.speed = 0;
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				
			timeincaseScreenFreeze = 0.016f;}
			tmpTimeFreeze += timeincaseScreenFreeze;
			if(tmpTimeFreeze>1)
			{
				//we have to check if monster is effected by other IceTower attack effect
				if(slow) this.speed = originalSpeed-1;
				else this.speed = originalSpeed;
				if(permanentSlow) this.speed = originalSpeed-3;
				if(permanentSlow && slow) this.speed = originalSpeed-4;
				freeze = false;
				tmpTimeFreeze=0;
				hpNumber.setColor(Color.BLACK);
			}
		}
		//slow effect reduce monster speed by one point for one second
		if(slow)
		{
			hpNumber.setColor(Color.BLUE);
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				
			timeincaseScreenFreeze = 0.016f;}
			tmpSlowTime += timeincaseScreenFreeze;
			//after one second pass we increase speed by one point
			if(tmpSlowTime>1)
			{
				speed +=1;
				slow = false;
				tmpSlowTime=0;
				hpNumber.setColor(Color.BLACK);
			}
		}
		//this change the color text base on what effect monster been effected 
		if(permanentSlow)hpNumber.setColor(Color.BLUE); 
		if(piercethrough)hpNumber.setColor(Color.FOREST);
		if(piercethrough && permanentSlow) hpNumber.setColor(Color.PURPLE); 
	}
	
	//check if checkpoint is reached by checking next coordinate
	protected boolean checkpointReached()
	{
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		if(x>=t.getX()-3 && x<=t.getX()+3 &&
				y>=t.getY()-3 && y<=t.getY()+3)
		{
			reached = true;
			x=t.getX();
			y=t.getY();
		}
		return reached;
	}
	
	//populate checkpoint base on the map 
	protected void populateCheckpointList() 
	{
		//add first checkpoint base on startile
		checkpoints.add(findNextC(startile,dir =findDirection(startile)));
		int cnt = 0 ;
		boolean continuee = true;
		while(continuee)
		{
			int[] currentD = findDirection(checkpoints.get(cnt).getTile());
			//check if the next direction exist
			if(currentD[0]==2 || cnt == 20 || currentD[1]==2)
			{
				continuee = false;
			
			}
			else
			{
				checkpoints.add(findNextC(checkpoints.get(cnt).getTile(),currentD));	
			}
			cnt++;
		}
	}
	
	protected Checkpoint findNextC(Tile start,int[] dir)
	{
		Tile next = null;
		Checkpoint c = null;
		
		//Boolean to decide if next checkpoint is found
		boolean found = false;
		// int to increase map(x,y)
		int cnt = 1;
		
		while(!found)
		{
			if(	start.getmapX()+dir[0]*cnt == 20||
					start.getmapY()+dir[1]*cnt == 15 ||
					start.getTiletype() !=
					map.getTile(start.getmapX()+dir[0]*cnt, 
							start.getmapY()+dir[1]*cnt).getTiletype())
			{
				found = true;
				//move cnt back to get the latest tiletype
				cnt-=1;
				next = map.getTile(start.getmapX()+dir[0]*cnt, 
						start.getmapY()+dir[1]*cnt);
			}
			cnt++;
		}
		
		c = new Checkpoint(next,dir[0],dir[1]);
		
		return c;
	}
	
	//find direction of monster base on checkpoint tile
	protected int[] findDirection(Tile ntile)
	{
		Tile u = map.getTile(ntile.getmapX(),ntile.getmapY()+1);
		Tile d = map.getTile(ntile.getmapX(),ntile.getmapY()-1);
		Tile r = map.getTile(ntile.getmapX()+1,ntile.getmapY());
		Tile l = map.getTile(ntile.getmapX()-1,ntile.getmapY());
		
		//if current tile is match tile type above down right left 
		  if(r.getTiletype()==ntile.getTiletype()&&dir[0]!=-1 )
			{
				dir[0]=1;
				dir[1]=0;
			}
		 
		 else if(d.getTiletype()==ntile.getTiletype()&&dir[1] != 1)
			{
				dir[0]=0;
				dir[1]=-1;
			}
		 
		 else if( u.getTiletype()==ntile.getTiletype()  && dir[1] != -1 )
			{
				dir[0]=0;
				dir[1]=1;
			}
		 
		 else if(l.getTiletype()==ntile.getTiletype()&&dir[0]!=1)
			{
				dir[0]=-1;
				dir[1]=0;
			}

		
		else {dir[0]=2;dir[1]=2;}
		return dir;
	
	}
	
	
	//draw method 
	public void draw(Batch b)
	{
		if(alive) {
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
		hpNumber.draw(b,String.valueOf(hp),getX()+25,getY()+85);
	//	hpNumber.draw(b,String.valueOf(getX())+" " +String.valueOf(getY()),getX()+25,getY()-32);
		hpNumber.draw(b,String.valueOf(speed),getX()+25,getY()-32);
		hpNumber.draw(b,String.valueOf(def),getX()-25,getY()+32);
		}
	}

	
	
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	//get damage from external source
	public void damage(float damage)
	{
		this.hp -= damage;
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		}
	}
	//run when monster enter last checkpoint
	public void die()
	{
		alive = false;
	}
	
	protected int getHiddenHealth()
	{
		return hiddenhealth;
	}
	
	//trigger burn effect
	protected void burn()
	{
		burn = true; 
	}
	
	//trigger freeze effect 
	protected void freeze()
	{
		this.speed=0;
		freeze = true;
	
	}
	
	//permanently decrease speed by 3 point and change its value to true 
	protected void slow()
	{
		this.speed -= 3;
		permanentSlow = true; 
	}
	
	//trigger slow effect
	protected void tmpSlow()
	{
		this.speed -= 1;
		slow = true ;
		
	}
	
	//called by poisonTower2 when attack monster deal damage without calculate the difference of armor and damage
	protected void pureDamage(float amount)
	{
		this.hp -= amount ;
	}
	
	//called by poisonTower1 when attack monster permanently decrease armor by the amount and change piercethrough to true 
	protected void reduceArmor(float amount)
	{
		this.def -= amount;
		piercethrough = true;
	}

	protected void reduceHiddenHealth(float amount) {
		// TODO Auto-generated method stub
		
	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setCurrentTile(Tile currentTile) {
		this.currentTile = currentTile;
	}

	public Tile getStartile() {
		return startile;
	}

	public void setStartile(Tile startile) {
		this.startile = startile;
	}
	public void updateCurrentTile()
	{
		currentTile = map.getTile((int)(this.x/64), (int)(this.y-64)/64);
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public float getArmor() {
		return def;
	}

	public void setArmor(int armor) {
		this.def = armor;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public int getmapX()
	{
		return (int) x/64;
	}
	
	public int getmapY()
	{
		return (int) (y-64)/64;
	}

	public float getWidth() {
		return width;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	
}
