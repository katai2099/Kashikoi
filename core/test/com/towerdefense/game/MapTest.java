package com.towerdefense.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	int [][] size ={
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				
	};
	
	Map map ;
	
	@Test
	public void testGetTileOutSideofIndexRange() {
		map = new Map(size);
		assertNotNull(map.getTileTest(25, 25));
	}
	@Test
	public void testGetConcreteTile() {
		//0 is Concrete
		map = new Map(size);
		assertEquals("Concrete",map.getTileTest(0, 0).getTiletype().name);
	}
	@Test
	public void testGetGrassTile() {
		//1 is GrassTile
		map = new Map(size);
		assertEquals("Grass",map.getTileTest(3, 0).getTiletype().name);
	}
	@Test
	public void testGetCastleTile() {
		//2 is CastleTile
		map = new Map(size);
		assertEquals("Castle",map.getTileTest(18, 3).getTiletype().name);
	}

}
