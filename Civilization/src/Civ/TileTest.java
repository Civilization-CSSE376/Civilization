package Civ;
import static org.junit.Assert.*;

import org.junit.Test;


public class TileTest {

	@Test
	public void testTileInitializes() {
		Tile tile = new Tile();
		assertNotNull(tile);
	}
	
	@Test
	public void testGetXPosandYPos(){
		Tile tile = new Tile(1,2);
		assertEquals(1, tile.getxPos());
		assertEquals(2, tile.getyPos());
	}
	
	@Test
	public void testWrongTerrainAndResourceInputs(){
		Tile target = new Tile(3, 3, "X", 1, 1, "X", 1, "X", 1);
		assertNotNull(target);
	}
	
	@Test
	public void testGetMethods(){
		Tile target = new Tile(3, 3, "M", 1, 1, "W", 1, "H", 1);
		
		assertEquals(3, target.getxPos());
		assertEquals(3, target.getyPos());
		assertEquals("Mountain", target.getTerrain().toString());
		assertEquals("Wheat", target.getResource().toString());
		assertEquals(1, target.getTrade());
		assertEquals(1, target.getCulture());
		assertEquals(1, target.getCoins());
		assertEquals(1, target.getProduction());
		assertNotNull(target.getFigures());
	}
	

}
