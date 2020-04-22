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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class level3 extends ScreenAdapter{
	
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
	ButtonStyle nextMapstyle ;
	Button resume;
	ButtonStyle resumestyle;
	Button exit; 
	ButtonStyle exitstyle;
	Button exitWin;
	ButtonStyle exitWinstyle;
	Button nextMap;
	ButtonStyle losestyle;
	Button forlose;
	Stage winner;
	Stage pauseMenu;
	Stage Lost;
	public level3() throws FileNotFoundException, IOException {
		
		this.batch = towerDefense.batch;
		map = new Map("level03.txt");
		monsterwave = new Monster[10];
		monsterwave[4] = new SekiroDieTwice(map.getTile(0, 6),map,64,64);
		monsterwave[0] = new Giant(map.getTile(0, 6),map,64,64);
		monsterwave[1] = new Fugu(map.getTile(0, 6),map,64,64);
		monsterwave[2] = new Squirrel(map.getTile(0,6),map,64,64);
		monsterwave[3] = new Onion(map.getTile(0, 6),map,64,64); 
		monsterwave[9] = new SekiroDieTwice(map.getTile(0, 11),map,64,64);
		monsterwave[5] = new Giant(map.getTile(0, 11),map,64,64);
		monsterwave[6] = new Fugu(map.getTile(0,11),map,64,64);
		monsterwave[7] = new Squirrel(map.getTile(0,11),map,64,64);
		monsterwave[8] = new Onion(map.getTile(0, 11),map,64,64); 
		//wavemanager = new WaveManager(monsterwave[4],4,5,3);	checking single enemy
		wavemanager= new WaveManager(monsterwave,3,6,1); //time , perWave , wavenum
		player = new Player(map,wavemanager);
		setupUI();
		nextMapstyle = new ButtonStyle();
		nextMapstyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("NEXTLEVEL.png")));
		resumestyle = new ButtonStyle();
		resumestyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("Resume.png")));
		exitstyle= new ButtonStyle();
		exitstyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("EXITpause.png")));
		exitWinstyle = new ButtonStyle();
		exitWinstyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("EXITpause.png")));
		losestyle = new ButtonStyle();
		losestyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("EXITpause.png")));
		forlose = new Button(losestyle);
		forlose.setPosition(1460/2-400,1080/2-200);
		exitWin = new Button(exitWinstyle);
		exitWin.setPosition(1460/2-400,1080/2-200);
		exit = new Button(exitstyle);
		exit.setPosition(1460/2-400,1080/2-200);
		resume = new Button(resumestyle);
		resume.setPosition(1460/2-400, 700-200);
		nextMap = new Button(nextMapstyle);
		nextMap.setPosition(1460/2-400, 700-200);
		winner = new Stage();
		pauseMenu = new Stage();
		Lost = new Stage();
		nextMap.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					try {
						towerDefense.setActiveScreen(new level1());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				}
					);
		resume.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					{
					pause = !pause;
					Gdx.input.setInputProcessor(null);
					}
					return false;
				}
					);
		exit.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					{
					towerDefense.setActiveScreen(new MenuScreen());
					pauseMenu.dispose();
					winner.dispose();
					}
					return false;
				}
					);
		
		exitWin.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					{
					towerDefense.setActiveScreen(new MenuScreen());
					pauseMenu.dispose();
					winner.dispose();
					}
					return false;
				}
					);
		
		forlose.addListener(
				(Event e) ->
				{
					if(!(e instanceof InputEvent) ||
						!((InputEvent)e).getType().equals(Type.touchDown))
						return false;
					
					{
					towerDefense.setActiveScreen(new MenuScreen());
					pauseMenu.dispose();
					winner.dispose();
					Lost.dispose();
					}
					return false;
				}
					);
		Lost.addActor(forlose);
		winner.addActor(nextMap);
		winner.addActor(exitWin);
		pauseMenu.addActor(exit);
		pauseMenu.addActor(resume);
	}
	void setupUI() {
		towerui = new UI(player);
		towerui.addButton("fireTower", new Texture("fire tower.jpg"), 1344 , 896); 
		towerui.addButton("iceTower",new Texture("ice tower.jpg"), 1344 , 768);
		towerui.addButton("poisonTower",new Texture("poison tower.jpg"), 1344, 640);
		towerui.addButton("pause",new Texture("pause.png"), 1349, 537);
		towerui.addHiddenButton("Sell", new Texture("canvas.png"), 1376-35, 264);
		towerui.addHiddenButton("levelup1", new Texture("levelup1Edit.png"), 1280+20, 328);
		towerui.addHiddenButton("levelup2", new Texture("levelup2Edit.png"), 1368+20, 328);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(!pause) {
		if(!Player.end) {
			System.out.println(map.getTile(18, 10).getTiletype().name);
		player.update();
		System.out.println(LevelSelection.selection);
		
		boolean tmp = Gdx.input.isButtonJustPressed(0);
		//System.out.println(tmp);
		if(tmp) {
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
			if(towerui.isButtonClicked("pause"))
			{
				pause = !pause;
				Gdx.input.setInputProcessor(null);
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
		
		if(towerui.isButtonClicked("levelup1") && player.isTowerSelected() && player.tmpSelectedTower.exp==100 && player.chkLevel(player.tmpSelectedTower))
		{ 
			player.upgrade1();
		}
		else if(towerui.isButtonClicked("levelup1") && player.isTowerSelected() && player.cash>=100 && player.chkLevel(player.tmpSelectedTower))
		{
			player.upgrade1();
			player.cashUpgrade();
		}
		if(towerui.isButtonClicked("levelup2") && player.isTowerSelected() && player.tmpSelectedTower.exp==100 && player.chkLevel(player.tmpSelectedTower))
		{
			player.upgrade2();
		} 
		else if(towerui.isButtonClicked("levelup2") && player.isTowerSelected() && player.cash>=100 && player.chkLevel(player.tmpSelectedTower))
		{
			player.upgrade2();
			player.cashUpgrade();
		}
		
		if(!towerui.isTowerClicked() && !towerui.isButtonClicked("levelup1"))
		{
			player.tmpSelectedTower = null;
			player.TowerSelected = false;
		}
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
		if(pause)
		{
			pauseMenu.act(Gdx.graphics.getDeltaTime());
			pauseMenu.draw();
			Gdx.input.setInputProcessor(pauseMenu);
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
		{
			pause = !pause;
			Gdx.input.setInputProcessor(null);
		}
		
		if(Player.win && LevelSelection.selection)
		{
			Lost.act(Gdx.graphics.getDeltaTime());
			Lost.draw();
			Gdx.input.setInputProcessor(Lost);
		}
		else if(Player.lose && LevelSelection.selection)
		{
			Lost.act(Gdx.graphics.getDeltaTime());
			Lost.draw();
			Gdx.input.setInputProcessor(Lost);
		}
		else {
		
		if(Player.win)
		{
			winner.act(Gdx.graphics.getDeltaTime());
			winner.draw();
			Gdx.input.setInputProcessor(winner);
		} 
		if(Player.lose)
		{
			Lost.act(Gdx.graphics.getDeltaTime());
			Lost.draw();
			Gdx.input.setInputProcessor(Lost);
		}
		
		}
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
