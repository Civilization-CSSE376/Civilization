package Civ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Civ.Player;
import TechCards.*;

public class TechCardTest {
	
	Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player();
		player.speed = 2;
	}

	@Test
	public void animalHusbandryTest() {
		TechCard target = new AnimalHusbandry();
		target.takeEffect(player);
		assertEquals("AnimalHusbandry", target.name);
		assertEquals(1, target.tier);
	}
	
	@Test
	public void atomicTheoryTest(){
		TechCard target = new AtomicTheory();
		target.takeEffect(player);
		assertEquals("AtomicTheory", target.name);
		assertEquals(4, target.tier);
	}
	
	@Test
	public void ballisticsTest(){
		TechCard target = new Ballistics();
		target.takeEffect(player);
		assertEquals("Ballistics", target.name);
		assertEquals(4, target.tier);
		assertEquals(4, player.artilleryLevel);
	}
	
	@Test
	public void bankingTest(){
		TechCard target = new Banking();
		target.takeEffect(player);
		assertEquals("Banking", target.name);
		assertEquals(3, target.tier);
		assertTrue(player.unlockedBuildings.contains("Bank"));
	}
	
	@Test
	public void biologyTest(){
		TechCard target = new Biology();
		target.takeEffect(player);
		assertEquals("Biology", target.name);
		assertEquals(3, target.tier);
		assertEquals(5, player.stackSize);
	}
	
	@Test
	public void chivalryTest(){
		TechCard target = new Chivalry();
		target.takeEffect(player);
		assertEquals("Chivalry", target.name);
		assertEquals(2, target.tier);
		assertEquals(2, player.cavalryLevel);
		assertTrue(player.unlockedGovernments.contains("Feudalism"));
	}
	
	@Test
	public void civilServicesTest(){
		TechCard target = new CivilServices();
		int handsize = player.handSize;
		int gold = player.gold;
		target.takeEffect(player);
		assertEquals("CivilServices", target.name);
		assertEquals(2, target.tier);
		assertEquals(gold + 1, player.gold);
		assertEquals(handsize + 1, player.handSize);
	}
	
	@Test
	public void codeOfLawsTest(){
		TechCard target = new CodeOfLaws();
		target.takeEffect(player);
		assertEquals("CodeOfLaws", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("TradingPost"));
		assertTrue(player.unlockedGovernments.contains("Republic"));
	}
	
	@Test
	public void combustionTest(){
		TechCard target = new Combustion();
		target.takeEffect(player);
		assertEquals("Combustion", target.name);
		assertEquals(4, target.tier);
		assertEquals(4, player.cavalryLevel);
	}
	
	@Test
	public void communsimTest(){
		TechCard target = new Communism();
		target.takeEffect(player);
		assertEquals("Communism", target.name);
		assertEquals(3, target.tier);
		assertTrue(player.unlockedGovernments.contains("Communism"));
	}
	
	@Test
	public void computersTest(){
		TechCard target = new Computers();
		int gold = player.gold;
		target.takeEffect(player);
		assertEquals("Computers", target.name);
		assertEquals(4, target.tier);
		assertEquals(gold + 1, player.gold);
	}
	
	@Test
	public void constructionTest(){
		TechCard target = new Construction();
		target.takeEffect(player);
		assertEquals("Construction", target.name);
		assertEquals(2, target.tier);
		assertTrue(player.unlockedBuildings.contains("Workshop"));
	}
	
	@Test
	public void currencyTest(){
		TechCard target = new Currency();
		target.takeEffect(player);
		assertEquals("Currency", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Market"));
	}
	
	@Test
	public void democracyTest(){
		TechCard target = new Democracy();
		target.takeEffect(player);
		assertEquals("Democracy", target.name);
		assertEquals(2, target.tier);
		assertEquals(2, player.infantryLevel);
		assertTrue(player.unlockedGovernments.contains("Democracy"));
	}
	
	@Test
	public void engineeringTest(){
		TechCard target = new Engineering();
		target.takeEffect(player);
		assertEquals("Engineering", target.name);
		assertEquals(2, target.tier);
		assertTrue(player.unlockedBuildings.contains("Aqueduct"));
	}
	
	@Test
	public void flightTest(){
		TechCard target = new Flight();
		target.takeEffect(player);
		assertEquals("Flight", target.name);
		assertEquals(4, target.tier);
		assertEquals(6, player.speed);
	}
	
	@Test
	public void gunpowderTest(){
		TechCard target = new Gunpowder();
		target.takeEffect(player);
		assertEquals("Gunpowder", target.name);
		assertEquals(3, target.tier);
		assertEquals(3, player.infantryLevel);
	}
	
	@Test
	public void horsebackRidingTest(){
		TechCard target = new HorsebackRiding();
		target.takeEffect(player);
		assertEquals("HorsebackRiding", target.name);
		assertEquals(1, target.tier);
		assertEquals(3, player.speed);
	}
	
	@Test
	public void irrigationTest(){
		TechCard target = new Irrigation();
		target.takeEffect(player);
		assertEquals("Irrigation", target.name);
		assertEquals(2, target.tier);
		assertEquals(3, player.cityLimit);
	}
	
	@Test
	public void masonryTest(){
		TechCard target = new Masonry();
		target.takeEffect(player);
		assertEquals("Masonry", target.name);
		assertEquals(1, target.tier);
		assertEquals(3, player.stackSize);
	}
	
	@Test
	public void massMediaTest(){
		TechCard target = new MassMedia();
		target.takeEffect(player);
		assertEquals("MassMedia", target.name);
		assertEquals(4, target.tier);
	}
	
	@Test
	public void mathematicsTest(){
		TechCard target = new Mathematics();
		target.takeEffect(player);
		assertEquals("Mathematics", target.name);
		assertEquals(2, target.tier);
		assertEquals(2, player.artilleryLevel);
	}
	
	@Test
	public void metalCastingTest(){
		TechCard target = new MetalCasting();
		int gold = player.gold;
		target.takeEffect(player);
		assertEquals("MetalCasting", target.name);
		assertEquals(3, target.tier);
		assertEquals(3, player.artilleryLevel);
		assertEquals(gold + 1, player.gold);
	}
	
	@Test
	public void metalWorkingTest(){
		TechCard target = new MetalWorking();
		target.takeEffect(player);
		assertEquals("MetalWorking", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Barracks"));
	}
	
	@Test
	public void militaryScienceTest(){
		TechCard target = new MilitaryScience();
		target.takeEffect(player);
		assertEquals("MilitaryScience", target.name);
		assertEquals(3, target.tier);
		assertTrue(player.unlockedBuildings.contains("Academy"));
	}
	
	@Test
	public void monarchyTest(){
		TechCard target = new Monarchy();
		target.takeEffect(player);
		assertEquals("Monarchy", target.name);
		assertEquals(2, target.tier);
		assertTrue(player.unlockedGovernments.contains("Monarchy"));
	}
	
	@Test
	public void navigationTest(){
		TechCard target = new Navigation();
		target.takeEffect(player);
		assertEquals("Navigation", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Harbor"));
	}
	
	@Test
	public void philosophyTest(){
		TechCard target = new Philosophy();
		target.takeEffect(player);
		assertEquals("Philosophy", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Temple"));
	}
	
	@Test
	public void potteryTest(){
		TechCard target = new Pottery();
		target.takeEffect(player);
		assertEquals("Pottery", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Granary"));
	}
	
	@Test
	public void printingPressTest(){
		TechCard target = new PrintingPress();
		target.takeEffect(player);
		assertEquals("PrintingPress", target.name);
		assertEquals(2, target.tier);
		assertEquals(4, player.stackSize);
		assertTrue(player.unlockedBuildings.contains("University"));
	}
	
	@Test
	public void railroadsTest(){
		TechCard target = new Railroad();
		target.takeEffect(player);
		assertEquals("Railroad", target.name);
		assertEquals(3, target.tier);
		assertEquals(3, player.cavalryLevel);
		assertTrue(player.unlockedBuildings.contains("IronMine"));
	}
	
	@Test
	public void replaceablePartsTest(){
		TechCard target = new ReplaceableParts();
		target.takeEffect(player);
		assertEquals("ReplaceableParts", target.name);
		assertEquals(4, target.tier);
		assertEquals(6, player.stackSize);
		assertEquals(4, player.infantryLevel);
	}
	
	@Test
	public void sailingTest(){
		TechCard target = new Sailing();
		target.takeEffect(player);
		assertEquals("Sailing", target.name);
		assertEquals(2, target.tier);
		assertEquals(4, player.speed);
	}
	
	@Test
	public void steamPowerTest(){
		TechCard target = new SteamPower();
		target.takeEffect(player);
		assertEquals("SteamPower", target.name);
		assertEquals(3, target.tier);
		assertEquals(5, player.speed);
	}
	
	@Test
	public void theologyTest(){
		TechCard target = new Theology();
		int handsize = player.handSize;
		target.takeEffect(player);
		assertEquals("Theology", target.name);
		assertEquals(3, target.tier);
		assertEquals(handsize + 1, player.handSize);
		assertTrue(player.unlockedBuildings.contains("Cathedral"));
		assertTrue(player.unlockedGovernments.contains("Fundamentalism"));
	}
	
	@Test
	public void writingTest(){
		TechCard target = new Writing();
		target.takeEffect(player);
		assertEquals("Writing", target.name);
		assertEquals(1, target.tier);
		assertTrue(player.unlockedBuildings.contains("Library"));
	}

}
