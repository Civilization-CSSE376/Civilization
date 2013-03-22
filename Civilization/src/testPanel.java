import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class testPanel {

	@Test
	public void testJUnit() {
		assertTrue("It's working", true);
	}
	
	@Test
	public void testPanelInitializes(){
		Panel target = new Panel();
		assertNotNull(target);
	}
	
	@Test
	public void testPanelisExploredAtInitialization(){
		Panel target = new Panel();
		assertFalse(target.getIsExplored()); 
	}
	
	@Test
	public void testCanChangeisExplored(){
		Panel target = new Panel();
		target.changeIsExplored();
		assertTrue(target.getIsExplored());
	}
	
	@Test
	public void testTilesAreInitialized(){
		Panel target = new Panel();
		Tile[][] tiles = target.getTiles();
		assertEquals(4, tiles.length);
		assertNotNull(tiles[3][3]);
	}
	

}
