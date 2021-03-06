package com.towerdefense.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.towerdefense.game.towerDefense;



public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.fullscreen = false;
		config.width = 1472;//1280;
		config.height = 1024;//960;

		new LwjglApplication(new towerDefense(), config);
	}
}
