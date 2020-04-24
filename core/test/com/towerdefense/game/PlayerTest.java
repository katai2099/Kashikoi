package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	Player player = new Player();
	FireTower fireTower = new FireTower();
	IceTower iceTower = new IceTower();
	PoisonTower poisonTower = new PoisonTower();
	
	

	@Test
	public void testSell() {
		player.tmpSelectedTower=fireTower;
		player.towers.add(fireTower);
		player.sell();
		assertEquals(125,Player.cash);
	}
 
	@Test
	public void testLose() {
		player.map.castle.hp=0;
		player.lose();
		assertTrue(Player.lose);
	} 

}
