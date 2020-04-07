package com.towerdefense.game;

import java.awt.Font;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public class UI {
	
	ArrayList <myButton> buttons;
	Font awtFont;
	Player player;
	Button button;
	
	public UI(Player player)
	{
		buttons = new ArrayList<myButton>();
		awtFont = new Font("Times New Roman",Font.BOLD,14);
		this.player = player;
	}
	
	public void addButton(String name,Texture texture,int x,int y)
	{
		buttons.add(new myButton(name,texture,x,y));
	}
	
	public boolean isButtonClicked(String buttonname)
	{
		myButton b = getButton(buttonname);
		float MouseX = Gdx.input.getX();
		float MouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
		if(MouseX > b.getX() && MouseX < b.getX() + b.getWidth() &&
				MouseY > b.getY() && MouseY < b.getY() + b.getHeight()) return true;
		else return false;
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
		return null;
	}
	
	public void draw(Batch b)
	{
		for(myButton bu:buttons)
		{
			if(!bu.name.equals("Levelup"))
			b.draw(bu.getTexture(), bu.getX(), bu.getY(), bu.getWidth(), bu.getHeight());
			if(bu.name.equals("Levelup") && player.isTowerClick())
				b.draw(bu.getTexture(), bu.getX(), bu.getY(), bu.getWidth(), bu.getHeight());
		}
	}
	

}
