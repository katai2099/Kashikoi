package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Player {
	
	public static boolean win;
	public static boolean lose;
	public static boolean end;
	public Map map;
	private boolean leftMousePressed;
	ArrayList<BaseTower>towers;
	BaseTower tmpTower;
	boolean holdingTower;
	public static int cash;
	WaveManager waveManager;
	boolean TowerSelected;
	BaseTower tmpSelectedTower;
	boolean build;
	
	public Player(Map map,WaveManager waveManager)
	{
		this.map = map;
		this.leftMousePressed = false;
		towers = new ArrayList<BaseTower>();
		cash = 1000;
		this.holdingTower = false;
		this.waveManager = waveManager;
		this.TowerSelected = false;
		tmpSelectedTower = null;
		lose = false;
		win = false;
		end = false;
		build = false;
	}
	
	//for Testing purpose
	//public Player(int k[][])
	public Player()
	{
		//map = new Map(k);
		map = new Map();
		cash = 100;
		tmpSelectedTower = null;
		TowerSelected=false;
		towers = new ArrayList<BaseTower>();
	}
	
	
	
	public static int getCash() {
		return cash;
	}
	
	//modifyCash function used when buying or selling tower or killing monsters
	
	public static boolean modifyCash(int cost)
	{
		if(cash + cost>= 0)
		{
			cash += cost;
			return true;
		}
		return false;
		
	}
	
	public void pickTower(BaseTower t)
	{
		tmpTower = t;
		holdingTower= true;
	}
	
	public void update()
	{
		if(map.getCastle().isDestroy()) {Player.lose = true; Player.end = true;}
		waveManager.update();
		for(BaseTower t:towers)
		{
			t.update();
			t.updateMonsterList(waveManager.getCurrentWave().getMonsters());
		}
		if(holdingTower)
		{
			tmpTower.setX(map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)).getX());
			tmpTower.setY(map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)).getY());
			tmpTower.setTile(map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)));
		}
		if(holdingTower)
		{
			if(Gdx.input.isButtonJustPressed(0)&&!leftMousePressed &&
					map.getTile(Gdx.input.getX()/64, (((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)).getTiletype().buildable==true)
			{
				for(BaseTower t:towers)
				{
					if(t.getmapX()==Gdx.input.getX()/64 && t.getmapY()==(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64))
					{
						build = true;
						break;
					}
				}
				if(!build)
				{
				if(modifyCash(-tmpTower.getCost()) ) {
				towers.add(tmpTower);
				holdingTower = false;
				tmpTower = null;}
				else 
					{
					holdingTower = false;
					tmpTower = null;
					}
				}else 
				{
				holdingTower = false;
				tmpTower = null;
				build = false; 
				}
			}
		}
		leftMousePressed = Gdx.input.isButtonJustPressed(0);
	}
	
	public void saveTower()
	{
		tmpSelectedTower = null;
		TowerSelected = true;
		float MouseX = Gdx.input.getX();
		float MouseY = Gdx.graphics.getHeight()-Gdx.input.getY();

			for(BaseTower t:towers) {
				if(MouseX > t.getX() && MouseX < t.getX() + t.getWidth() &&
						MouseY > t.getY() && MouseY < t.getY() + t.getHeight()) 
					tmpSelectedTower = t;
			}
	}
	
	//First Upgrade Option
	
	public void upgrade1()
	{
		if(tmpSelectedTower.getExp()==100) {
		BaseTower t = tmpSelectedTower;
		for(int i=0;i<towers.size();i++)
		{
			if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
				towers.remove(i);
		} 
		tmpSelectedTower = null;
		TowerSelected = false ;
		
		if(t instanceof FireTower) {
			towers.add(new FireTower1(new Texture("fireTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		}
		else if(t instanceof IceTower) 
			towers.add(new IceTower1(new Texture("iceTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		else if(t instanceof PoisonTower)
			towers.add(new PoisonTower1(new Texture("poisonTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		t = null;
		}
		else if(Player.cash>=100)
		{
			modifyCash(-100);
			BaseTower t = tmpSelectedTower;
			for(int i=0;i<towers.size();i++)
			{
				if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
					towers.remove(i);
			} 
			tmpSelectedTower = null;
			TowerSelected = false ;
			
			if(t instanceof FireTower) {
				towers.add(new FireTower1(new Texture("fireTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			}
			else if(t instanceof IceTower) 
				towers.add(new IceTower1(new Texture("iceTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			else if(t instanceof PoisonTower)
				towers.add(new PoisonTower1(new Texture("poisonTowerEdit1.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			t = null;
		}
	}
	
	//forTesting
	public void upgrade1Test()
	{
		if(tmpSelectedTower.getExp()==100) {
		BaseTower t = tmpSelectedTower;
		for(int i=0;i<towers.size();i++)
		{
			if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
				towers.remove(i);
		} 
		tmpSelectedTower = null;
		TowerSelected = false ;
		
		if(t instanceof FireTower) {
			towers.add(new FireTower1());
		}
		else if(t instanceof IceTower) 
			towers.add(new IceTower1());
		else if(t instanceof PoisonTower)
			towers.add(new PoisonTower1());
		t = null;
		}
		else if(Player.cash>=100) {
			modifyCash(-100);
			BaseTower t = tmpSelectedTower;
			for(int i=0;i<towers.size();i++)
			{
				if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
					towers.remove(i);
			} 
			tmpSelectedTower = null;
			TowerSelected = false ;
			
			if(t instanceof FireTower) {
				towers.add(new FireTower1());
			}
			else if(t instanceof IceTower) 
				towers.add(new IceTower1());
			else if(t instanceof PoisonTower)
				towers.add(new PoisonTower1());
			t = null;
		}
	}
	
	//Second Upgrade Option
	
	public void upgrade2()
	{
		if(tmpSelectedTower.getExp()==100) {
		BaseTower t = tmpSelectedTower;
		for(int i=0;i<towers.size();i++)
		{
		if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
			towers.remove(i);
		} 
		tmpSelectedTower = null;
		TowerSelected = false ;
		if(t instanceof FireTower)
			towers.add(new FireTower2(new Texture("fireTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		else if(t instanceof IceTower) 
			towers.add(new IceTower2(new Texture("iceTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		else if(t instanceof PoisonTower)
			towers.add(new PoisonTower2(new Texture("poisonTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
		t = null;
		}
		else if(Player.cash>=100)
		{
			modifyCash(-100);
			BaseTower t = tmpSelectedTower;
			for(int i=0;i<towers.size();i++)
			{
			if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
				towers.remove(i);
			} 
			tmpSelectedTower = null;
			TowerSelected = false ;
			if(t instanceof FireTower)
				towers.add(new FireTower2(new Texture("fireTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			else if(t instanceof IceTower) 
				towers.add(new IceTower2(new Texture("iceTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			else if(t instanceof PoisonTower)
				towers.add(new PoisonTower2(new Texture("poisonTowerEdit2.png"),t.getTile(),64,64,this.waveManager.getCurrentWave().getMonsters()));
			t = null;
		}

	} 
	
	//forTesting
		public void upgrade2Test()
		{
			if(tmpSelectedTower.getExp()==100)
			{
				BaseTower t = tmpSelectedTower;
				for(int i=0;i<towers.size();i++)
				{
					if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
						towers.remove(i);
				} 
				tmpSelectedTower = null;
				TowerSelected = false ;
				
				if(t instanceof FireTower) {
					towers.add(new FireTower2());
				}
				else if(t instanceof IceTower) 
					towers.add(new IceTower2());
				else if(t instanceof PoisonTower)
					towers.add(new PoisonTower2());
				t = null;
			}
			else if(Player.cash>=100)
			{
				modifyCash(-100);
				BaseTower t = tmpSelectedTower;
				for(int i=0;i<towers.size();i++)
				{
					if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
						towers.remove(i);
				} 
				tmpSelectedTower = null;
				TowerSelected = false ;
				
				if(t instanceof FireTower) {
					towers.add(new FireTower2());
				}
				else if(t instanceof IceTower) 
					towers.add(new IceTower2());
				else if(t instanceof PoisonTower)
					towers.add(new PoisonTower2());
				t = null;
			}
		}
	
	public void sell()
	{
		for(int i=0;i<towers.size();i++)
		{
			if(tmpSelectedTower.getmapX() == towers.get(i).getmapX() && tmpSelectedTower.getmapY() == towers.get(i).getmapY())
				towers.remove(i);
		}
		Player.modifyCash(tmpSelectedTower.getRefund());
		tmpSelectedTower = null;
		TowerSelected = false;
	}
	
	
	public ArrayList<BaseTower> getTowers() {
		return towers;
	}
	


	public void draw(Batch b)
	{
		for(Monster m: waveManager.getCurrentWave().getMonsters())
		{
			m.draw(b);
		}
		for(BaseTower t : towers)
		{
			t.draw(b);
		}
		if(holdingTower)
		{
			tmpTower.draw(b);
		}
	}
	
	public Boolean isTowerSelected() {
		return TowerSelected;
	}
	
	//Check whether the selected tower is not a special tower (for upgrade)
	
	public boolean chkLevel(BaseTower t)
	{
		if(t instanceof FireTower1 || t instanceof IceTower1 || t instanceof PoisonTower1 ||
				t instanceof FireTower2 || t instanceof IceTower2 || t instanceof PoisonTower2 ) return false;
		return true;
	}
	
	public boolean lose()
	{
		if( map.getCastle().isDestroy())
		{
			Player.lose = true;
			return map.getCastle().isDestroy();
		}
		else return false; 
	}
	
	
}
