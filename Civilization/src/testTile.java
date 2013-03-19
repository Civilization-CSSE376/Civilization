import static org.junit.Assert.*;

import org.junit.Test;


public class testTile {

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
	

}
