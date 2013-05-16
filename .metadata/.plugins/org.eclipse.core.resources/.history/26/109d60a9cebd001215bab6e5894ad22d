package Civ;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;



public class CityTest {
	
	private Board board;
	private Hashtable<String, Panel> map;
	private Player player;
	private Player enemyPlayer;
	
	private Figure friendlyFigure;
	private Figure enemyFigure;
	
	private Panel topLeft;
	private Panel topRight;
	private Panel bottomLeft;
	private Panel bottomRight;
	
	private HashMap<String, Panel> tLNeighbors;
	private HashMap<String, Panel> tRNeighbors;
	private HashMap<String, Panel> bLNeighbors;
	private HashMap<String, Panel> bRNeighbors;
	
	private Tile startTile;
	private Tile enemyStartTile;	
	private ArrayList<Tile> outskirtTiles;
	private ArrayList<Tile> emptyOutskirtTiles;
	
	private City city;
	private City enemyCity;
	
	@Before
	public void setUp() throws Exception{
		this.topLeft = new Panel();
		this.topRight = new Panel();
		this.bottomLeft = new Panel();
		this.bottomRight = new Panel();
		
		this.topLeft.changeIsExplored();
		this.topRight.changeIsExplored();
		this.bottomLeft.changeIsExplored();
		this.bottomRight.changeIsExplored();
		
		/*
		 * Setting specific tile terrains to make sure
		 * the randomness of the Tile(int x, int y) constructor
		 * does not effect key positions for the testing of
		 * validOutskirts
		 */
		this.topLeft.getTiles()[1][1].setTerrain("F");
		this.topLeft.getTiles()[3][1].setTerrain("F");
		this.bottomLeft.getTiles()[0][1].setTerrain("F");
		this.bottomLeft.getTiles()[1][0].setTerrain("F");
		this.bottomLeft.getTiles()[0][3].setTerrain("F");
		this.bottomLeft.getTiles()[2][2].setTerrain("F");
		this.bottomLeft.getTiles()[2][3].setTerrain("W");//this one is the for the "water test" of validOutskirts
		this.bottomLeft.getTiles()[3][2].setTerrain("F");
		this.bottomRight.getTiles()[2][1].setTerrain("F");
		this.bottomRight.getTiles()[2][2].setTerrain("F");
		
		this.tLNeighbors = new HashMap<String, Panel>();
		this.tRNeighbors = new HashMap<String, Panel>();
		this.bLNeighbors = new HashMap<String, Panel>();
		this.bRNeighbors = new HashMap<String, Panel>();
		
		this.tLNeighbors.put("South", bottomLeft);
		this.tLNeighbors.put("East", topRight);
		this.tLNeighbors.put("North", null);
		this.tLNeighbors.put("West", null);
		
		this.tRNeighbors.put("West", topLeft);
		this.tRNeighbors.put("South", bottomRight);
		this.tRNeighbors.put("North", null);
		this.tRNeighbors.put("East", null);
		
		this.bLNeighbors.put("North", topLeft);
		this.bLNeighbors.put("East", bottomRight);
		this.bLNeighbors.put("South", null);
		this.bLNeighbors.put("West", null);
		
		this.bRNeighbors.put("North", topRight);
		this.bRNeighbors.put("West", bottomLeft);
		this.bRNeighbors.put("South", null);
		this.bRNeighbors.put("East", null);
		
		this.topLeft.setNeighbors(tLNeighbors);
		this.topRight.setNeighbors(tRNeighbors);
		this.bottomLeft.setNeighbors(bLNeighbors);
		this.bottomRight.setNeighbors(bRNeighbors);
		
		this.map = new Hashtable<String, Panel>();
		this.map.put("topLeft", topLeft);
		this.map.put("topRight", topRight);
		this.map.put("bottomLeft", bottomLeft);
		this.map.put("bottomRight", bottomRight);
		
		this.outskirtTiles = new ArrayList<Tile>();
		this.emptyOutskirtTiles = new ArrayList<Tile>();
		
		this.startTile = topLeft.getTiles()[1][1];
		this.enemyStartTile = bottomRight.getTiles()[2][2];
		
		this.player = new Player();
		this.enemyPlayer = new Player();
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player);
		players.add(this.enemyPlayer);
		this.board = new Board(this.map, players);
//		this.board = new Board(this.map);
		
