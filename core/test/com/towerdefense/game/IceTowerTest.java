package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class IceTowerTest {

	Giant t = new Giant();
	IceTower k = new IceTower();
	
	
	@Test
	public void testDamageMonster() {
		k.damageMonster(t); 
		float speed = t.getSpeed();
		float expected = 7.0f;
		assertEquals(expected,speed,0.0f);
	
	}

}
