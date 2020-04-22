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

}
