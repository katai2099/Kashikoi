package com.towerdefense.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BaseTowerTest {

	Giant test = new Giant();
	Giant test2 = new Giant();
	ArrayList <Monster> tes = new ArrayList <Monster> ();;
	BaseTower t = new BaseTower();
	
	@Test
	public void testAimTargetNotInRange() {
		test.setX(500);
		test.setY(500);
		test2.setX(900);
		test2.setY(400);
		tes.add(test);
		tes.add(test2);
		t.setRange(256);
		t.updateMonsterList(tes);
		t.target = t.aimTarget();
		assertFalse(t.lockOn);
	}
	
	@Test
	public void testAimTargetInRange() {
		test.y=100;
		test.x=100;
		test2.x=200;
		test2.y=200;
		tes.add(test);
		tes.add(test2);
		t.setRange(256);
		t.updateMonsterList(tes);
		t.target = t.aimTarget();
		assertTrue(t.lockOn);
	}
	
	
	

}
