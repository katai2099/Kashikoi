package com.towerdefense.game;



import java.awt.MouseInfo;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;


public class GameScreen extends ScreenAdapter{
	Button k;
	public static final int GAME_HEIGHT=234;
	public static final int GAME_WIDTH=256;
	
	private Game game;
	private SpriteBatch batch;
	Texture texture;
	
	Map map;
	Castle castle;
	Monster monster;
	boolean lose = false;
	Wave wave;
	Random ran = new Random();
	Monster [] monsterwave = new Monster[2];
	BaseTower tower;
	ShapeRenderer shapeDebugger;

	
	
	int [][] size ={
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
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
					
				
					
			};
	
	public GameScreen(Game game,SpriteBatch batch) {
		this.game = game; 
		this.batch = batch;
		shapeDebugger = new ShapeRenderer();
		//basetower = new baseTower(texture = new Texture("grass.png"));
		map = new Map(size);
		castle = new Castle(texture = new Texture("castle3.png"),map.getTile(5,3),70,70);
		monster = new Monster(texture = new Texture("enemy.png"),map.getTile(0, 3),40,40,2,2);
		monsterwave = new Monster[2];
		monsterwave[0] = new Monster(texture = new Texture("enemy.png"),map.getTile(0, 3),60,60,2,1);
		monsterwave[1] = new Monster(texture = new Texture("enemy2.png"),map.getTile(0, 3),60,60,2,1);
		wave = new Wave(2,monsterwave);
		tower = new FireTower(texture=new Texture("baseTower1.png"),wave,map.getTile(5, 5),70,70);	
	}
	long start = System.currentTimeMillis();
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
			
		int n = ran.nextInt(2);
		//System.out.println(n);
		wave.Update();
		tower.update(wave);
		if((System.currentTimeMillis()-start)/1000>4)
		{
			System.out.println(wave.getMonsters().get(0).getHp() + " " +wave.getMonsters().get(0).isDead());
			for(int i=0;i<wave.getMonsters().size();i++)
			{
				if(wave.getMonsters().get(i).isDead())
				{
					wave.getMonsters().remove(i);
				}
			}
			Rectangle k = wave.getMonsters().get(0).getBoundingRectangle();
			Rectangle ok = castle.getBoundingRectangle();
			if(
					ok.contains(k)
			  ) lose = true;
		}
		Gdx.gl.glClearColor(160,160 ,160 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
	
		
		for(int i=0;i<map.map.length;i++)
		{
			for(int j=0;j<map.map[i].length;j++)
			{
				Tile tmp = map.map[i][j];
				tmp.draw(batch);
			}
		}
		
		for(int i=0;i<wave.getMonsters().size();i++)
		{
			wave.getMonsters().get(i).draw(batch);
		} 
		
		tower.draw(batch);

		castle.draw(batch);
		for(int i=0;i<tower.getAmmos().size();i++)
		{
			tower.getAmmos().get(i).draw(batch);
			//batch.draw(tower.getAmmos().get(i).getTexture(),tower.getAmmos().get(i).getX(),tower.getAmmos().get(i).getY(),tower.getAmmos().get(i).getWidth()/2+5,tower.getAmmos().get(i).getHeight()/2+5);
		}
		if(lose)
		{
			batch.draw(texture = new Texture("gameOver.png"),1080/2,960/2,texture.getWidth()/2,texture.getHeight()/2);
		}
		//System.out.println((System.currentTimeMillis()-start)/1000);
//		if(shoot)
//		{
//		shapeDebugger.begin(ShapeType.Line);
//		shapeDebugger.line(2,2,100,100);
//		shapeDebugger.setColor(255,0,0,1);
//		shapeDebugger.end();
//		}
		System.out.println(map.getTile(2, 2).getX() + " " + map.getTile(2, 2).getY());
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
