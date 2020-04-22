package com.towerdefense.game;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FireTowerTest {

	@Test
	void testDamageMonster() {
		Onion t = new Onion();
		FireTower k = new FireTower();
		
		k.damageMonster(t); 
		
		float hp = t.getHp();
		float expected = 2.0f;
		assertEquals(expected,hp,0.0f);
	}

}
