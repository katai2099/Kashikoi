package com.towerdefense.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

//User Interface of the game 

public class UI {
	
	ArrayList <myButton> buttons;
	ArrayList <myButton> hiddenButtons;
	BitmapFont font;
	BitmapFont expfont;
	BitmapFont numWave;
	BitmapFont coinFont;
	Player player;
	Button button;
	Texture Bar ;
	Texture barBackground;
	
	public UI(Player player)
	{
		
		coinFont = new BitmapFont();
		coinFont.setColor(Color.YELLOW);
		coinFont.getData().setScale(1.2f);
		numWave = new BitmapFont();
		numWave.setColor(Color.RED);
		numWave.getData().setScale(1.2f);
		font = new BitmapFont();
		font.setColor(Color.RED);
		font.getData().setScale(0.8f);
		expfont = new BitmapFont();
		expfont.setColor(Color.BLACK);
		buttons = new ArrayList<myButton>();
		this.player = player;
		hiddenButtons = new ArrayList<myButton>();
	    Bar = new Texture("bar.png");
	    barBackground = new Texture("bar-background.png");
	}
	
	public void addButton(String name,Texture texture,int x,int y)
	{
		buttons.add(new myButton(name,texture,x,y));
	} 
	
	public void addHiddenButton(String name,Texture texture,int x,int y)
	{
		hiddenButtons.add(new myButton(name,texture,x,y));
	}
	//check if the button is clicked by user
	public boolean isButtonClicked(String buttonname)
	{
		myButton b = getButton(buttonname);
		float MouseX = Gdx.input.getX();
		float MouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
		if(MouseX > b.getX() && MouseX < b.getX() + b.getWidth() &&
				MouseY > b.getY() && MouseY < b.getY() + b.getHeight()) return true;
		else return false; 
	}
	//check if the tower is clicked by user 
	public boolean isTowerClicked()
	{
		float MouseX = Gdx.input.getX();
		float MouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
		for(BaseTower t : player.getTowers())
		{
			if(MouseX > t.getX() && MouseX < t.getX() + t.getWidth() &&
					MouseY > t.getY() && MouseY < t.getY() + t.getHeight()) 
				{	return true;}
		}
		return false;
	}
	
	
	public myButton getButton(String buttonName)
	{
		for(myButton bu:buttons)
		{
			if(bu.getName().equals(buttonName))
			{
				return bu;
			}
		}
		for(myButton bt:hiddenButtons)
		{
			if(bt.getName().equals(buttonName))
		{
			return bt;
		}
		}
		
		return null;
	}
	
	
	public void draw(Batch b)
	{
		for(myButton bu:buttons)
		{
			b.draw(bu.getTexture(), bu.getX(), bu.getY(), 64, 64);
		}
		coinFont.draw(b,String.valueOf(Player.cash)+" G",1344,200);
		numWave.draw(b,"Wave no: "+String.valueOf(player.waveManager.waveNumber),1320,175);
		if(player.waveManager.waveNumber !=0 && player.waveManager.waveNumber %3 ==0)
			numWave.draw(b,"Monster no: "+String.valueOf(player.waveManager.monstersPerWave-1),1320,145);
		else
		numWave.draw(b,"Monster no: "+String.valueOf(player.waveManager.monstersPerWave),1320,145);
		for(myButton bt:hiddenButtons)
		{
			if(player.TowerSelected)
			{
				b.draw(bt.getTexture(), bt.getX(), bt.getY(), bt.getWidth(), bt.getHeight());	
				font.draw(b,"SELL: "+String.valueOf(player.tmpSelectedTower.getRefund()) +" G",1346,290);
				font.draw(b, "EXP: "+String.valueOf(player.tmpSelectedTower.getExp()),1345,245);
				font.draw(b, "EXP: "+String.valueOf(player.tmpSelectedTower.getX()) +" "+ String.valueOf(player.tmpSelectedTower.getY()),1345,500);
			}
		}
		b.draw(barBackground,0,0,1935,barBackground.getHeight()); 
		b.draw(Bar,-10,0,player.map.getCastle().getBarWidth(),Bar.getHeight());
	}
	

}
