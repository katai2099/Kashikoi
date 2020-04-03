package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Monster extends Sprite{
	float x;
	float y;
	float height;
	float width;
	int speed;
	int atk;
	int armor;
	int hp;
	Texture texture;
	Tile startile;
	Tile currentTile;
	boolean attack;
	Map map;
	int dir[] ;
	ArrayList<Checkpoint> checkpoints;
	int currentCheckpoint;
	
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
	
	Monster(Texture texture,Tile startile,Map map,float height,float width,int atk,int speed)
	{
		this.texture=texture;
		this.x = startile.getX();
		this.y = startile.getY();
		this.height = height;
		this.width = width;
		this.atk = atk;
		this.speed = speed;
		this.startile=startile;
		this.currentTile=startile;
		this.hp = 10;
		this.map = map;
		this.dir = new int[2];
		this.checkpoints = new ArrayList<Checkpoint>();
		dir[0]=0;
		dir[1]=0;
	 	dir = findDirection(startile);
	 	this.currentCheckpoint = 0 ;
	 	populateCheckpointList();
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

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getSpeed() {
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
	
	public void move()
	{
		if(x<=1280 && y<=1024 )
		{
			if(checkpointReached()) 
			{
				if(currentCheckpoint+1==checkpoints.size())
				{
					System.out.println("Monster enter tower");
					
				}
				else
					currentCheckpoint++;
			}else {
		//		System.out.println(checkpoints.get(currentCheckpoint).getTile().getmapX()+" "+checkpoints.get(currentCheckpoint).getTile().getmapY()+" "
		//	+checkpoints.get(currentCheckpoint).getX()+" "+checkpoints.get(currentCheckpoint).getY());
		//	x = x+speed;
		/*	if(dir[0]==1&&dir[1]==0)x=x+speed;
			else if(dir[0]==-1&&dir[1]==0)x=x-speed;
			else if(dir[0]==0&&dir[1]==1)y=y+speed;
			else if(dir[0]==0&&dir[1]==-1)y=y-speed;  */
				//System.out.println(checkpoints.get(currentCheckpoint).getX()+" "+checkpoints.get(currentCheckpoint).getY());
			if(checkpoints.get(currentCheckpoint).getX()==1
					&&checkpoints.get(currentCheckpoint).getY()==0) x=x+speed;
			else if (checkpoints.get(currentCheckpoint).getX()==-1
					&&checkpoints.get(currentCheckpoint).getY()==0) x=x-speed;
			else if (checkpoints.get(currentCheckpoint).getX()==0
					&&checkpoints.get(currentCheckpoint).getY()==1) y=y+speed;
			else if (checkpoints.get(currentCheckpoint).getX()==0
					&&checkpoints.get(currentCheckpoint).getY()==-1) y=y-speed;
			}
		}
		updateCurrentTile();
	}
	
	public boolean attackYet(Castle castle)
	{
		return castle.getX()==this.getX() && castle.getY()==this.getY();
	}
	
	private boolean checkpointReached()
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
	
	private void populateCheckpointList() 
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
	
	private Checkpoint findNextC(Tile start,int[] dir)
	{
		Tile next = null;
		Checkpoint c = null;
		
		//Boolean to decide if next checkpoint is found
		boolean found = false;
		// int to increase map(x,y)
		int cnt = 1;
		
		while(!found)
		{
			if(start.getTiletype() !=
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
	

	public int[] findDirection(Tile ntile)
	{
		//dir = new int[2];
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
	
	
	public boolean isDead()
	{
		return hp<=0;
	}
	
	public void gotShot(int atk)
	{
		this.hp -= atk;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getHp() {
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
	
	public void attack(Castle castle)
	{
		
	}
	
	public Boolean isAttack()
	{
		return attack;
	}
	
	public void draw(Batch b)
	{
		b.draw(getTexture(),getX(),getY(),getWidth(),getHeight());
	}

	public void current()
	{
		System.out.println(currentTile.getmapX()+ " " + currentTile.getmapY() );
	}
	
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	

	
}
