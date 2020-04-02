package com.towerdefense.game.desktop;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.towerdefense.game.Castle;
import com.towerdefense.game.Tile;
import com.towerdefense.game.TileType;
import com.towerdefense.game.towerDefense;



public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new towerDefense(), config);
		config.fullscreen = false;
		config.width = 1280;//1280;
		config.height = 1024;//960;
		
	}
}
