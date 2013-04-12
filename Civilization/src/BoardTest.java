import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	private static Board board;

	@BeforeClass
	public static void setupLoadedTiles() {
		File file = new File("src/Panel1.txt");
		board = new Board("America", "China");
		board.readFromFile(file);
	}

	@Test
	public void testBoardInitializes() {
		Board target = new Board("America", "China");
		assertNotNull(target);
	}

	@Test
	public void testLoadFromFile() {
		Tile tile = board.map.get(7).getTiles()[3][3];
		assertNotNull(tile);
	}

	// @Test
	// public void testLoadResources() {
	// Tile wheatTile = board.map.get(0).getTiles()[0][0];
	// Tile silkTile = board.map.get(0).getTiles()[1][0];
	// Tile ironTile = board.map.get(0).getTiles()[2][0];
	// Tile incenseTile = board.map.get(0).getTiles()[3][0];
	// Tile emptyTile = board.map.get(0).getTiles()[0][1];
	//
	// assertEquals(Tile.Resource.Wheat, wheatTile.getResource());
	// assertEquals(Tile.Resource.Silk, silkTile.getResource());
	// assertEquals(Tile.Resource.Iron, ironTile.getResource());
	// assertEquals(Tile.Resource.Incense, incenseTile.getResource());
	// assertEquals(Tile.Resource.None, emptyTile.getResource());
	// }
	//
	// @Test
	// public void testLoadTerrain() {
	// Tile mountainTile = board.map.get(0).getTiles()[0][0];
	// Tile waterTile = board.map.get(0).getTiles()[1][0];
	// Tile forestTile = board.map.get(0).getTiles()[2][0];
	// Tile desertTile = board.map.get(0).getTiles()[3][0];
	// Tile grasslandTile = board.map.get(0).getTiles()[0][1];
	//
	// assertEquals(Tile.Terrain.Mountain, mountainTile.getTerrain());
	// assertEquals(Tile.Terrain.Water, waterTile.getTerrain());
	// assertEquals(Tile.Terrain.Forest, forestTile.getTerrain());
	// assertEquals(Tile.Terrain.Desert, desertTile.getTerrain());
	// assertEquals(Tile.Terrain.Grassland, grasslandTile.getTerrain());
	// }
	//
	// @Test
	// public void testLoadProduction() {
	// Tile noProductionTile = board.map.get(0).getTiles()[0][0];
	// Tile oneProductionTile = board.map.get(0).getTiles()[1][0];
	// Tile twoProductionTile = board.map.get(0).getTiles()[2][0];
	//
	// assertEquals(0, noProductionTile.getProduction());
	// assertEquals(1, oneProductionTile.getProduction());
	// assertEquals(2, twoProductionTile.getProduction());
	// }
	//
	// @Test
	// public void testLoadTrade() {
	// Tile noTradeTile = board.map.get(0).getTiles()[0][0];
	// Tile oneTradeTile = board.map.get(0).getTiles()[1][0];
	// Tile twoTradeTile = board.map.get(0).getTiles()[2][0];
	//
	// assertEquals(0, noTradeTile.getTrade());
	// assertEquals(1, oneTradeTile.getTrade());
	// assertEquals(2, twoTradeTile.getTrade());
	// }
	//
	// @Test
	// public void testLoadCulture() {
	// Tile noCultureTile = board.map.get(0).getTiles()[0][0];
	// Tile oneCultureTile = board.map.get(0).getTiles()[1][0];
	// Tile twoCultureTile = board.map.get(0).getTiles()[2][0];
	//
	// assertEquals(0, noCultureTile.getCulture());
	// assertEquals(1, oneCultureTile.getCulture());
	// assertEquals(2, twoCultureTile.getCulture());
	// }
	//
	// @Test
	// public void testLoadCoins() {
	// Tile noCoinTile = board.map.get(0).getTiles()[0][0];
	// Tile oneCoinTile = board.map.get(0).getTiles()[1][0];
	// Tile twoCoinTile = board.map.get(0).getTiles()[2][0];
	//
	// assertEquals(0, noCoinTile.getCoins());
	// assertEquals(1, oneCoinTile.getCoins());
	// assertEquals(2, twoCoinTile.getCoins());
	// }
	//
	// @Test
	// public void testLoadHutsandVillages() {
	// Tile hutTile = board.map.get(0).getTiles()[0][0];
	// Tile villageTile = board.map.get(0).getTiles()[1][0];
	// Tile blankTile = board.map.get(0).getTiles()[2][0];
	//
	// assertEquals(Settler.class, hutTile.getFigure().get(0).getClass());
	// assertEquals(Army.class, villageTile.getFigure().get(0).getClass());
	// assertEquals(new ArrayList<Figure>(), blankTile.getFigure());
	// }

	@Test
	public void testPhaseChanges() {
		assertEquals(board.getPlayer1(), board.getCurrentPlayer());
		board.endPhase();
		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
		assertEquals("Start of Turn", board.getCurrentPhase());
		board.endPhase();
		assertEquals(board.getPlayer1(), board.getCurrentPlayer());
		assertEquals("Trade", board.getCurrentPhase());
		for (int i = 0; i < 7; i++) {
			board.endPhase();
		}
		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
		assertEquals("Research", board.getCurrentPhase());
		board.endPhase();
		assertEquals(board.getPlayer2(), board.getCurrentPlayer());
		assertEquals("Start of Turn", board.getCurrentPhase());
	}

	@Test
	public void findFirstPanel() {
		Panel first = board.findPanel(0, 235);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(1, 235);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(235, 235);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(438, 235);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(439, 235);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(235, 0);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(235, 1);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(235, 438);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(235, 439);
		assertEquals(0, Board.map.indexOf(first));
		first = board.findPanel(439, 439);
		assertEquals(0, Board.map.indexOf(first));
	}

	@Test
	public void findAllPanels() {
		Panel first = board.findPanel(235, 235);
		Panel second = board.findPanel(650, 235);
		Panel third = board.findPanel(1000, 235);
		Panel fourth = board.findPanel(1500, 235);
		Panel fifth = board.findPanel(235, 650);
		Panel sixth = board.findPanel(650, 650);
		Panel seventh = board.findPanel(1000, 650);
		Panel eigth = board.findPanel(1500, 650);
		Panel nowhere = board.findPanel(-1, -1);

		assertEquals(0, Board.map.indexOf(first));
		assertEquals(1, Board.map.indexOf(second));
		assertEquals(2, Board.map.indexOf(third));
		assertEquals(3, Board.map.indexOf(fourth));
		assertEquals(4, Board.map.indexOf(fifth));
		assertEquals(5, Board.map.indexOf(sixth));
		assertEquals(6, Board.map.indexOf(seventh));
		assertEquals(7, Board.map.indexOf(eigth));
		assertEquals(0, Board.map.indexOf(nowhere));
	}

	@Test
	public void findTileFromClick() {
		Tile tile1 = board.findTile(Board.map.get(0), 235, 235);
		assertEquals(tile1, Board.map.get(0).getTiles()[2][2]);

		Tile tile2 = board.findTile(Board.map.get(4), 235, 675);
		assertEquals(tile2, Board.map.get(4).getTiles()[2][2]);

		Tile target = board.findTile(Board.map.get(0), 55, 55);
		assertEquals(target, Board.map.get(0).getTiles()[0][0]);

		target = board.findTile(Board.map.get(0), 120, 55);
		assertEquals(target, Board.map.get(0).getTiles()[1][0]);

		target = board.findTile(Board.map.get(0), 240, 55);
		assertEquals(target, Board.map.get(0).getTiles()[2][0]);

		target = board.findTile(Board.map.get(0), 340, 55);
		assertEquals(target, Board.map.get(0).getTiles()[3][0]);

		target = board.findTile(Board.map.get(0), 55, 120);
		assertEquals(target, Board.map.get(0).getTiles()[0][1]);

		target = board.findTile(Board.map.get(0), 55, 240);
		assertEquals(target, Board.map.get(0).getTiles()[0][2]);

		target = board.findTile(Board.map.get(0), 55, 340);
		assertEquals(target, Board.map.get(0).getTiles()[0][3]);
	}

	@Test
	public void testGetFirstPlayer() {
		Board target = new Board("America", "China");
		assertNotNull(target.getFirstPlayer());
	}

	@Test
	public void testGetPhase() {
		Board target = new Board("America", "China");
		assertEquals(1, target.getPhase());
	}

	@Test
	public void testCheckUnexploredPanel() {
		Board target = new Board("America", "China");

		assertTrue(Board.map.get(0).getIsExplored());
		assertFalse(Board.map.get(1).getIsExplored());
		assertFalse(Board.map.get(2).getIsExplored());
		assertFalse(Board.map.get(3).getIsExplored());
		assertFalse(Board.map.get(4).getIsExplored());
		assertFalse(Board.map.get(5).getIsExplored());
		assertFalse(Board.map.get(6).getIsExplored());
		assertTrue(Board.map.get(7).getIsExplored());

		target.checkUnexploredPanel(500, 300);
		assertTrue(Board.map.get(1).getIsExplored());
		assertFalse(Board.map.get(2).getIsExplored());

		target.checkUnexploredPanel(500, 300);
		assertTrue(Board.map.get(1).getIsExplored());

		target.checkUnexploredPanel(1000, 300);
		assertTrue(Board.map.get(2).getIsExplored());

		target.checkUnexploredPanel(1000, 300);
		assertTrue(Board.map.get(2).getIsExplored());

		target.checkUnexploredPanel(1700, 300);
		assertTrue(Board.map.get(3).getIsExplored());

		target.checkUnexploredPanel(1700, 300);
		assertTrue(Board.map.get(3).getIsExplored());

		target.checkUnexploredPanel(1800, 300);

		target.checkUnexploredPanel(300, 500);
		assertTrue(Board.map.get(4).getIsExplored());

		target.checkUnexploredPanel(300, 500);
		assertTrue(Board.map.get(4).getIsExplored());

		target.checkUnexploredPanel(500, 500);
		assertTrue(Board.map.get(5).getIsExplored());

		target.checkUnexploredPanel(500, 500);
		assertTrue(Board.map.get(5).getIsExplored());

		target.checkUnexploredPanel(1000, 500);
		assertTrue(Board.map.get(6).getIsExplored());

		target.checkUnexploredPanel(1000, 500);
		assertTrue(Board.map.get(6).getIsExplored());

		target.checkUnexploredPanel(1400, 880);
		assertTrue(Board.map.get(6).getIsExplored());

		target.checkUnexploredPanel(1400, 881);

	}

	@Test
	public void testCheckSpaceForEnemyFigures() {
		Board target = new Board("America", "China");
		assertFalse(target.checkSpaceForEnemyFigures(Board.map.get(0)
				.getTiles()[0][0]));
		assertTrue(target
				.checkSpaceForEnemyFigures(Board.map.get(7).getTiles()[3][3]));
		target.changePlayerTurn();
		assertTrue(target
				.checkSpaceForEnemyFigures(Board.map.get(0).getTiles()[0][0]));
		assertFalse(target.checkSpaceForEnemyFigures(Board.map.get(7)
				.getTiles()[3][3]));
	}

	@Test
	public void testAddFigure() {
		Board target = new Board("America", "China");
		Board.currentFigure = new Settler(target.getCurrentPlayer(), new Tile());
		Board.currentClick = new Point(50, 50);
		Board.currentCity = Board.map.get(0).getTiles()[1][1].getCity();
		assertFalse(target.addFigure(Board.map.get(7).getTiles()[3][3]));
		assertTrue(target.addFigure(Board.map.get(0).getTiles()[0][0]));
		target.changePlayerTurn();
		Board.currentFigure = new Army(target.getCurrentPlayer(), new Tile());
		Board.currentClick = new Point(50, 50);
		Board.currentCity = Board.map.get(7).getTiles()[2][2].getCity();
		assertFalse(target.addFigure(Board.map.get(7).getTiles()[3][3]));
		assertTrue(target.addFigure(Board.map.get(7).getTiles()[3][2]));
	}

}
