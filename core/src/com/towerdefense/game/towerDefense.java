package com.towerdefense.game;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class towerDefense extends Game {
	private SpriteBatch batch;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		try {
			setScreen(new GameScreen(this,batch));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
