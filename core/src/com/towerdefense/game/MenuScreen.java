package com.towerdefense.game;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class MenuScreen extends ScreenAdapter{

	ButtonStyle StartGame;
	Button startgame;
	ButtonStyle LevelSelection;
	Button levelselection;
	ButtonStyle Exit;
	Button exit;
	LabelStyle helpstyle;
	Label help;
	ImageButton musicBtn;

	Texture logoTexture;
	SpriteBatch batch;
	TextButton startGame;
	TextButtonStyle style;
	BitmapFont font;
	Stage stage;
	ButtonStyle style2;
	TextureRegion texture;
	Button b;
	
	public MenuScreen()
	{
		Image image = new Image(new Texture("logo.png"));
		image.setX(1472/2-100);
		image.setY(1024/2+150);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(1.2f);
		batch = towerDefense.batch;
	/*	style = new TextButtonStyle();
		style.font = font;
		style.fontColor = Color.BLACK;
		style.up = new TextureRegionDrawable(new Texture("seki.png")); */
	/*	style = new TextButtonStyle();
		style.font = font;
		style.fontColor = Color.BLACK;
	//	NinePatch buttonpatch = new NinePatch(new Texture("seki.png"),24,24,24,24);
	//	style.up = new NinePatchDrawable(buttonpatch);
		startGame = new TextButton("START GAME",style);
		startGame.setPosition(30, 30);
		startGame.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					try {
						towerDefense.setActiveScreen(new GameScreen());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				}
					);
		stage.addActor(startGame);*/
		stage = new Stage();
		StartGame = new ButtonStyle();
		StartGame.up = new TextureRegionDrawable(new TextureRegion(new Texture("StartGame.png")));
		startgame = new Button(StartGame);
		startgame.setPosition(1472/2-100, 500);	
		startgame.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					try {
						towerDefense.setActiveScreen(new GameScreen());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				}
					);
		LevelSelection = new ButtonStyle();
		LevelSelection.up = new TextureRegionDrawable(new TextureRegion(new Texture("SelectLevel.png")));
		levelselection = new Button(LevelSelection);
		levelselection.setPosition(1472/2-250,300);
		levelselection.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					try {
						towerDefense.setActiveScreen(new LevelSelection());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				}
					);
		
		Exit = new ButtonStyle();
		Exit.up = new TextureRegionDrawable(new TextureRegion(new Texture("Exit.png")));
		exit = new Button(Exit);
		exit.setPosition(1472/2-400,100);
		exit.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					Gdx.app.exit();
					return false;
				}
					);

		musicBtn = new ImageButton(new SpriteDrawable((new Sprite(
				new Texture("Music On.png")))));
		musicBtn.setPosition( 44, 44, Align.bottomLeft);
		musicBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (MusicManager.getInstance().isMusicon()) {
					MusicManager.getInstance().setMusicon(false);
					MusicManager.getInstance().stopMusic();
				} else {
					MusicManager.getInstance().setMusicon(true);
					MusicManager.getInstance().playMusic();
				}
			}
		});

		helpstyle = new LabelStyle(font,Color.RED);
		help = new Label("Note:Press ESC in game to pause",helpstyle);
		help.setPosition(1100, 100 );
		stage.addActor(help);
		stage.addActor(startgame);
		stage.addActor(levelselection);
		stage.addActor(image);
		stage.addActor(exit);
		stage.addActor(musicBtn);
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(float delta)
	{
		stage.act(delta);
		if(Gdx.input.isKeyJustPressed(Keys.S))
		{
			try {
				towerDefense.setActiveScreen(new GameScreen());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Gdx.gl.glClearColor(162/255f,206/255f ,220/255f , 1);
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

	@Override
	public void dispose() {
		stage.dispose();
	}
	
}
