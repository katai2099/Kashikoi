package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class FireTowertestt {

	Onion t = new Onion();
	FireTower k = new FireTower();
	
	
	
	@Test
	public void testDamageMonster() {
		
		k.damageMonster(t); 
		float hp = t.getHp();
		float expected = 2.0f;
		assertEquals(expected,hp,0.0f);
	}

}
