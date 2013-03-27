import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	private static Board board;

	@BeforeClass
	public static void setupLoadedTiles() {
		File file = new File("src/Panel1.txt");
		board = new Board();
		board.readFromFile(file);
	}

	@Test
	public void testBoardInitializes() {
		Board target = new Board();
		assertNotNull(target);
	}

	@Test
	public void testLoadFromFile() {
		Tile tile = board.map.get(7).getTiles()[3][3];
		assertNotNull(tile);
	}

	@Test
	public void testLoadResources() {
		Tile wheatTile = board.map.get(0).getTiles()[0][0];
		Tile silkTile = board.map.get(0).getTiles()[1][0];
		Tile ironTile = board.map.get(0).getTiles()[2][0];
		Tile incenseTile = board.map.get(0).getTiles()[3][0];
		Tile emptyTile = board.map.get(0).getTiles()[0][1];

		assertEquals(Tile.Resource.Wheat, wheatTile.getResource());
		assertEquals(Tile.Resource.Silk, silkTile.getResource());
		assertEquals(Tile.Resource.Iron, ironTile.getResource());
		assertEquals(Tile.Resource.Incense, incenseTile.getResource());
		assertEquals(Tile.Resource.None, emptyTile.getResource());
	}

	@Test
	public void testLoadTerrain() {
		Tile mountainTile = board.map.get(0).getTiles()[0][0];
		Tile waterTile = board.map.get(0).getTiles()[1][0];
		Tile forestTile = board.map.get(0).getTiles()[2][0];
		Tile desertTile = board.map.get(0).getTiles()[3][0];
		Tile grasslandTile = board.map.get(0).getTiles()[0][1];

		assertEquals(Tile.Terrain.Mountain, mountainTile.getTerrain());
		assertEquals(Tile.Terrain.Water, waterTile.getTerrain());
		assertEquals(Tile.Terrain.Forest, forestTile.getTerrain());
		assertEquals(Tile.Terrain.Desert, desertTile.getTerrain());
		assertEquals(Tile.Terrain.Grassland, grasslandTile.getTerrain());
	}

	@Test
	public void testLoadProduction() {
		Tile noProductionTile = board.map.get(0).getTiles()[0][0];
		Tile oneProductionTile = board.map.get(0).getTiles()[1][0];
		Tile twoProductionTile = board.map.get(0).getTiles()[2][0];

		assertEquals(0, noProductionTile.getProduction());
		assertEquals(1, oneProductionTile.getProduction());
		assertEquals(2, twoProductionTile.getProduction());
	}

	@Test
	public void testLoadTrade() {
		Tile noTradeTile = board.map.get(0).getTiles()[0][0];
		Tile oneTradeTile = board.map.get(0).getTiles()[1][0];
		Tile twoTradeTile = board.map.get(0).getTiles()[2][0];

		assertEquals(0, noTradeTile.getTrade());
		assertEquals(1, oneTradeTile.getTrade());
		assertEquals(2, twoTradeTile.getTrade());
	}

	@Test
	public void testLoadCulture() {
		Tile noCultureTile = board.map.get(0).getTiles()[0][0];
		Tile oneCultureTile = board.map.get(0).getTiles()[1][0];
		Tile twoCultureTile = board.map.get(0).getTiles()[2][0];

		assertEquals(0, noCultureTile.getCulture());
		assertEquals(1, oneCultureTile.getCulture());
		assertEquals(2, twoCultureTile.getCulture());
	}

	@Test
	public void testLoadCoins() {
		Tile noCoinTile = board.map.get(0).getTiles()[0][0];
		Tile oneCoinTile = board.map.get(0).getTiles()[1][0];
		Tile twoCoinTile = board.map.get(0).getTiles()[2][0];

		assertEquals(0, noCoinTile.getCoins());
		assertEquals(1, oneCoinTile.getCoins());
		assertEquals(2, twoCoinTile.getCoins());
	}

	@Test
	public void testLoadHutsandVillages() {
		Tile hutTile = board.map.get(0).getTiles()[0][0];
		Tile villageTile = board.map.get(0).getTiles()[1][0];
		Tile blankTile = board.map.get(0).getTiles()[2][0];

		assertEquals(Settler.class, hutTile.getFigures().get(0).getClass());
		assertEquals(Army.class, villageTile.getFigures().get(0).getClass());
		assertEquals(new ArrayList<Figure>(), blankTile.getFigures());
	}

//	@Test
//	public void testPhaseChanges() {
//		assertEquals(board.getPlayer1(), board.getCurrentPlayer());
//		board.endPhase();
//		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
//		assertEquals(1, board.getPhase());
//		board.endPhase();
//		assertEquals(board.getPlayer1(), board.getCurrentPlayer());
//		assertEquals(2, board.getPhase());
//		for (int i = 0; i < 7; i++) {
//			board.endPhase();
//		}
//		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
//		assertEquals(5, board.getPhase());
//		board.endPhase();
//		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
//		assertEquals(1, board.getPhase());
//		
//	}

}
