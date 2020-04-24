package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class IceTower1Test {

		Giant t = new Giant();
		IceTower1 k = new IceTower1();
		
		
		@Test
		public void testDamageMonster() {
			k.damageMonster(t); 
			float speed = t.getSpeed();
			float expected = 5.0f;
			assertEquals(expected,speed,0.0f);
		}
		
		@Test
		public void MonsterTmpFreeze() {
			k.damageMonster(t); 
			assertTrue(t.permanentSlow);
		}
		
		
}
