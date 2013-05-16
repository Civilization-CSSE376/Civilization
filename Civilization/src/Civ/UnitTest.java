package Civ;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;



public class UnitTest {

	private static Locale currentLocale = new Locale("en", "US");
	private static ResourceBundle messages = ResourceBundle.getBundle(
			"MessagesBundle", currentLocale);
	
	@Test
	public void constructorTest() {
		Unit target = new Unit("Airplane", 2, messages);
		assertNotNull(target);
	}

	@Test
	public void infantryLevelOneTest(){
		Unit target = new Unit("Infantry", 1, messages);
		assertEquals("Infantry", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void infantryLevelTwoTest(){
		Unit target = new Unit("Infantry", 2, messages);
		assertEquals("Infantry", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void infantryLevelThreeTest(){
		Unit target = new Unit("Infantry", 3, messages);
		assertEquals("Infantry", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void infantryLevelFourTest(){
		Unit target = new Unit("Infantry", 4, messages);
		assertEquals("Infantry", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void calvaryLevelOneTest(){
		Unit target = new Unit("Calvary", 1, messages);
		assertEquals("Calvary", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void calvaryLevelTwoTest(){
		Unit target = new Unit("Calvary", 2, messages);
		assertEquals("Calvary", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void calvaryLevelThreeTest(){
		Unit target = new Unit("Calvary", 3, messages);
		assertEquals("Calvary", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void calvaryLevelFourTest(){
		Unit target = new Unit("Calvary", 4, messages);
		assertEquals("Calvary", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void artilleryLevelOneTest(){
		Unit target = new Unit("Artillery", 1, messages);
		assertEquals("Artillery", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(5, target.cost);
		assertTrue(target.attack > 0 && target.attack < 4);
		
	}
	
	@Test
	public void artilleryLevelTwoTest(){
		Unit target = new Unit("Artillery", 2, messages);
		assertEquals("Artillery", target.type);
		assertEquals(2, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(7, target.cost);
		assertTrue(target.attack > 1 && target.attack < 5);
		
	}
	
	@Test
	public void artilleryLevelThreeTest(){
		Unit target = new Unit("Artillery", 3, messages);
		assertEquals("Artillery", target.type);
		assertEquals(3, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(9, target.cost);
		assertTrue(target.attack > 2 && target.attack < 6);
		
	}
	
	@Test
	public void artilleryLevelFourTest(){
		Unit target = new Unit("Artillery", 4, messages);
		assertEquals("Artillery", target.type);
		assertEquals(4, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(11, target.cost);
		assertTrue(target.attack > 3 && target.attack < 7);
		
	}
	
	@Test
	public void airplaneTest(){
		Unit target = new Unit("Airplane", 1, messages);
		assertEquals("Airplane", target.type);
		assertEquals(1, target.level);
		assertEquals(target.attack, target.health);
		assertEquals(12, target.cost);
		assertTrue(target.attack > 4 && target.attack < 8);
	}
	
	@Test
	public void randomnessTest(){
		List<String> possibleUnits = 
				Arrays.asList("Infantry", "Cavalry", "Artillery", "Airplane");
		for(int i = 0; i < 20; i++){
		Collections.shuffle(possibleUnits);
		Unit target = new Unit(possibleUnits.get(0), 1, messages);
		}
	}
	
}
