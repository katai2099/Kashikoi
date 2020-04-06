package com.towerdefense.game;

public class StateManager {

	public static enum GameState{
		MAINMENU , LEVEL_SELECTION , GAME
	}
	
	public static GameState gameState;
	public static MainMenu mainMenu;
	public static Game game;
	public static LevelSelection levelselect;
	
	public static void update() {
		switch(gameState) {
		case MAINMENU:
			if(mainMenu==null)
				mainMenu = new MainMenu();
			mainMenu.update();
			break;
		case LEVEL_SELECTION:
			break;
		}
	}
	
}
