package Civ;

import static org.junit.Assert.*;

import org.junit.Test;


public class UnitTest {

	@Test
	public void constructorTest() {
		Unit target = new Unit("Airplane", 2);
		assertNotNull(target);
	}

	@Test
	public void infantryLevelOneTest(){
		Unit target = new Unit("Infantry", 1);
		assertEquals("Infantry", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void infantryLevelTwoTest(){
		Unit target = new Unit("Infantry", 2);
		assertEquals("Infantry", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void infantryLevelThreeTest(){
		Unit target = new Unit("Infantry", 3);
		assertEquals("Infantry", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void infantryLevelFourTest(){
		Unit target = new Unit("Infantry", 4);
		assertEquals("Infantry", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void calvaryLevelOneTest(){
		Unit target = new Unit("Calvary", 1);
		assertEquals("Calvary", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void calvaryLevelTwoTest(){
		Unit target = new Unit("Calvary", 2);
		assertEquals("Calvary", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void calvaryLevelThreeTest(){
		Unit target = new Unit("Calvary", 3);
		assertEquals("Calvary", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void calvaryLevelFourTest(){
		Unit target = new Unit("Calvary", 4);
		assertEquals("Calvary", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void artilleryLevelOneTest(){
		Unit target = new Unit("Artillery", 1);
		assertEquals("Artillery", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void artilleryLevelTwoTest(){
		Unit target = new Unit("Artillery", 2);
		assertEquals("Artillery", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void artilleryLevelThreeTest(){
		Unit target = new Unit("Artillery", 3);
		assertEquals("Artillery", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void artilleryLevelFourTest(){
		Unit target = new Unit("Artillery", 4);
		assertEquals("Artillery", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void airplaneTest(){
		Unit target = new Unit("Airplane", 1);
		assertEquals("Airplane", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(12, target.cost);
		assertTrue(target.attack > 4 && target.attack < 8);
	}
	
	@Test
	public void randomAirAttackBaseTest(){
		
	}
	
}
