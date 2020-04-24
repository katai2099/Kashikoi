package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SekiroDieTwiceTest {

	SekiroDieTwice test = new SekiroDieTwice();
	FireTower ftest = new FireTower();
	
	@Test
	public void reviveTest() {
		ftest.damageMonster(test);
		ftest.damageMonster(test);
		assertEquals(100,test.getHp(),0.0f);
	}
	
	@Test
	public void reviveYetTest() {
		ftest.damageMonster(test);
		ftest.damageMonster(test);
		assertTrue(test.reviveYet);
	}
	
	@Test
	public void notReviveYetTest() {
		ftest.damageMonster(test);
		assertFalse(test.reviveYet);
	}
	
	
	
	

}
