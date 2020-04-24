package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	Player player = new Player();
	FireTower fireTower = new FireTower();
	IceTower iceTower = new IceTower();
	PoisonTower poisonTower = new PoisonTower(); 
	FireTower1 fireTower1 = new FireTower1();

	@Test
	public void testSellBaseTower() {
		player.tmpSelectedTower=fireTower;
		player.towers.add(fireTower);
		player.sell();
		assertEquals(125,Player.cash);
	}
	
	@Test
	public void testSellSpecialTower() {
		player.tmpSelectedTower=fireTower1;
		player.towers.add(fireTower1);
		player.sell();
		assertEquals(150,Player.cash);
	}
	
	@Test
	public void testTowerSizeAfterSell() {
		player.tmpSelectedTower=fireTower1;
		player.towers.add(fireTower1);
		player.sell();
		assertEquals(0,player.towers.size());
	}
	
	@Test
	public void testMultipleTowerSizeAfterSell() {
		player.tmpSelectedTower=fireTower1;
		iceTower.x=200;
		iceTower.y=200;
		poisonTower.x=500;
		poisonTower.y=500;
		player.towers.add(fireTower1);
		player.towers.add(iceTower);
		player.towers.add(poisonTower);
		player.sell();
		assertEquals(2,player.towers.size());
	}
 
	@Test
	public void testLose() {
		player.map.castle.hp=0;
		player.lose();
		assertTrue(Player.lose);
	} 
	
	@Test
	public void upgrade1()
	{
		player.tmpSelectedTower=fireTower;
		player.towers.add(fireTower);
		player.upgrade1Test();
		assertTrue(player.towers.get(0) instanceof FireTower1);
	}
	
	@Test 
	public void upgrade2()
	{
		player.tmpSelectedTower=fireTower;
		player.towers.add(fireTower);
		player.upgrade2Test();
		assertTrue(player.towers.get(0) instanceof FireTower2);
	}
	
	

}
