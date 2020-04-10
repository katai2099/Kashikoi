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


public class GameScreen extends ScreenAdapter{
	
	TextButton k;
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
	
	public GameScreen() throws FileNotFoundException, IOException {
		
		this.batch = towerDefense.batch;
		map = new Map("level1.txt");
		monsterwave = new Monster[5];
		monsterwave[4] = new SekiroDieTwice(map.getTile(3, 0),map,64,64);
		monsterwave[0] = new Giant(map.getTile(3, 0),map,64,64);
		monsterwave[1] = new Fugu(map.getTile(3, 0),map,64,64);
		monsterwave[2] = new Squirrel(map.getTile(3,0),map,64,64);
		monsterwave[3] = new Onion(map.getTile(3, 0),map,64,64); 
		//wavemanager = new WaveManager(monsterwave[4],4,5,3);	checking single enemy
		wavemanager= new WaveManager(monsterwave,3,6,5); //time , perWave , wavenum
		player = new Player(map,wavemanager);
		setupUI();
	}
	void setupUI() {
		towerui = new UI(player);
		towerui.addButton("fireTower", new Texture("fire tower.jpg"), 1344 , 896); 
		towerui.addButton("iceTower",new Texture("ice tower.jpg"), 1344 , 768);
		towerui.addButton("poisonTower",new Texture("poison tower.jpg"), 1344, 640);
		towerui.addHiddenButton("Sell", new Texture("canvas.png"), 1376-35, 264);
		towerui.addHiddenButton("levelup1", new Texture("levelup1Edit.png"), 1280+20, 328);
		towerui.addHiddenButton("levelup2", new Texture("levelup2Edit.png"), 1368+20, 328);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(!Player.end) {
		player.update();
		if(Gdx.input.isKeyPressed(Keys.SPACE)) pause = ! pause;
		boolean tmp = Gdx.input.isButtonJustPressed(0);
		//System.out.println(tmp);
		if(tmp) {
			/*
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
		}*/
			if(towerui.isButtonClicked("fireTower"))
			{
				player.pickTower(new FireTower(new Texture("fire tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,wavemanager.getCurrentWave().getMonsters()));
			}
			if(towerui.isButtonClicked("iceTower"))
			{
				player.pickTower(new IceTower(new Texture("ice tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,wavemanager.getCurrentWave().getMonsters()));
			}
			if(towerui.isButtonClicked("poisonTower"))
			{
				player.pickTower(new PoisonTower(new Texture("poison tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,wavemanager.getCurrentWave().getMonsters()));
			}
		if(towerui.isTowerClicked())
		{
			player.saveTower();
			System.out.println("tower click" + Gdx.input.getX() +" "+ Gdx.input.getY());
		}
		if(towerui.isButtonClicked("Sell") && player.isTowerSelected())
		{
			player.sell();
		}
		
		if(towerui.isButtonClicked("levelup1") && player.isTowerSelected() && player.tmpSelectedTower.exp==100)
		{ 
			player.upgrade1();
		}
		
		if(towerui.isButtonClicked("levelup2") && player.isTowerSelected() && player.tmpSelectedTower.exp==100)
		{
			player.upgrade2();
		} 
		if(!towerui.isTowerClicked() && !towerui.isButtonClicked("levelup1"))
		{
			player.tmpSelectedTower = null;
			player.TowerSelected = false;
		}
		}
		
		}
	//	System.out.println(Gdx.graphics.getDeltaTime()*10);
		Gdx.gl.glClearColor(162/255f,206/255f ,220/255f , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		towerui.draw(batch);
		player.draw(batch);
		if(Player.lose)
		{
			batch.draw(texture = new Texture("gameOver.png"),1280/2,960/2,texture.getWidth()/2,texture.getHeight()/2);
		}
		if(Player.win)
		{
			batch.draw(texture = new Texture("unnamed.png"),(1280/2)-200,960/2,texture.getWidth()/2,texture.getHeight()/2);
			
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
		batch.dispose();
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
