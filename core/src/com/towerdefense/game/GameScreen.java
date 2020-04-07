package com.towerdefense.game;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class GameScreen extends ScreenAdapter{
	
	TextButton k;
	public static final int GAME_HEIGHT=234;
	public static final int GAME_WIDTH=256;
	FileHandle handle;
	private Game game;
	private SpriteBatch batch;
	Texture texture;
	Texture Bar = new Texture("bar.png");
	float barWidth=2680;
	WaveManager wavemanager;
	UI towerui;
	BitmapFont font;
	Map map;
	//Castle castle;
	Monster monster;
	boolean pause=false;
	boolean lose = false;
	Wave wave;
	Random ran = new Random();
	Monster [] monsterwave = new Monster[2];
	BaseTower tower;
	Player player;
	ShapeRenderer shapeDebugger;
	Stage uiStage;
	TextButtonStyle ui;
	
	public GameScreen(Game game,SpriteBatch batch) throws FileNotFoundException, IOException {
		this.game = game; 
		this.batch = batch;
		map = new Map("level1.txt");
		
		monsterwave = new Monster[5];
		monsterwave[4] = new SekiroDieTwice(map.getTile(3, 0),map,64,64);
		monsterwave[0] = new Giant(map.getTile(3, 0),map,64,64);
		monsterwave[1] = new Fugu(map.getTile(3, 0),map,64,64);
		monsterwave[2] = new Squirrel(map.getTile(3,0),map,64,64);
		monsterwave[3] = new Onion(map.getTile(3, 0),map,64,64);
		wavemanager= new WaveManager(monsterwave,5,4);
		player = new Player(map,wavemanager);
		font = new BitmapFont();
		font.setColor(Color.MAGENTA);
		setupUI();
		ui = new TextButtonStyle();
		ui.font = font;
		ui.fontColor = Color.MAGENTA;
		k = new TextButton("Level up",ui);
		k.setText("Level up");
		k.setPosition(1344, 594);
		k.setVisible(true);
		uiStage = new Stage();
		uiStage.addActor(k);
	}
	void setupUI() {
		towerui = new UI(player);
		towerui.addButton("fireTower", new Texture("fire tower.jpg"), 1344 , 896); 
		towerui.addButton("iceTower",new Texture("ice tower.jpg"), 1344 , 768);
		towerui.addButton("poisonTower",new Texture("poison tower.jpg"), 1344, 640);
		towerui.addButton("Levelup", new Texture("levelupbutton.png"), 1344, 264);
	}
	
	long start = System.currentTimeMillis();
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		player.update();
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) pause = ! pause;
		boolean tmp = Gdx.input.isButtonJustPressed(0);
		if(tmp) {
		if(towerui.isButtonClicked("fireTower"))
		{
			player.pickTower(new BaseTower(new Texture("fire tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,10,wavemanager.getCurrentWave().getMonsters()));
		}
		if(towerui.isButtonClicked("iceTower"))
		{
			player.pickTower(new BaseTower(new Texture("ice tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,10,wavemanager.getCurrentWave().getMonsters()));
		}
		if(towerui.isButtonClicked("poisonTower"))
		{
			player.pickTower(new BaseTower(new Texture("poison tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,10,wavemanager.getCurrentWave().getMonsters()));
		}
		}
		if(map.castle.isDestroy()) lose = true;
		Gdx.gl.glClearColor(.192f,.192f ,.200f , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		towerui.draw(batch);
		player.draw(batch);
		
		font.draw(batch,String.valueOf(Player.cash),1344,200);
		if(lose)
		{
			batch.draw(texture = new Texture("gameOver.png"),1280/2,960/2,texture.getWidth()/2,texture.getHeight()/2);
		}
		
		batch.draw(texture = new Texture("bar-background.png"),0,0,1935,texture.getHeight()); 
		batch.draw(texture = new Texture("bar.png"),-10,0,map.getCastle().getBarWidth(),texture.getHeight());
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
