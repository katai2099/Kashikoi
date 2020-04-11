package com.towerdefense.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

	

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
	
	
	boolean Twice ;
	
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
	
	public void update()
	{
		if(x<=1280 && y<=1024 && x>=0 && y >=64)
		{
			if(checkpointReached()) 
			{
				if(currentCheckpoint+1==checkpoints.size())
				{
					map.castle.decreasehp(atk);
					die();
				}
				else
					currentCheckpoint++;
			}else {
				dt = Gdx.graphics.getDeltaTime()*10;
				if(dt > 0.18f) dt = 0.16f;
			//	System.out.println(dt);
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
		if(burn)
		{
			
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				System.out.println(timeincaseScreenFreeze);
			timeincaseScreenFreeze = 0.016f;}
			tmpTimeBurn += timeincaseScreenFreeze;
		//	System.out.println(tmpTimeBurn);
			if(tmpTimeBurn>1 && !Twice)
			{
				hp-=1;
				Twice = true; 
				//burn = false;
				//tmpTimeBurn=0;
			}
			if(tmpTimeBurn>2 && Twice)
			{
				hp-=1;
				if(hp<=0) die(); Player.modifyCash(this.giveGold);
				burn = false;
				Twice = false;
				tmpTimeBurn = 0 ;
			}
		}
		if(freeze)
		{
			this.speed = 0;
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				System.out.println(timeincaseScreenFreeze);
			timeincaseScreenFreeze = 0.016f;}
			tmpTimeFreeze += timeincaseScreenFreeze;
			if(tmpTimeFreeze>1)
			{
				if(slow) this.speed = 9;
				else this.speed = 10;
				if(permanentSlow) this.speed = 7;
				if(permanentSlow && slow) this.speed = 6;
				freeze = false;
				tmpTimeFreeze=0;
			}
		}
		if(slow)
		{
			float timeincaseScreenFreeze = Gdx.graphics.getDeltaTime();
			if(timeincaseScreenFreeze > 0.020f) {
				System.out.println(timeincaseScreenFreeze);
			timeincaseScreenFreeze = 0.016f;}
			tmpSlowTime += timeincaseScreenFreeze;
			if(tmpSlowTime>1)
			{
				speed +=1;
				slow = false;
				tmpSlowTime=0;
			}
		}
	}
	
	public boolean enterCastle()
	{
		//return map.castle.getX()==this.getX() && map.castle.getY()==this.getY();
		return this.getmapX() == (int)map.getCastle().getX()/64 && this.getmapY() == (int)((map.getCastle().getY()-64)/64);
	}
	
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
	
	protected void populateCheckpointList() 
	{
		
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
			//	System.out.println(currentD[0]+" "+currentD[1]+" "+cnt);
			}
			else
			{
				
				checkpoints.add(findNextC(checkpoints.get(cnt).getTile(),currentD));
					//	dir=findDirection(checkpoints.get(cnt).getTile())));
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
	

	protected int[] findDirection(Tile ntile)
	{
		Tile u = map.getTile(ntile.getmapX(),ntile.getmapY()+1);
		Tile d = map.getTile(ntile.getmapX(),ntile.getmapY()-1);
		Tile r = map.getTile(ntile.getmapX()+1,ntile.getmapY());
		Tile l = map.getTile(ntile.getmapX()-1,ntile.getmapY());
		
		
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

	public void current()
	{
		System.out.println(currentTile.getmapX()+ " " + currentTile.getmapY() );
	}
	
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	public void damage(float damage)
	{
		this.hp -= damage;
		if(hp<=0) 
		{
			die();
			Player.modifyCash(this.giveGold);
		}
	}
	
	public void die()
	{
		alive = false;
	}
	
	protected int getHiddenHealth()
	{
		return hiddenhealth;
	}
	
	protected void reduceHiddenHealth(int amount)
	{
		this.hiddenhealth -= amount;
	}
	
	protected void burn()
	{
		burn = true; 
	}
	
	protected void freeze()
	{
		this.speed=0;
		freeze = true;
	}
	
	protected void slow()
	{
		this.speed -= 3;
		permanentSlow = true; 
	}
	
	protected void tmpSlow()
	{
		this.speed -= 1;
		slow = true ;
	}
	
	protected void pureDamage(float amount)
	{
		this.hp -= amount ;
	}
	
	protected void reduceArmor(float amount)
	{
		this.def -= amount;
		piercethrough = true;
	}
	

	
}
