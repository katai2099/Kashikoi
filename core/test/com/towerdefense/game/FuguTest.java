package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuguTest {

	Fugu test = new Fugu();
	FireTower firetower = new FireTower();
	
	@Test
	public void testIncreaseArmor() {
		firetower.damageMonster(test);
		assertEquals(3,test.getArmor(),0.0f);
	}
	
	@Test
	public void testIncreaseArmorMaximum() {
		test.def = 12;
		firetower.damageMonster(test);
		assertEquals(12,test.getArmor(),0.0f);
	}
	
	@Test
	public void testCannotIncreaseArmorWhenPiercethrough() {
		
		test.def=3;
		test.piercethrough = true; 
		firetower.damageMonster(test);
		assertEquals(3,test.getArmor(),0.0f);
	}
	
	

}
