import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JRadioButtonMenuItem;

import org.hamcrest.core.IsNull;
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
		assertNotNull(board.getFirstPlayer());
	}

	@Test
	public void testGetPhase() {
		assertEquals(1, board.getPhase());
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

		board.checkUnexploredPanel(500, 300);
		assertTrue(Board.map.get(1).getIsExplored());
		assertFalse(Board.map.get(2).getIsExplored());

		board.checkUnexploredPanel(500, 300);
		assertTrue(Board.map.get(1).getIsExplored());

		board.checkUnexploredPanel(1000, 300);
		assertTrue(Board.map.get(2).getIsExplored());

		board.checkUnexploredPanel(1000, 300);
		assertTrue(Board.map.get(2).getIsExplored());

		board.checkUnexploredPanel(1700, 300);
		assertTrue(Board.map.get(3).getIsExplored());

		board.checkUnexploredPanel(1700, 300);
		assertTrue(Board.map.get(3).getIsExplored());

		board.checkUnexploredPanel(1800, 300);

		board.checkUnexploredPanel(300, 500);
		assertTrue(Board.map.get(4).getIsExplored());

		board.checkUnexploredPanel(300, 500);
		assertTrue(Board.map.get(4).getIsExplored());

		board.checkUnexploredPanel(500, 500);
		assertTrue(Board.map.get(5).getIsExplored());

		board.checkUnexploredPanel(500, 500);
		assertTrue(Board.map.get(5).getIsExplored());

		board.checkUnexploredPanel(1000, 500);
		assertTrue(Board.map.get(6).getIsExplored());

		board.checkUnexploredPanel(1000, 500);
		assertTrue(Board.map.get(6).getIsExplored());

		board.checkUnexploredPanel(1400, 880);
		assertTrue(Board.map.get(6).getIsExplored());

		board.checkUnexploredPanel(1400, 881);

	}

	@Test
	public void testCheckTiles() {

		// Test middle tile, panel 0
		Tile testTile = Board.map.get(0).getTiles()[1][1];
		board.setCurrentMovementFigure();
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top left corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top row middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][1];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test rightmost column middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[1][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom row middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][1];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom left corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test leftmost column middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[1][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top left corner tile, panel 5
		testTile = Board.map.get(5).getTiles()[0][0];
		board.getValidTiles(Board.map.get(5), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom left corner tile, panel 1
		testTile = Board.map.get(1).getTiles()[3][0];
		board.getValidTiles(Board.map.get(1), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 4
		testTile = Board.map.get(4).getTiles()[0][3];
		board.getValidTiles(Board.map.get(4), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 4
		testTile = Board.map.get(4).getTiles()[3][3];
		board.getValidTiles(Board.map.get(4), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 3
		testTile = Board.map.get(3).getTiles()[0][3];
		board.getValidTiles(Board.map.get(3), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 7
		testTile = Board.map.get(7).getTiles()[3][3];
		board.getValidTiles(Board.map.get(7), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 7
		testTile = Board.map.get(7).getTiles()[0][3];
		board.getValidTiles(Board.map.get(7), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		board.setCurrentMovementFigureMoves(); // Now they can't land on water.
		// Test middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[1][1];
		board.setCurrentMovementFigure();
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top left corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top row middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][1];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[0][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test rightmost column middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[1][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][3];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom row middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][1];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom left corner tile, panel 0
		testTile = Board.map.get(0).getTiles()[3][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(1, board.getValidTileList().size());
		board.resetValidTileList();

		// Test leftmost column middle tile, panel 0
		testTile = Board.map.get(0).getTiles()[1][0];
		board.getValidTiles(Board.map.get(0), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top left corner tile, panel 5
		testTile = Board.map.get(5).getTiles()[0][0];
		board.getValidTiles(Board.map.get(5), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom left corner tile, panel 1
		testTile = Board.map.get(1).getTiles()[3][0];
		board.getValidTiles(Board.map.get(1), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 4
		testTile = Board.map.get(4).getTiles()[0][3];
		board.getValidTiles(Board.map.get(4), testTile);
		assertEquals(1, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 4
		testTile = Board.map.get(4).getTiles()[3][3];
		board.getValidTiles(Board.map.get(4), testTile);
		assertEquals(3, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 3
		testTile = Board.map.get(3).getTiles()[0][3];
		board.getValidTiles(Board.map.get(3), testTile);
		assertEquals(4, board.getValidTileList().size());
		board.resetValidTileList();

		// Test bottom right corner tile, panel 7
		testTile = Board.map.get(7).getTiles()[3][3];
		board.getValidTiles(Board.map.get(7), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

		// Test top right corner tile, panel 7
		testTile = Board.map.get(7).getTiles()[0][3];
		board.getValidTiles(Board.map.get(7), testTile);
		assertEquals(2, board.getValidTileList().size());
		board.resetValidTileList();

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
		String figure = "Settler";// (target.getCurrentPlayer(), new Tile());
		Board.currentClick = new Point(50, 50);
		City city = Board.map.get(0).getTiles()[1][1].getCity();
		assertFalse(target.addFigure(Board.map.get(7).getTiles()[3][3], city,
				figure));
		assertTrue(target.addFigure(Board.map.get(0).getTiles()[0][0], city,
				figure));
		target.changePlayerTurn();
		figure = "Army";// new Army(target.getCurrentPlayer(), new Tile());
		Board.currentClick = new Point(50, 50);
		city = Board.map.get(7).getTiles()[2][2].getCity();
		assertFalse(target.addFigure(Board.map.get(7).getTiles()[3][3], city,
				figure));
		assertTrue(target.addFigure(Board.map.get(7).getTiles()[3][2], city,
				figure));
	}

	@Test
	public void testmovement() {
		Board target = new Board("America", "China");
		target.currentMovementFigure = Board.map.get(0).getTiles()[0][0]
				.getFigures().get(0);
		target.getValidTiles(Board.map.get(0),
				Board.map.get(0).getTiles()[0][0]);
		Tile tile = Board.map.get(0).getTiles()[0][1];
		target.movement(tile, Board.map.get(0));
		assertEquals(0, Board.map.get(0).getTiles()[0][0].getFigures().size());
		assertEquals(1, Board.map.get(0).getTiles()[0][1].getFigures().size());
		target.currentMovementFigure = Board.map.get(0).getTiles()[0][1]
				.getFigures().get(0);
		target.movement(Board.map.get(0).getTiles()[0][1], Board.map.get(0));
		assertEquals(0, Board.map.get(0).getTiles()[0][2].getFigures().size());
		assertEquals(1, Board.map.get(0).getTiles()[0][1].getFigures().size());
	}

	@Test
	public void handleBuild() {
		Board target = new Board("America", "China");
		Board.currentCity = Board.map.get(0).getTiles()[1][1].getCity();
		Board.currentCity.calcProduction();
		Board.currentTile = Board.map.get(0).getTiles()[0][1];
		target.items = new JRadioButtonMenuItem[3];
		Figure figure = null;

		target.items[0] = new JRadioButtonMenuItem("Settler");
		target.items[1] = new JRadioButtonMenuItem("Army");
		target.items[2] = new JRadioButtonMenuItem("Cancel");
		Board.currentFigure = null;
		assertNull(Board.currentFigure);
		target.handleBuild(0, Board.currentCity);
		assertNull(Board.currentFigure);
		assertEquals(0, Board.map.get(0).getTiles()[0][1].getFigures().size());
		target.handleBuild(1, Board.currentCity);
		assertNotNull(Board.currentFigure);

	}

	@Test
	public void testAddMarkerWonder() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "G", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String marker = "Stonehenge";// new Wonder("Stonehenge");
		assertTrue(target.addMarker(tile, city, marker));
		// assertEquals(marker.getLocation(), tile);
		// assertEquals(tile.getMarker(), marker);
	}

	@Test
	public void testMakeBuilding() {
		Board target = new Board("America", "China");
		Board.currentCity = Board.map.get(0).getTiles()[1][1].getCity();
		Board.currentCity.calcProduction();

		target.makeBuilding("Workshop", Board.currentCity);
		assertFalse(Board.currentMarker);

		target.makeBuilding("Granary", Board.currentCity);
		assertTrue(Board.currentMarker);
		// assertEquals("Granary", target.currentChoice);

	}

	@Test
	public void testAddMarkerInvalidWonder() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "W", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String marker = "Stonehenge";// new Wonder("Stonehenge");
		assertFalse(target.addMarker(tile, city, marker));
	}

	@Test
	public void testAddMarkerGoodBuilding() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "G", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String building = "Library";// new Building("Library");
		assertTrue(target.addMarker(tile, city, building));
		// assertEquals(building.getLocation(), tile);
		assertEquals(tile.getMarker().name, building);
	}

	@Test
	public void testAddMarkerInvalidTerrainBuilding() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "W", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String building = "Library";// new Building("Library");
		assertFalse(target.addMarker(tile, city, building));
		assertNull(tile.getMarker());
	}

	@Test
	public void testAddMarkerInvalidTerrainNotWater() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "F", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String building = "Library";// new Building("Library");
		assertFalse(target.addMarker(tile, city, building));
		assertNull(tile.getMarker());
	}

	@Test
	public void testAddMarkerInvalidTerrainNotWaterMarket() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "W", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String building = "Market";// new Building("Market");
		assertFalse(target.addMarker(tile, city, building));
		assertNull(tile.getMarker());
	}

	@Test
	public void testAddMarkerInvalidNotOutskirts() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "W", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		city.setOutskirts(outskirts);
		String building = "Market";// new Building("Market");
		assertFalse(target.addMarker(tile, city, building));
		assertNull(tile.getMarker());
	}

	@Test
	public void testAddMarkerGoodNotWaterBuilding() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "G", 0, 0, "NONE", 0, "NONE", 0);
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		city.setOutskirts(outskirts);
		String building = "Market";// new Building("Market");
		assertTrue(target.addMarker(tile, city, building));
		// assertEquals(building.getLocation(), tile);
		assertEquals(tile.getMarker().name, building);
	}

	@Test
	public void testAddMarkerInvalidHasStar() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "W", 0, 0, "NONE", 0, "NONE", 0);
		Tile starTile = new Tile(0, 0, "M", 0, 0, "NONE", 0, "NONE", 0);
		starTile.setMarker(new Building("Temple"));
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		outskirts.add(starTile);
		city.setOutskirts(outskirts);
		String building = "Barracks";// new Building("Barracks");
		assertFalse(target.addMarker(tile, city, building));
		assertNull(tile.getMarker());
	}

	@Test
	public void testAddMarkerGoodHasStarBuilding() {
		Board target = new Board("America", "China");
		City city = new City(new Tile());
		Tile tile = new Tile(0, 0, "G", 0, 0, "NONE", 0, "NONE", 0);
		Tile starTile = new Tile(0, 0, "M", 0, 0, "NONE", 0, "NONE", 0);
		starTile.setMarker(new Building("Temple"));
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		outskirts.add(tile);
		outskirts.add(starTile);
		city.setOutskirts(outskirts);
		String building = "Granary";// new Building("Granary");
		assertTrue(target.addMarker(tile, city, building));
		// assertEquals(building.getLocation(), tile);
		assertEquals(tile.getMarker().name, building);
	}

	@Test
	public void testCityManagementNewSettler() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setTerrain("G");
		tile.setScreenLocation(0, 0);
		City city = new City(new Tile());
		ArrayList<Tile> outs = new ArrayList<Tile>();
		outs.add(tile);
		city.setOutskirts(outs);
		// Figure figure = new Settler(board.getCurrentPlayer(), new Tile());
		board.currentChoice = "Settler";
		Boolean figure = true;
		Boolean marker = false;

		City newcity = board.cityManagement(tile, city, figure, marker);
		assertFalse(Board.currentFigure);
		assertNull(newcity);
		assertFalse(city.getHasAction());
		assertEquals(1, tile.getFigures().size());
	}

	@Test
	public void testCityManagementNewArmy() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setTerrain("G");
		tile.setScreenLocation(0, 0);
		City city = new City(new Tile());
		ArrayList<Tile> outs = new ArrayList<Tile>();
		outs.add(tile);
		city.setOutskirts(outs);
		// Figure figure = new Army(board.getCurrentPlayer(), new Tile());
		board.currentChoice = "Army";
		Boolean figure = true;
		Boolean marker = false;

		City newcity = board.cityManagement(tile, city, figure, marker);
		assertFalse(Board.currentFigure);
		assertNull(newcity);
		assertFalse(city.getHasAction());
		assertEquals(1, tile.getFigures().size());
	}

	@Test
	public void testCityManagementNewBuilding() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setTerrain("G");
		City city = new City(new Tile());
		ArrayList<Tile> outs = new ArrayList<Tile>();
		outs.add(tile);
		city.setOutskirts(outs);
		Boolean figure = false;
		// Marker marker = new Building("Market");
		Boolean marker = true;
		board.currentChoice = "Market";

		City newcity = board.cityManagement(tile, city, figure, marker);
		assertFalse(Board.currentMarker);
		assertNull(newcity);
		assertFalse(city.getHasAction());
		assertEquals("Market", tile.getMarker().name);
	}

	@Test
	public void testCityManagementBadResource() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setResource(Tile.Resource.None);
		City city = new City(new Tile());
		ArrayList<Tile> outs = new ArrayList<Tile>();
		outs.add(tile);
		city.setOutskirts(outs);
		Board.setGoingForResource(true);

		City newcity = board.goForResource(tile, city);
		assertTrue(Board.isGoingForResource());
		assertEquals(city, newcity);
		assertTrue(city.getHasAction());
	}

	@Test
	public void testCityManagementGoodResource() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setResource(Tile.Resource.Iron);
		City city = new City(new Tile());
		ArrayList<Tile> outs = new ArrayList<Tile>();
		outs.add(tile);
		city.setOutskirts(outs);
		Board.setGoingForResource(true);
		City newcity = board.goForResource(tile, city);
		assertFalse(Board.isGoingForResource());
		assertNull(newcity);
		assertFalse(city.getHasAction());
		assertTrue(board.getCurrentPlayer().resources
				.contains(Tile.Resource.Iron));
	}

	@Test
	public void testStartTurnTryToBuildCityGood() {
		Board board = new Board("America", "China");
		Tile tile = new Tile();
		tile.setScreenLocation(0, 0);
		City city = new City(new Tile());
		city.isValid = true;
		Figure newCity = new Settler(board.getCurrentPlayer(), tile);
		board.getCurrentPlayer().figures.add(newCity);
		board.tryToBuildCity(tile, newCity, city);
		city.setScreenLocation(tile.getScreenLocation());
		assertEquals(tile.getCity(), city);
		assertTrue(board.getCurrentPlayer().cities.contains(city));
		assertFalse(board.getCurrentPlayer().figures.contains(newCity));
	}

}
