package com.towerdefense.game;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;


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
	
	/*int [][] size ={
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},		
			}; */
	
	public GameScreen(Game game,SpriteBatch batch) throws FileNotFoundException, IOException {
		this.game = game; 
		this.batch = batch;
		shapeDebugger = new ShapeRenderer();
		//map = new Map(size);
		//map = new Map("C:/Users/KaTaizZ/Documents/LibGDX/core/assets/level1.txt");
		map = new Map("level1.txt");
		//castle = new Castle(texture = new Texture("castle3.png"),map.getTile(18,3),70,70);
		monsterwave = new Monster[2];
		monsterwave[0] = new Monster(texture = new Texture("enemy.png"),map.getTile(3, 3),map,60,60,10,1);
		monsterwave[1] = new Monster(texture = new Texture("enemy2.png"),map.getTile(3, 3),map,60,60,10,1);
		wave = new Wave(2,monsterwave[0],5);
		tower = new FireTower(texture=new Texture("fire tower.jpg"),wave,map.getTile(5, 5),70,70);	
		player = new Player(map);
		
	}
	
	long start = System.currentTimeMillis();
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		player.update();
		if(Gdx.input.isKeyPressed(Keys.SPACE)) pause = ! pause;
		if(map.castle.isDestroy()) lose = true;
		int n = ran.nextInt(2);
		
		if(!lose&&!pause ) {
			wave.Update();
		//tower.update(wave);
		if((System.currentTimeMillis()-start)/1000>4 )
		{
			
			for(int i=0;i<wave.getMonsters().size();i++)
			{
				if(wave.getMonsters().get(i).isDead())
				{
					wave.getMonsters().remove(i);
				}
				if(map.castle.gotattacked(wave.getMonsters().get(i)))
				{
					map.castle.decreasehp(wave.getMonsters().get(i));
					barWidth -= ((wave.getMonsters().get(i).getAtk() * 2680)/100) ;
					System.out.println(map.castle.getHp());
					wave.getMonsters().remove(i);
				}
			}
		}
		}
		Gdx.gl.glClearColor(160,160 ,160 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		map.draw(batch);
		wave.draw(batch);
		tower.draw(batch);
		map.castle.draw(batch);
		player.draw(batch);
	/*	for(int i=0;i<tower.getAmmos().size();i++)
		{
			tower.getAmmos().get(i).draw(batch);
		}*/
		if(lose)
		{
			batch.draw(texture = new Texture("gameOver.png"),1280/2,960/2,texture.getWidth()/2,texture.getHeight()/2);
		}
		
		batch.draw(texture = new Texture("bar-background.png"),0,0,1935,texture.getHeight()); 
		batch.draw(texture = new Texture("bar.png"),-10,0,barWidth,texture.getHeight());
		//System.out.println((System.currentTimeMillis()-start)/1000);
//		if(shoot)
//		{
//		shapeDebugger.begin(ShapeType.Line);
//		shapeDebugger.line(2,2,100,100);
//		shapeDebugger.setColor(255,0,0,1);
//		shapeDebugger.end();
//		}
		//System.out.println(map.castle.getX()/64 +" "+ map.castle.getY()/64);
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
