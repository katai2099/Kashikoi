package com.towerdefense.game;

public enum TileType {
	
	Land("Land",true) , Sahara("Sahara",false) , Lake("Lake",false);
	
	String name;
	boolean buildable;
	
	 TileType(String name,boolean builable)
	 {
		 this.name = name ;
		 this.buildable = builable;
	 }

}
