package com.towerdefense.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class towerDefense extends BaseGame {
	//public static Game game;
	public static SpriteBatch batch;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
			setActiveScreen(new MenuScreen());
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
