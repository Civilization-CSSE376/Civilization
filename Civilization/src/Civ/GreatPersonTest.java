package Civ;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;


public class GreatPersonTest {
	private static final Locale currentLocale = new Locale("en", "US");
	private static final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

	@Test
	public void constructorTest() {
		GreatPerson target = new GreatPerson("Prophet", messages);
		assertNotNull(target);
	}
	
	@Test
	public void isInReverseTest(){
		GreatPerson target = new GreatPerson("Merchant", messages);
		assertTrue(target.isInReserve);
		target.isInReserve = false;
		assertFalse(target.isInReserve);
	}
	
	@Test
	public void poetCreationTest() {
		GreatPerson target = new GreatPerson("Poet", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(1, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Poet", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void prophetCreationTest() {
		GreatPerson target = new GreatPerson("Prophet", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(1, target.trade);
		assertEquals(1, target.production);
		assertEquals(1, target.culture);
		assertEquals(1, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Prophet", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}

	@Test
	public void explorerCreationTest() {
		GreatPerson target = new GreatPerson("Explorer", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(1, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Explorer", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void generalCreationTest() {
		GreatPerson target = new GreatPerson("General", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(4, target.combatAdvantage);
		assertEquals("General", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void scientistCreationTest() {
		GreatPerson target = new GreatPerson("Scientist", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(2, target.trade);
		assertEquals(1, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Scientist", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void merchantCreationTest() {
		GreatPerson target = new GreatPerson("Merchant", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(0, target.cost);
		assertEquals(0, target.trade);
		assertEquals(2, target.production);
		assertEquals(0, target.culture);
		assertEquals(1, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Merchant", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
}
