package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class CastleTest {

	@Test
	public void test() {
		Monster k = new Monster (10);
		Castle test = new Castle();
		test.decreasehp(k.getAtk());
		assertEquals(90,test.getHp());
	}
	
	@Test
	public void testDamageMonster() {
		Onion t = new Onion();
		FireTower k = new FireTower();
		
		k.damageMonster(t); 
		
		float hp = t.getHp();
		float expected = 1.0f;
		assertEquals(expected,hp,0.0f);
	}

	

}
