package com.towerdefense.game;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class level4 extends ScreenAdapter{
	
	SpriteBatch batch;
	Map map;
	
	public level4() throws FileNotFoundException, IOException {
		this.batch = towerDefense.batch;
		map = new Map("level04.txt");
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(162/255f,206/255f ,220/255f , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		batch.end();
	}
	
	@Override
	public void hide()
	{
		batch.dispose();
	}
	
}
