import static org.junit.Assert.*;

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
	
	private Panel topLeft;
	private Panel topRight;
	private Panel bottomLeft;
	private Panel bottomRight;
	
	private HashMap<String, Panel> tLNeighbors;
	private HashMap<String, Panel> tRNeighbors;
	private HashMap<String, Panel> bLNeighbors;
	private HashMap<String, Panel> bRNeighbors;
	
	private Tile startTile;
	private ArrayList<Tile> outskirtTiles;
	private ArrayList<Tile> emptyOutskirtTiles;
	
	private City city;
	
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
		
		this.board = new Board(map);
		
		this.outskirtTiles = new ArrayList<Tile>();
		this.emptyOutskirtTiles = new ArrayList<Tile>();
		
		this.startTile = topLeft.getTiles()[1][1];
		
		for(int i = 0; i < 8; i++){
			this.outskirtTiles.add(new Tile (1, 1, "M", 1, 1, "N", 1, "N", 1));
		}
		
		for(int i = 0; i < 8; i++){
			this.emptyOutskirtTiles.add(new Tile(2, 2, "G", 0, 0, "N", 0, "N", 0));
		}


		this.city = new City(this.startTile);
	}

	@Test
	public void testConstruct() {
		assertNotNull(new City(new Tile (1, 1, "M", 0, 1, "N", 0, "n", 0)));
	}
	
	@Test
	public void testMulitpleCalcProduction() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		testCity.setOutskirts(outskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcProduction");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(8, output);
		
	}
	
	@Test
	public void testNoCalcProduction() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		
		testCity.setOutskirts(emptyOutskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcProduction");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(0, output);
		
	}
	
	@Test
	public void testCalcCulture() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		testCity.setOutskirts(outskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcCulture");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(8, output);
	}
	
	@Test
	public void testEmptyCalcCulture() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		testCity.setOutskirts(emptyOutskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcCulture");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(0, output);
	}
	
	
	@Test
	public void testTrade() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		testCity.setOutskirts(outskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcTrade");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(8, output);
	}
	
	@Test
	public void testEmptyTradeCalc() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		City testCity= new City(new Tile (2, 2, "M", 0, 0, "N", 0, "N", 0));
		
		testCity.setOutskirts(emptyOutskirtTiles);
		
		Method method = City.class.getDeclaredMethod("calcTrade");
		method.setAccessible(true);
		int output = (int) method.invoke(testCity);
		
		assertEquals(0, output);
	}
	
	@Test //need to test "not explored" panels
	public void testGetOutskirtsTopLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[0][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
		assertTrue(output.contains(topRight.getTiles()[3][0]));
		assertTrue(output.contains(topRight.getTiles()[3][1]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][3]));
	}
	
	@Test
	public void testGetOutskirtsTopRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomLeft.getTiles()[0][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(bottomLeft.getTiles()[0][2]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][2]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][3]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
		assertTrue(output.contains(topRight.getTiles()[3][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
	}
	
	@Test
	public void testGetOutskirtsBottomRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topLeft.getTiles()[3][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
		assertTrue(output.contains(topLeft.getTiles()[2][2]));
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][2]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(topRight.getTiles()[2][0]));
		assertTrue(output.contains(topRight.getTiles()[3][0]));
	}
	
	@Test
	public void testGetOutskirtsBottomLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topRight.getTiles()[3][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(topRight.getTiles()[2][0]));
		assertTrue(output.contains(topRight.getTiles()[2][1]));
		assertTrue(output.contains(topRight.getTiles()[3][1]));
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
		assertTrue(output.contains(topLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][3]));
		assertTrue(output.contains(bottomRight.getTiles()[0][0]));
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
	}
	
	@Test
	public void testGetOutskirtsLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[2][0];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(bottomRight.getTiles()[3][0]));
		assertTrue(output.contains(bottomRight.getTiles()[3][1]));
		assertTrue(output.contains(bottomRight.getTiles()[2][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][1]));
		assertTrue(output.contains(bottomRight.getTiles()[1][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[3][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[2][3]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][3]));
	}
	
	@Test
	public void testGetOutskirtsRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topLeft.getTiles()[1][3];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(topLeft.getTiles()[2][3]));
		assertTrue(output.contains(topLeft.getTiles()[2][2]));
		assertTrue(output.contains(topLeft.getTiles()[1][2]));
		assertTrue(output.contains(topLeft.getTiles()[0][2]));
		assertTrue(output.contains(topLeft.getTiles()[0][3]));
		assertTrue(output.contains(topRight.getTiles()[2][0]));
		assertTrue(output.contains(topRight.getTiles()[1][0]));
		assertTrue(output.contains(topRight.getTiles()[0][0]));
	}
	
	@Test
	public void testGetOutskirtsTop() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomLeft.getTiles()[0][1];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(bottomLeft.getTiles()[0][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][0]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][1]));
		assertTrue(output.contains(bottomLeft.getTiles()[1][2]));
		assertTrue(output.contains(bottomLeft.getTiles()[0][2]));
		assertTrue(output.contains(topLeft.getTiles()[3][0]));
		assertTrue(output.contains(topLeft.getTiles()[3][1]));
		assertTrue(output.contains(topLeft.getTiles()[3][2]));
	}
	
	@Test
	public void testGetOutskirtsBottom() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.topRight.getTiles()[3][2];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
		assertTrue(output.contains(topRight.getTiles()[3][3]));
		assertTrue(output.contains(topRight.getTiles()[2][3]));
		assertTrue(output.contains(topRight.getTiles()[2][2]));
		assertTrue(output.contains(topRight.getTiles()[2][1]));
		assertTrue(output.contains(topRight.getTiles()[3][1]));
		assertTrue(output.contains(bottomRight.getTiles()[0][1]));
		assertTrue(output.contains(bottomRight.getTiles()[0][2]));
		assertTrue(output.contains(bottomRight.getTiles()[0][3]));
	}
	
	@Test
	public void testGetOutskirtsCenter() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Tile tileToTest = this.bottomRight.getTiles()[1][1];
		
		Method method = City.class.getDeclaredMethod("getOutskirts", Tile.class);
		method.setAccessible(true);
		HashSet<Tile> output = (HashSet<Tile>) method.invoke(this.city, tileToTest);
		
		System.out.println(output.size());
		
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
	public void testValidOutskirts(){
		//can't test until players have more implementation
	}

}
