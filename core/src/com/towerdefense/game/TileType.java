package com.towerdefense.game;

public enum TileType {
	
	Land("Land",true) , Concrete("Concrete",true) , Castle("Castle",false), Grass("Grass",false), Outsider("Edge",false);
	
	String name;
	boolean buildable;
	
	 TileType(String name,boolean builable)
	 {
		 this.name = name ;
		 this.buildable = builable;
	 }

}
