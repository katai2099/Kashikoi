package com.towerdefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class towerDefense extends Game {
	private SpriteBatch batch;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this,batch));
		
		
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
