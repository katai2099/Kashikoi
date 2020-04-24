package com.towerdefense.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Map extends Sprite {
	
	public Tile [][] map;
	public Texture texture ;
	public Castle castle;
	FileHandle handle ;
	
	public Map(String level) throws FileNotFoundException, IOException
	{
		loadMapInternal(level);
	}
	
	//For testing purpose
	
	public Map(int [][] newmap)
	{
		map = new Tile[20][15];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(newmap[i][j]==0)
				{
				map[i][j]= new Tile(texture = new Texture("concrete.png"),TileType.Concrete,i*64,(j+1)*64,64,64);
				}
				else if(newmap[i][j]==1)
				{
					map[i][j] = new Tile(texture = new Texture("grass.png"),TileType.Grass,i*64,(j+1)*64,64,64);
				}
				else if(newmap[i][j]==2)
				{
					map[i][j] = new Tile(texture = new Texture("grass.png"),TileType.Castle,i*64,(j+1)*64,64,64);
					castle = new Castle(texture = new Texture("castle3.png"),i*64,(j+1)*64,70,70);
				}
			}
		}
	}
	
	public Map() {
		castle = new Castle();
	}

	public void loadMap(String Level) throws FileNotFoundException, IOException
	{
		map = new Tile[20][15];
		BufferedReader br = new BufferedReader(new FileReader(Level));
		int x=0;
		String line;
		while((line=br.readLine())!=null)
		{
			int y=0;
			for (char file : line.toCharArray()) {

                if (file=='0') {
                    
                	map[x][y]= new Tile(texture = new Texture("concrete.png"),TileType.Concrete,x*64,(y+1)*64,64,64);
                }
                else if(file=='1')
				{
					map[x][y] = new Tile(texture = new Texture("grass.png"),TileType.Grass,x*64,(y+1)*64,64,64);
				}
				else if(file=='2')
				{
					map[x][y] = new Tile(texture = new Texture("grass.png"),TileType.Castle,x*64,(y+1)*64,64,64);
					castle = new Castle(texture = new Texture("castle3.png"),x*64,(y+1)*64,70,70);
				}
                y++;
		}
			x++;
	}
		br.close();
	}
	
	public void loadMapInternal(String Level)
	{
		map = new Tile[20][15];
		FileHandle handle = Gdx.files.internal(Level); 
		String line = handle.readString();
		Scanner sc = new Scanner(line);
		int x=0;
		while(sc.hasNext())
		{
			int y=0;
			String tmp = sc.nextLine();
			tmp.trim();
			for (char file : tmp.toCharArray()) {

                if (file=='0') {
                    
                	map[x][y]= new Tile(texture = new Texture("concrete.png"),TileType.Concrete,x*64,(y+1)*64,64,64);
                }
                else if(file=='1')
				{
					map[x][y] = new Tile(texture = new Texture("grass.png"),TileType.Grass,x*64,(y+1)*64,64,64);
				}
				else if(file=='2')
				{
					map[x][y] = new Tile(texture = new Texture("grass.png"),TileType.Castle,x*64,(y+1)*64,64,64);
					castle = new Castle(texture = new Texture("castle3.png"),x*64,(y+1)*64,70,70);
				}
                y++;
			}
			x++;
		}
		sc.close();
	}
	
	public Tile[][] getMap() {
		return map;
	}

	public void setMap(Tile[][] map) {
		this.map = map;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public Tile getTile(int x,int y)
	{
		if(x<20 && y <15 && x >-1 && y >-1) 
			return map[x][y];
		else return new Tile(texture=new Texture("badlogic.jpg"),TileType.Outsider,0,0,0,0);
	}
	
	public void draw(Batch b)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[i].length;j++)
			{
				map[i][j].draw(b);
			}
		}
		castle.draw(b);
	}
	
	
	
	


}