		this.city = new City(this.startTile);
		this.player.cities.add(this.city);
		
		this.enemyCity = new City(this.enemyStartTile);
		this.enemyPlayer.cities.add(this.enemyCity);
		
		this.friendlyFigure = new Settler(this.player, bottomLeft.getTiles()[3][2]);
		this.player.figures.add(this.friendlyFigure);
		
		this.enemyFigure = new Settler(this.enemyPlayer, bottomLeft.getTiles()[1][0]);
		this.enemyPlayer.figures.add(this.enemyFigure);
		
		for(int i = 0; i < 8; i++){
			this.outskirtTiles.add(new Tile (1, 1, "M", 1, 1, "N", 1, "N", 1));
		}
		
		for(int i = 0; i < 8; i++){
			this.emptyOutskirtTiles.add(new Tile(2, 2, "G", 0, 0, "N", 0, "N", 0));
		}

		this.city = new City(this.startTile, this.player);
		this.enemyCity = new City(this.enemyStartTile, this.enemyPlayer);
		
		
	}

	@Test
	public void testConstruct() {
		assertNotNull(this.city);
	}
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testMulitpleCalcProduction() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
//		
//		this.city.setOutskirts(outskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcProduction");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(8, output);
//		
//	}
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testNoCalcProduction() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
//		
//		this.city.setOutskirts(emptyOutskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcProduction");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(0, output);
//		
//	}
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testCalcCulture() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		
//		this.city.setOutskirts(outskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcCulture");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(8, output);
//	}
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testEmptyCalcCulture() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//				
//		this.city.setOutskirts(emptyOutskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcCulture");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(0, output);
//	}
	
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testTrade() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//	
//		this.city.setOutskirts(outskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcTrade");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(8, output);
//	}
	
