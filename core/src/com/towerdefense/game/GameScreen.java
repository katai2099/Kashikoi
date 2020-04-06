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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;


public class GameScreen extends ScreenAdapter{
	
	Button k;
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
	
	
	public GameScreen(Game game,SpriteBatch batch) throws FileNotFoundException, IOException {
		this.game = game; 
		this.batch = batch;
		map = new Map("level1.txt");
		
		monsterwave = new Monster[2];
		monsterwave[0] = new Monster(texture = new Texture("enemy.png"),map.getTile(3, 0),map,60,60,10,1);
		wavemanager= new WaveManager(monsterwave[0],5,4);
		player = new Player(map,wavemanager);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		setupUI();
	}
	void setupUI() {
		towerui = new UI();
		towerui.addButton("fireTower", new Texture("fire tower.jpg"), 1344 , 896); 
		towerui.addButton("iceTower",new Texture("ice tower.jpg"), 1344 , 768);
		towerui.addButton("poisonTower",new Texture("poison tower.jpg"), 1344, 640);
		
	}
	
	long start = System.currentTimeMillis();
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		player.update();
		//wavemanager.update();
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) pause = ! pause;
		boolean tmp = Gdx.input.isButtonJustPressed(0);
		if(tmp) {
		if(towerui.isButtonClicked("fireTower"))
		{
			player.pickTower(new BaseTower(new Texture("fire tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,5,wavemanager.getCurrentWave().getMonsters()));
		}
		if(towerui.isButtonClicked("iceTower"))
		{
			player.pickTower(new BaseTower(new Texture("ice tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,5,wavemanager.getCurrentWave().getMonsters()));
		}
		if(towerui.isButtonClicked("poisonTower"))
		{
			player.pickTower(new BaseTower(new Texture("poison tower.jpg"),map.getTile(Gdx.input.getX()/64,(((Gdx.graphics.getHeight()-Gdx.input.getY())-64)/64)),64,64,5,wavemanager.getCurrentWave().getMonsters()));
		}
		
		}
		if(map.castle.isDestroy()) lose = true;
	//	int n = ran.nextInt(2);
		
		/*if(!lose&&!wavemanager.currentWave.isCompleted() ) {
		
		
			
			for(int i=0;i<wavemanager.getCurrentWave().getMonsters().size();i++)
			{
				if(map.castle.gotattacked(wavemanager.getCurrentWave().getMonsters().get(i)))
				{
					map.castle.decreasehp(wave.getMonsters().get(i));
					barWidth -= ((wavemanager.getCurrentWave().getMonsters().get(i).getAtk() * 2680)/100) ;
					System.out.println(map.castle.getHp());
					wavemanager.getCurrentWave().getMonsters().get(i).die();
				}
			}
		
		} */
		//System.out.println(wavemanager.getCurrentWave().getMonsters().get(0).getHp());
	//	System.out.println(Gdx.graphics.getDeltaTime()*60);
		Gdx.gl.glClearColor(160,160 ,160 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		towerui.draw(batch);
		//wavemanager.draw(batch);
		//ui.draw(batch);
		//tower.draw(batch);
		player.draw(batch);
		font.draw(batch,String.valueOf(player.getCash()),1344,200);
		if(lose)
		{
			batch.draw(texture = new Texture("gameOver.png"),1280/2,960/2,texture.getWidth()/2,texture.getHeight()/2);
		}
		
		batch.draw(texture = new Texture("bar-background.png"),0,0,1935,texture.getHeight()); 
		batch.draw(texture = new Texture("bar.png"),-10,0,map.getCastle().getBarWidth(),texture.getHeight());
		//System.out.println((System.currentTimeMillis()-start)/1000);
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
