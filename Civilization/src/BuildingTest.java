import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;


public class BuildingTest {
	private static final Locale currentLocale = new Locale("en", "US");
	private static final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	
	@Test
	public void constructorTest(){
		Building target = new Building("Temple", messages);
		assertNotNull(target);
	}
	
	@Test
	public void isUpgradedTest(){
		Building target = new Building ("Cathedral", messages);
		assertTrue(target.isUpgraded);
		
		target = new Building ("Market", messages);
		assertFalse(target.isUpgraded);
	}

	@Test
	public void marketCreationTest() {
		Building target = new Building("Market", messages);
		
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
		Building target = new Building("Bank", messages);
		
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
		Building target = new Building("Temple", messages);
		
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
		Building target = new Building("Cathedral", messages);
		
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
		Building target = new Building("Granary", messages);
		
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
		Building target = new Building("Aqueduct", messages);
		
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
		Building target = new Building("Library", messages);
		
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
		Building target = new Building("University", messages);
		
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
		Building target = new Building("Barracks", messages);
		
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
		Building target = new Building("Academy", messages);
		
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
		Building target = new Building("Workshop", messages);
		
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
		Building target = new Building("Iron Mine", messages);
		
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
		Building target = new Building("Trading Post", messages);
		
		assertFalse(target.isUpgraded);
		assertFalse(target.hasStar);
		
		assertEquals(7, target.cost);
		assertEquals(2, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Trading Post", target.name);
		assertEquals(Terrain.Desert, target.allowedTerrain);
		
	}
	
	@Test
	public void harborCreationTest() {
		Building target = new Building("Harbor", messages);
		
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
//	
//	@Test
//	public void testNameTranslation() {
//		String[] englishNames = {"Market", "Bank", "Temple", "Cathedral", "Granary", "Aqueduct", "Library", "University", "Barracks", "Academy", "Workshop", "Iron Mine", "Trading Post", "Harbor"};
//		String[] spanishNames = {"Mercado", "Banco", "Templo", "Catedral", "Granero", "Acueducto", "Biblioteca", "Universidad", "Cuartel", "Academia", "Taller", "Mina de Hierro", "Factoria", "Puerto"};
//		Building target;
//		for(int i = 0; i < englishNames.length; i++){
//			target = new Building(englishNames[i], messages);
//			System.out.println(target.name + " ... " + englishNames[i]);
//			assertTrue(target.name.equals(englishNames[i]));
//			target = new Building(spanishNames[i], messages);
//			assertTrue(target.name.equals(englishNames[i]));
//		}
//	}
	
	

}
