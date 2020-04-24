package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PoisonTower1Test {

	Giant t = new Giant();
	PoisonTower1 k = new PoisonTower1();
	
	
	@Test
	public void testMonsterArmorReduction() {
		k.damageMonster(t); 
		assertEquals(2,t.getArmor(),0.0f);
	}
	
	@Test
	public void testPierceThroughEffect()
	{
		k.damageMonster(t); 
		assertTrue(t.piercethrough);
	}
	
}
