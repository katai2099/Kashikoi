package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class IceTower2Test {

		Giant t = new Giant();
		IceTower2 k = new IceTower2();
		
		
		@Test
		public void testDamageMonster() {
			k.damageMonster(t); 
			float speed = t.getSpeed();
			float expected = 0.0f;
			assertEquals(expected,speed,0.0f);
		
		}

}
