import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class BoardTest {

	@Test
	public void testBoardInitializes() {
		Board target = new Board();
		assertNotNull(target);
	}
	
	@Test
	public void testLoadFromFile(){
		File file = new File("src/Panel1.txt");
		Board target = new Board();
		target.readFromFile(file);
		Tile tile = target.map.get(2).getTiles()[3][3];
		assertNotNull(tile);
		
	}

}
