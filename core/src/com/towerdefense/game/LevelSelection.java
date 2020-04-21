package com.towerdefense.game;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class LevelSelection extends ScreenAdapter{
	
	private SpriteBatch batch;
	Stage stage;
	Image levelselection;
	ButtonStyle level1;
	Button level01;
	ButtonStyle level2;
	Button level02;
	ButtonStyle level3;
	Button level03;
	ButtonStyle level4;
	Button level04;
	ButtonStyle levelDevstyle;
	Button levelDev;
	ImageButton backBtn;
	public static boolean selection;
	
	public LevelSelection() throws FileNotFoundException, IOException {
		
		selection = false; 
		this.batch = towerDefense.batch;
		this.stage = new Stage();
		levelselection = new Image(new Texture("levelSelection.png"));
		levelselection.setX((1472/2)-270);
		levelselection.setY(750);
		
		level1 = new ButtonStyle();
		level1.up = new TextureRegionDrawable(new TextureRegion(new Texture("level1.png")));
		level01 = new Button(level1);
		level01.setPosition(1472/2-270, 500);
		
		level2 = new ButtonStyle();
		level2.up = new TextureRegionDrawable(new TextureRegion(new Texture("level2.png")));
		level02 = new Button(level2);
		level02.setPosition(1472/2+100, 500);
		
		level3 = new ButtonStyle();
		level3.up = new TextureRegionDrawable(new TextureRegion(new Texture("level3.png")));
		level03 = new Button(level3);
		level03.setPosition(1472/2-450,230);

		Pixmap pixLevel04 = new Pixmap(Gdx.files.internal("level04.png"));
		Pixmap pixResizedLevel04 = new Pixmap(291, 201, pixLevel04.getFormat());
		pixResizedLevel04.drawPixmap(pixLevel04,
				0, 0, pixLevel04.getWidth(), pixLevel04.getHeight(),
				0, 0, pixResizedLevel04.getWidth(), pixResizedLevel04.getHeight()
		);
		level4 = new ButtonStyle();
		level4.up = new TextureRegionDrawable(new TextureRegion(new Texture(pixResizedLevel04)));
		level04 = new Button(level4);
		level04.setPosition(1472/2-80,230);
		
		levelDevstyle = new ButtonStyle();
		levelDevstyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("leveldevedit.png")));
		levelDev = new Button(levelDevstyle);
		levelDev.setPosition(1472/2+300, 231);

		backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Back.png"))));
		backBtn.setPosition(50, 50, Align.bottomLeft);

		level01.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
					selection = true; 
					try {
						towerDefense.setActiveScreen(new level1());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				}
				}
					);
		level02.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
					selection = true;
					try {
						towerDefense.setActiveScreen(new level2());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					return false;
				}
					);
		level03.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
						selection = true;
					try {
						towerDefense.setActiveScreen(new level3());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					return false;
				}
					);
		level04.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
						selection = true;
					
					try {
						towerDefense.setActiveScreen(new level4());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					return false;
				}
					);
		levelDev.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{	
						selection = true;
					
					try {
						towerDefense.setActiveScreen(new GameScreen());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					return false;
				}
					);

		backBtn.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
							!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
						selection = true;

						towerDefense.setActiveScreen(new MenuScreen());
					}
					return false;
				}
		);

		stage.addActor(levelselection);
		stage.addActor(level01);
		stage.addActor(level02);
		stage.addActor(level03);
		stage.addActor(level04);
		stage.addActor(levelDev);
		stage.addActor(backBtn);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta)
	{
		
		stage.act(delta);
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) towerDefense.setActiveScreen(new MenuScreen());
		//Gdx.gl.glClearColor(162/255f,206/255f ,220/255f , 1);
		Gdx.gl.glClearColor(105/255f,105/255f ,105/255f , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		stage.draw();
		batch.end();
	}
	
	@Override 
	public void hide()
	{
		stage.dispose();
	}
	
	
}