//	@Test //this test no longer works with the current test scenario because we can no longer "inject" our own outskirts to test
//	public void testEmptyTradeCalc() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		
//		this.city.setOutskirts(emptyOutskirtTiles);
//		
//		Method method = City.class.getDeclaredMethod("calcTrade");
//		method.setAccessible(true);
//		int output = (int) method.invoke(this.city);
//		
//		assertEquals(0, output);
//	}

	@Test //need to test "not explored" panels
	public void testGetOutskirtsTopLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[0][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);
		
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
		assertTrue(output.contains(topRight.getTiles()[0][3]));
		assertTrue(output.contains(topRight.getTiles()[1][3]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][1]));
	}
	
	@Test
	public void testGetOutskirtsTopRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomLeft.getTiles()[3][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(bottomLeft.getTiles()[2][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[2][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][1]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
		assertTrue(output.contains(topRight.getTiles()[0][3]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
	}
	
	@Test
	public void testGetOutskirtsBottomRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topLeft.getTiles()[3][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
		assertTrue(output.contains(topLeft.getTiles()[2][2]));
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[2][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(topRight.getTiles()[0][2]));
		assertTrue(output.contains(topRight.getTiles()[0][3]));
	}
	
	@Test
	public void testGetOutskirtsBottomLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topRight.getTiles()[0][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(topRight.getTiles()[0][2]));
		assertTrue(output.contains(topRight.getTiles()[1][2]));
		assertTrue(output.contains(topRight.getTiles()[1][3]));
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
	}
	
	@Test
	public void testGetOutskirtsLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[0][2];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(bottomRight.getTiles()[0][3]));
		assertTrue(output.contains(bottomRight.getTiles()[1][3]));
		assertTrue(output.contains(bottomRight.getTiles()[1][2]));
		assertTrue(output.contains(bottomRight.getTiles()[1][1]));
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][2]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][1]));
	}
	
	@Test
	public void testGetOutskirtsRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topLeft.getTiles()[3][1];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
		assertTrue(output.contains(topLeft.getTiles()[2][2]));
		assertTrue(output.contains(topLeft.getTiles()[2][1]));
		assertTrue(output.contains(topLeft.getTiles()[2][0]));
		assertTrue(output.contains(topLeft.getTiles()[3][0]));
		assertTrue(output.contains(topRight.getTiles()[0][2]));
		assertTrue(output.contains(topRight.getTiles()[0][1]));
		assertTrue(output.contains(topRight.getTiles()[0][0]));
	}
	
	@Test
	public void testGetOutskirtsTop() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomLeft.getTiles()[1][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(bottomLeft.getTiles()[0][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[2][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[2][0]));
		assertTrue(output.contains(topLeft.getTiles()[0][3]));
		assertTrue(output.contains(topLeft.getTiles()[1][3]));
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
	}
	
	@Test
	public void testGetOutskirtsBottom() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topRight.getTiles()[2][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(topRight.getTiles()[3][3]));
		assertTrue(output.contains(topRight.getTiles()[3][2]));
		assertTrue(output.contains(topRight.getTiles()[2][2]));
		assertTrue(output.contains(topRight.getTiles()[1][2]));
		assertTrue(output.contains(topRight.getTiles()[1][3]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
		assertTrue(output.contains(bottomRight.getTiles()[2][0]));
		assertTrue(output.contains(bottomRight.getTiles()[3][0]));
	}
	
	@Test
	public void testGetOutskirtsCenter() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[1][1];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		ArrayList<Tile> output = (ArrayList<Tile>) method.invoke(this.city, tileToTest);		
		
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
		assertTrue(output.contains(bottomRight.getTiles()[0][2]));
		assertTrue(output.contains(bottomRight.getTiles()[1][2]));
		assertTrue(output.contains(bottomRight.getTiles()[2][2]));
		assertTrue(output.contains(bottomRight.getTiles()[2][1]));
		assertTrue(output.contains(bottomRight.getTiles()[2][0]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
	}
	
	@Test
	public void testSetCapital() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		this.city.setCapital();
		
		Field field = City.class.getDeclaredField("isCapital");
		field.setAccessible(true);
		boolean output = (boolean) field.get(this.city);
		
		assertTrue(output);
	}
	
	@Test
	public void testGetCapital() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Field field = City.class.getDeclaredField("isCapital");
		field.setAccessible(true);
		field.set(this.city, true);
		
		assertTrue(this.city.getIsCapital());
		
		field.set(this.city, false);
		
		assertFalse(this.city.getIsCapital());
	}
	
	@Test
	public void testSetHasAction() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		this.city.setHasAction(false);
		
		Field field = City.class.getDeclaredField("hasAction");
		field.setAccessible(true);
		boolean output = (boolean) field.get(this.city);
		
		assertFalse(output);
		
		this.city.setHasAction(true);
		
		output = (boolean) field.get(this.city);
		
		assertTrue(output);
	}
	
	@Test
	public void testGetLocation() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		
		Point2D.Double screenLocation = new Point2D.Double(1.35, 4.56);
		
		Field field = City.class.getDeclaredField("screenLocation");
		field.setAccessible(true);
		field.set(this.city, screenLocation);

		assertEquals(screenLocation, this.city.getLocation());
	}
	
	@Test
	public void testValidOutskirts() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{ //need to test villages and huts
		Field field = Panel.class.getDeclaredField("isExplored");
		field.setAccessible(true);
		field.set(this.topRight, false);
		
		//Too close to friendly city
		City testCity = new City(this.topLeft.getTiles()[3][2], this.player);
		assertFalse(testCity.isValid);
		
		//Too close to enemy figure
		testCity = new City(this.bottomLeft.getTiles()[0][1], this.player);
		assertFalse(testCity.isValid);
		
		//On top of water
		testCity = new City(this.bottomLeft.getTiles()[2][3], this.player);
		assertFalse(testCity.isValid);
		
		//next to friendly figure
		testCity = new City(this.bottomLeft.getTiles()[2][2], this.player);
		assertTrue(testCity.isValid);
		
		//too close to enemy city
		testCity = new City(this.bottomRight.getTiles()[2][1], this.player);
		assertFalse(testCity.isValid);
	}

}
