import static org.junit.Assert.*;

import org.junit.Test;


public class GreatPersonTest {

	@Test
	public void constructorTest() {
		GreatPerson target = new GreatPerson("Prophet");
		assertNotNull(target);
	}
	
	@Test
	public void isInReverseTest(){
		GreatPerson target = new GreatPerson("Merchant");
		assertFalse(target.isInReserve);
		target.isInReserve = true;
		assertTrue(target.isInReserve);
	}
	
	@Test
	public void poetCreationTest() {
		GreatPerson target = new GreatPerson("Poet");
		
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
		GreatPerson target = new GreatPerson("Prophet");
		
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
		GreatPerson target = new GreatPerson("Explorer");
		
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
		GreatPerson target = new GreatPerson("General");
		
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
		GreatPerson target = new GreatPerson("Scientist");
		
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
		GreatPerson target = new GreatPerson("Merchant");
		
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
