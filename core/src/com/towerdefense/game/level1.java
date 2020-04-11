package com.towerdefense.game;



import java.io.FileNotFoundException;
import java.io.IOException;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class level1 extends ScreenAdapter{
	
	FileHandle handle;
	private SpriteBatch batch;
	Texture texture;
	Texture Bar = new Texture("bar.png");
	WaveManager wavemanager;
	UI towerui;
	Map map;
	Monster monster;
	boolean pause=false;
	Wave wave;
	Monster [] monsterwave ;
	BaseTower tower;
	Player player;
	float nextMap;
	
	public level1() throws FileNotFoundException, IOException {
		
		this.batch = towerDefense.batch;
		map = new Map("level01.txt");
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
	//	System.out.println(Gdx.graphics.getDeltaTime()*10);
		Gdx.gl.glClearColor(162/255f,206/255f ,220/255f , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		batch.end();
		
	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		//batch.dispose();
		texture.dispose();
		Bar.dispose();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
}
