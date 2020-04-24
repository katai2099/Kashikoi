package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class OnionTest {
	
	Onion onion = new Onion();
	IceTower1 icy1 = new IceTower1();
	IceTower2 icy2 = new IceTower2();
	PoisonTower1 poison1 = new PoisonTower1();
	PoisonTower2 poison2 = new PoisonTower2();
	
	@Test
	public void IceTower1Test() { 
		icy1.damageMonster(onion);
		assertFalse(onion.permanentSlow);
	}
	
	@Test
	public void IceTower2Test() {
		icy2.damageMonster(onion);
		assertEquals(10,onion.getSpeed(),0.0f);
	}
	
	@Test
	public void PoisonTower1Test() {
		poison1.damageMonster(onion);
		assertFalse(onion.piercethrough);
	}
	
	@Test
	public void PoisonTower2Test() {
		poison2.damageMonster(onion);
		assertEquals(2,onion.getHp(),0.0f);
	}
	

}
