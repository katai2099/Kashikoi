package com.towerdefense.game;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class towerDefense extends BaseGame {
	//public static Game game;
	public static SpriteBatch batch;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	/*	try {
			setActiveScreen(new GameScreen());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
	
		/*	try {
				setActiveScreen(new GameScreen());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			setActiveScreen(new MenuScreen());
	/*	try {
			setActiveScreen(new LevelSelection());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
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
