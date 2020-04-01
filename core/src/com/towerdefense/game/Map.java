package com.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map  {
	
	public Tile [][] map;
	public Texture texture ;
	SpriteBatch batch;
	
	public Map()
	{
		map = new Tile[20][15];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[i].length;j++)
			{
				map[i][j]= new Tile(texture = new Texture("grass.png"),i*68,j*68,68,68);
			}
		}
	}
	
	public Map(int [][] newmap)
	{
		map = new Tile[20][15];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(newmap[i][j]==0)
				{
				map[i][j]= new Tile(texture = new Texture("concrete.png"),i*64,(j+1)*64,64,64);
				}
				else if(newmap[i][j]==1)
				{
					map[i][j] = new Tile(texture = new Texture("grass.png"),i*64,(j+1)*64,64,64);
				}
				else if(newmap[i][j]==2)
				{
					map[i][j] = new Tile(texture = new Texture("castle2.png"),i*64,j*64,64,64);
				}
			}
		}
	}
	
	public Tile getTile(int x,int y)
	{
		return map[x][y];
	}
	
	public void update()
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[i].length;j++)
			{
				Tile tmp = map[i][j];
				
				batch.draw(tmp.getTexture(),tmp.getX(),tmp.getY(),tmp.getHeight(),tmp.getWidth());
			}
		}
	}
	
	


}
