package Civ;
import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;


public class WonderTest {
	private static final Locale currentLocale = new Locale("en", "US");
	private static final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	
	@Test
	public void construtorTest() {
		Wonder target = new Wonder("Stonehenge", messages);
		assertNotNull(target);
	}
	
	@Test
	public void stoneHengeCreationTest() {
		Wonder target = new Wonder("Stonehenge", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(1, target.tier);
		assertEquals(10, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("Stonehenge", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void theOracleCreationTest() {
		Wonder target = new Wonder("TheOracle", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(1, target.tier);
		assertEquals(15, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("TheOracle", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void theHangingGardensCreationTest() {
		Wonder target = new Wonder("TheHangingGardens", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(1, target.tier);
		assertEquals(15, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("TheHangingGardens", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void theColossusCreationTest() {
		Wonder target = new Wonder("TheColossus", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(1, target.tier);
		assertEquals(15, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(1, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("TheColossus", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void theLouvreCreationTest() {
		Wonder target = new Wonder("TheLouvre", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(2, target.tier);
		assertEquals(20, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("TheLouvre", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void angkorWatCreationTest() {
		Wonder target = new Wonder("AngkorWat", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(2, target.tier);
		assertEquals(20, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("AngkorWat", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void himejiSamuraiCastleCreationTest() {
		Wonder target = new Wonder("HimejiSamuraiCastle", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(2, target.tier);
		assertEquals(20, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("HimejiSamuraiCastle", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void PorcelainTowerCreationTest() {
		Wonder target = new Wonder("PorcelainTower", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(2, target.tier);
		assertEquals(20, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(2, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("PorcelainTower", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void statueOfLibertyCreationTest() {
		Wonder target = new Wonder("StatueOfLiberty", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(3, target.tier);
		assertEquals(25, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(3, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("StatueOfLiberty", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void sydneyOperaHouseCreationTest() {
		Wonder target = new Wonder("SydneyOperaHouse", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(3, target.tier);
		assertEquals(25, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(3, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("SydneyOperaHouse", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void unitedNationsCreationTest() {
		Wonder target = new Wonder("UnitedNations", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(3, target.tier);
		assertEquals(20, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(3, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("UnitedNations", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
	
	@Test
	public void panamaCanalCreationTest() {
		Wonder target = new Wonder("PanamaCanal", messages);
		
		assertFalse(target.hasStar);
		
		assertEquals(3, target.tier);
		assertEquals(25, target.cost);
		assertEquals(0, target.trade);
		assertEquals(0, target.production);
		assertEquals(3, target.culture);
		assertEquals(0, target.coin);
		assertEquals(0, target.combatAdvantage);
		assertEquals("PanamaCanal", target.name);
		assertEquals(Terrain.NotWater, target.allowedTerrain);
		
	}
}
