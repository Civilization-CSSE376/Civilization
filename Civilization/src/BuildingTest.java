import static org.junit.Assert.*;

import org.junit.Test;


public class BuildingTest {
	
	
	@Test
	public void constructorTest(){
		Building target = new Building("Temple");
		assertNotNull(target);
	}
	
	@Test
	public void isUpgradedTest(){
		Building target = new Building ("Cathedral");
		assertTrue(target.isUpgraded);
		
		target = new Building ("Market");
		assertFalse(target.isUpgraded);
	}

	@Test
	public void marketCreationTest() {
		Building target = new Building("Market");
		
		assertFalse(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(1, target.trade);
		assertEquals(1, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Market", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void bankCreationTest() {
		Building target = new Building("Bank");
		
		assertTrue(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(10, target.cost);
		assertEquals(1, target.trade);
		assertEquals(1, target.production);
		assertEquals(1, target.culture);
		assertEquals(1, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Market", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void templeCreationTest() {
		Building target = new Building("Temple");
		
		assertFalse(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Temple", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void cathedralCreationTest() {
		Building target = new Building("Cathedral");
		
		assertTrue(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(10, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(3, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Temple", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void granaryCreationTest() {
		Building target = new Building("Granary");
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(5, target.cost);
		assertEquals(1, target.trade);
		assertEquals(1, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Granary", target.name);
		assertEquals(Terrain.Grassland, target.allowedTerrain);
		
	}
	
	@Test
	public void aqueductCreationTest() {
		Building target = new Building("Aqueduct");
		
		assertTrue(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(8, target.cost);
		assertEquals(2, target.trade);
		assertEquals(2, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Granary", target.name);
		assertEquals(Terrain.Grassland, target.allowedTerrain);
		
	}
	
	@Test
	public void libraryCreationTest() {
		Building target = new Building("Library");
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(5, target.cost);
		assertEquals(1, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Library", target.name);
		assertEquals(Terrain.Grassland, target.allowedTerrain);
		
	}
	
	@Test
	public void universityCreationTest() {
		Building target = new Building("University");
		
		assertTrue(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(8, target.cost);
		assertEquals(2, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Library", target.name);
		assertEquals(Terrain.Grassland, target.allowedTerrain);
		
	}
	
	@Test
	public void barracksCreationTest() {
		Building target = new Building("Barracks");
		
		assertFalse(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(2, target.trade);
		assertEquals(0, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(2, target.combatAdvantage);
		assertEquals("Barracks", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void academyCreationTest() {
		Building target = new Building("Academy");
		
		assertTrue(target.isUpgraded);
		assertTrue(target.hasStar);
		
		assertEquals(10, target.cost);
		assertEquals(2, target.trade);
		assertEquals(0, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(4, target.combatAdvantage);
		assertEquals("Barracks", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void workshopCreationTest() {
		Building target = new Building("Workshop");
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(0, target.trade);
		assertEquals(3, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Workshop", target.name);
		assertEquals(Terrain.Mountain, target.allowedTerrain);
		
	}
	
	@Test
	public void ironMineCreationTest() {
		Building target = new Building("IronMine");
		
		assertTrue(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(10, target.cost);
		assertEquals(0, target.trade);
		assertEquals(4, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Workshop", target.name);
		assertEquals(Terrain.Mountain, target.allowedTerrain);
		
	}
	
	@Test
	public void tradingPostCreationTest() {
		Building target = new Building("TradingPost");
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(2, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("TradingPost", target.name);
		assertEquals(Terrain.Desert, target.allowedTerrain);
		
	}
	
	@Test
	public void harborCreationTest() {
		Building target = new Building("Harbor");
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(2, target.trade);
		assertEquals(1, target.production);
		assertEquals(0, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Harbor", target.name);
		assertEquals(Terrain.Water, target.allowedTerrain);
		
	}

}
