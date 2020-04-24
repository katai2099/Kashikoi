package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PoisonTower2Test {

	Giant t = new Giant();
	PoisonTower2 k = new PoisonTower2();
	
	
	@Test
	public void testDamageMonsterPiercethrough() {
		k.damageMonster(t); 
		assertEquals(12.0,t.getHp(),0.0f);
	}
	
	@Test
	public void testDamageMonsterNotPiercethrough() {
		k.damageMonster(t); 
		assertNotEquals(17.0,t.getHp(),0.0f);
	}
 
}
