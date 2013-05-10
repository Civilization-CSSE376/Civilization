package Civ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import java.util.Hashtable;

import javax.swing.JPanel;

import Civ.Tile.Resource;

@SuppressWarnings("serial")
public class Board extends JPanel {

	final String START_OF_TURN = "Start of Turn";
	final String TRADE = "Trade";
	final String CITY_MANAGEMENT = "City Management";
	final String MOVEMENT = "Movement";
	final String RESEARCH = "Research";
	private String currentPhase;
	public String currentChoice;

	private File file = new File("src/1stEight.txt");

	private static Player player1;
	private static Player player2;
	Figure currentMovementFigure = null;

	public static ArrayList<Panel> map;
	public static ArrayList<Player> players = new ArrayList<Player>();
	// private Market market;
	private static Player firstPlayer;
	private static Player currentPlayer;

	private int phase;
	public JFrame p;

	private String player1Civilization;
	private String player2Civilization;

	private ArrayList<Tile> validTiles = new ArrayList<Tile>();
	private ResourceBundle messages;

	public Board(String p1Civ, String p2Civ, ResourceBundle messages) {
		this.messages = messages;
		this.player1Civilization = p1Civ;
		this.player2Civilization = p2Civ;

		players = new ArrayList<Player>();

		map = new ArrayList<Panel>();
		readFromFile();
		setTileLocations();

		setPanelNeighbors();

		this.player1 = new Player();
		this.player2 = new Player();

		players.add(this.player1);
		players.add(this.player2);

		Settler settler1 = new Settler(player1, map.get(0).getTiles()[0][0]);
		Settler settler2 = new Settler(player2, map.get(7).getTiles()[3][3]);
		Army army1 = new Army(player1, map.get(0).getTiles()[1][0]);
		Army army2 = new Army(player2, map.get(0).getTiles()[0][1]);

		settler1.resetMoves(player1.getSpeed());
		settler2.resetMoves(player2.getSpeed());
		army1.resetMoves(player1.getSpeed());
		army2.resetMoves(player2.getSpeed());

		settler1.setLocation(10, 10);
		settler2.setScreenLocation(map.get(7).getTiles()[3][3]
				.getScreenLocation());
		army1.setScreenLocation((map.get(0).getTiles()[1][0]
				.getScreenLocation()));
		army2.setScreenLocation((map.get(0).getTiles()[0][1]
				.getScreenLocation()));

		this.player1.figures.add(settler1);
		this.player2.figures.add(settler2);
		this.player1.figures.add(army1);
		this.player2.figures.add(army2);

		map.get(0).getTiles()[0][0].getFigures().add(settler1);
		map.get(7).getTiles()[3][3].getFigures().add(settler2);
		map.get(0).getTiles()[1][0].getFigures().add(army1);
		map.get(0).getTiles()[0][1].getFigures().add(army2);

		City city1 = new City(map.get(0).getTiles()[1][1], this.player1);
		City city2 = new City(map.get(7).getTiles()[2][2], this.player2);

		city1.setLocation(130, 130);
		city2.setLocation((440 * 4) - 115, 750);

		this.player1.cities.add(city1);
		this.player2.cities.add(city2);

		map.get(0).getTiles()[1][1].setCity(city1);
		map.get(7).getTiles()[2][2].setCity(city2);

		this.currentPhase = START_OF_TURN;

		this.firstPlayer = player1;
		this.currentPlayer = player1;
		this.phase = 1;

		// TODO make this more efficient. quickly copied/pasted initially
		for (City c : this.player1.cities) {
			this.player1.trade += c.calcTrade();
		}
		for (City c : this.player2.cities) {
			this.player2.trade += c.calcTrade();
		}

		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);

	}

	private void setPanelNeighbors() {
		// panel 0
		HashMap<String, Panel> neighbors = new HashMap<String, Panel>();
		neighbors.put("North", null);
		neighbors.put("South", Board.map.get(4));
		neighbors.put("East", Board.map.get(1));
		neighbors.put("West", null);
		Board.map.get(0).setNeighbors(neighbors);

		// panel 1
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", null);
		neighbors.put("South", Board.map.get(5));
		neighbors.put("East", Board.map.get(2));
		neighbors.put("West", Board.map.get(0));
		Board.map.get(1).setNeighbors(neighbors);

		// panel 2
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", null);
		neighbors.put("South", Board.map.get(6));
		neighbors.put("East", Board.map.get(3));
		neighbors.put("West", Board.map.get(1));
		Board.map.get(2).setNeighbors(neighbors);

		// panel 3
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", null);
		neighbors.put("South", Board.map.get(7));
		neighbors.put("East", null);
		neighbors.put("West", Board.map.get(2));
		Board.map.get(3).setNeighbors(neighbors);

		// panel 4
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", Board.map.get(0));
		neighbors.put("South", null);
		neighbors.put("East", Board.map.get(5));
		neighbors.put("West", null);
		Board.map.get(4).setNeighbors(neighbors);

		// panel 5
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", Board.map.get(1));
		neighbors.put("South", null);
		neighbors.put("East", Board.map.get(6));
		neighbors.put("West", Board.map.get(4));
		Board.map.get(5).setNeighbors(neighbors);

		// panel 6
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", Board.map.get(2));
		neighbors.put("South", null);
		neighbors.put("East", Board.map.get(7));
		neighbors.put("West", Board.map.get(5));
		Board.map.get(6).setNeighbors(neighbors);

		// panel 7
		neighbors = new HashMap<String, Panel>();
		neighbors.put("North", Board.map.get(3));
		neighbors.put("South", null);
		neighbors.put("East", null);
		neighbors.put("West", Board.map.get(6));
		Board.map.get(5).setNeighbors(neighbors);

	}

	public void checkUnexploredPanel(int x, int y) {

		if (x < 880 && y < 440) { // Panel 2
			if (!map.get(1).getIsExplored())
				map.get(1).changeIsExplored();
		} else if (x < 1320 && y < 440) { // Panel 3
			if (!map.get(2).getIsExplored())
				map.get(2).changeIsExplored();
		} else if (x < 1761 && y < 440) { // Panel 4
											// (top
											// right)
			if (!map.get(3).getIsExplored())
				map.get(3).changeIsExplored();
		} else if (x < 440) { // Panel 5
								// (bottom left)
			if (!map.get(4).getIsExplored())
				map.get(4).changeIsExplored();
		} else if (x < 880) { // Panel 6
			if (!map.get(5).getIsExplored())
				map.get(5).changeIsExplored();
		} else if (x < 1320) { // Panel 7
			if (!map.get(6).getIsExplored())
				map.get(6).changeIsExplored();
		} else
			System.out.println("Error.");
	}

	public void checkUnexploredPanelNew(int x, int y) {
		Panel panel = findPanel(x, y);
		if (!panel.getIsExplored()) {
			panel.changeIsExplored();
		}
	}

	public void makeMovementWindow(final ArrayList<Figure> figures) {
		if (figures.get(0).getUsedThisTurn()
				|| figures.get(0).getNumberOfMoves() == 0) {
			return;
		}
		int answer = JOptionPane.showConfirmDialog(
				null,
				messages.getString("askToMoveUnit")
						+ figures.get(0).getNumberOfMoves()
						+ messages.getString("numMovesLeft"), "Movement",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			currentMovementFigure = figures.get(0);
			return;
		} else {
			currentMovementFigure = null;
			return;
		}
	}

	public Boolean makeNewCityWindow() {
		int answer = JOptionPane.showConfirmDialog(null,
				messages.getString("askToCreateCity"), "Create New City",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	public void startOfTurn(Tile tile) {
		Figure newCity = null;
		for (Figure f : tile.getFigures()) {
			if (f instanceof Settler && currentPlayer.figures.contains(f)) {
				newCity = f;
				break;
			}
		}
		if (newCity == null) {
			return;
		}
		if (makeNewCityWindow()) {
			City city = new City(tile, currentPlayer);
			if (newCity.tryToBuildCity(tile, currentPlayer, city))
				repaint();
		}
		return;
	}

	JRadioButtonMenuItem items[];
	static Tile currentTile = null;
	public static Point currentClick = null;
	public static Boolean currentFigure = false;
	public static String currentMarker = null;
	static City currentCity = null;
	private static boolean goingForResource = false;

	public City cityManagement(Tile tile, City city, Boolean figure,
			String marker) {
		if (tile.getCity() != null && tile.getCity().getHasAction()
				&& currentPlayer.cities.contains(tile.getCity())) {
			currentFigure = false;
			city = tile.getCity();
			String[] choices = { messages.getString("buildSomethingOption"),
					messages.getString("collectResourceOption"),
					messages.getString("devoteArtsOption") };
			makeChoice(
					choices,
					new InitialHandler(),
					new Point((int) tile.getScreenLocation().x, (int) tile
							.getScreenLocation().y));
		} else {
			if (figure && !checkSpaceForEnemyFigures(tile)) {
				if (addFigure(tile, city, currentChoice)) {
					currentFigure = false;
					city.setHasAction(false);
					city = null;
				}
			} else if (marker != null && !checkSpaceForEnemyFigures(tile)) {
				if (addMarker(tile, city, currentMarker, currentChoice)) {
					currentMarker = null;
					city.setHasAction(false);
					city = null;
				}
			} else if (isGoingForResource()) {
				city = goForResource(tile, city);
				if (city == null) {
					JOptionPane.showConfirmDialog(null,
							messages.getString("resourceObtained")
									+ tile.getResource().toString(),
							"Collect Resource", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(null,
							messages.getString("invalidResourceLocation"),
							"Collect Resource", JOptionPane.PLAIN_MESSAGE);
				}

			}
		}
		return city;
	}

	public City goForResource(Tile tile, City city) {
		if ((tile.getResource() != null && !tile.getResource().toString()
				.equals("None"))
				&& city.getOutskirts().contains(tile)) {
			currentPlayer.resources.add(tile.getResource());
			// check that there is enough of that resource left
			city.setHasAction(false);
			city = null;
			setGoingForResource(false);
		}
		return city;
	}

	public boolean addMarker(Tile tile, City city, String type,
			String markerString) {
		Marker marker = Marker.makeMarker(type, markerString, messages);
		if (marker.isValid(tile, city)) {
			if (city.getOutskirts().contains(tile)) {
				marker.setTileLocal(tile);
				marker.setScreenLocation(tile.getScreenLocation());
				tile.setMarker(marker);
				repaint();
				return true;
			}
		}
		return false;
	}

	public void makeChoice(String[] choices, ActionListener handler, Point point) {
		JPopupMenu menu = new JPopupMenu();
		ButtonGroup group = new ButtonGroup();
		items = new JRadioButtonMenuItem[choices.length + 1];
		for (int i = 0; i < choices.length; i++) {
			items[i] = new JRadioButtonMenuItem(choices[i]);
			menu.add(items[i]);
			group.add(items[i]);
			items[i].addActionListener(handler);
		}
		items[items.length - 1] = new JRadioButtonMenuItem("Cancel");
		menu.add(items[items.length - 1]);
		group.add(items[items.length - 1]);
		items[items.length - 1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				return;
			}
		});
		menu.show(this, point.x, point.y);
	}

	public void buildSomething() {
		String[] choices = { messages.getString("building"),
				messages.getString("settler"), messages.getString("army"),
				messages.getString("wonder"), messages.getString("units") };
		makeChoice(choices, new BuilderHandler(), currentClick);
	}

	private class BuildingHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					currentChoice = items[i].getText();
					makeBuilding(items[i].getText(), Board.currentCity);
					return;
				}
		}
	}

	public boolean checkSpaceForEnemyFigures(Tile tile) {
		Player enemy;
		if (currentPlayer.equals(player1))
			enemy = player2;
		else
			enemy = player1;

		for (Figure f : tile.getFigures()) {
			if (f.getOwner() == null || f.getOwner().equals(enemy))
				return true;
		}
		return false;
	}

	public void makeBuilding(String type, City city) {
		Building newBuilding = new Building(type, messages);
		if (city.getProduction() < newBuilding.getCost())
			Board.currentMarker = null;
		else
			Board.currentMarker = "Building";

	}

	private class BuilderHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					handleBuild(i, Board.currentCity);
					return;
				}
		}
	}

	private class InitialHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					if (items[i].getText().equals(
							messages.getString("buildSomethingOption"))) {
						buildSomething();
					} else if (items[i].getText().equals(
							messages.getString("collectResourceOption"))) {
						setGoingForResource(true);
					}
					repaint();
					return;
				}
		}
	}

	public void movement(Tile tile, Panel panel) {
		if (p != null) {
			if (currentFigure == null) {
				p = null;
				return;
			}
			Tile conqueredTile = currentMovementFigure.location;
			Figure fig;
			if (((Combat) p).successfulAttack()) {
				fig = conqueredTile.getFigures().get(0);
				if (player1.equals(currentPlayer)) {
					player2.figures.remove(fig);
				} else {
					player1.figures.remove(fig);
				}
			} else {
				fig = conqueredTile.getFigures().get(
						conqueredTile.getFigures().size() - 1);
				currentPlayer.figures.remove(fig);
			}
			conqueredTile.getFigures().remove(fig);
			p = null;
			repaint();
		} else if (currentMovementFigure == null
				|| currentMovementFigure.location.equals(tile)) {
			ArrayList<Figure> figures = tile.getFigures();
			if (!figures.isEmpty()) {
				Figure figure = figures.get(0);
				if (figure.getOwner() != null) {
					if (figure.getOwner().equals(Board.this.currentPlayer)
							&& !figure.getUsedThisTurn()) {
						makeMovementWindow(figures);
						getValidTiles(panel, tile);
					}
				}
			}
		} else {
			if (currentMovementFigure.getNumberOfMoves() > 0) {
				if (Board.this.validTiles.contains(tile)) {
					if (panel.getIsExplored()) {
						if (checkSpaceForEnemyFigures(tile)) {
							Figure enemy = tile.getFigures().get(0);
							Player enemyPlayer = enemy.getOwner();
							if (enemyPlayer == null) {
								enemyPlayer = new Player();
							}

							if (enemy instanceof Settler) {
								tile.getFigures().remove(0);
								enemyPlayer.figures.remove(enemy);
								if (enemy.getOwner() == null) {
									Resource resource = Resource.values()[new Random()
											.nextInt(4)];
									currentPlayer.resources.add(resource);
									JOptionPane
											.showConfirmDialog(
													null,
													messages.getString("resourceObtained")
															+ resource
																	.toString(),
													"Collect Resource",
													JOptionPane.PLAIN_MESSAGE);
								}
							} else {
								p = new Combat(currentPlayer, enemyPlayer, 0);
								p.setLocation(10, 10);
								this.setEnabled(false);
							}
							Tile oldTile = currentMovementFigure.location;
							oldTile.getFigures().remove(currentMovementFigure);
							Board.this.currentMovementFigure
									.setScreenLocation(tile.getScreenLocation());
							currentMovementFigure.setTileLocal(tile);
							tile.getFigures().add(currentMovementFigure);
							Board.this.validTiles.clear();
							currentMovementFigure.setUsedThisTurn(true);
							Board.this.repaint();
							return;

						}
						System.out.println("Tile valid! Moving figure.");
						Tile oldTile = currentMovementFigure.location;
						oldTile.getFigures().remove(currentMovementFigure);
						Board.this.currentMovementFigure.setScreenLocation(tile
								.getScreenLocation());
						currentMovementFigure.setTileLocal(tile);
						tile.getFigures().add(currentMovementFigure);

					} else {
						checkUnexploredPanelNew(Board.currentClick.x,
								Board.currentClick.y);
					}

					currentMovementFigure.decreaseMoves();

					currentMovementFigure = null;
					Board.this.validTiles.clear();
					Board.this.repaint();
				}
				// else
				// JOptionPane.showConfirmDialog(null,
				// "Invalid Tile",
				// "Movement", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public void getValidTiles(Panel panel, Tile tile) {
		int panelNumber = map.indexOf(panel);
		int x = tile.getxPos();
		int y = tile.getyPos();
		Tile tileToCheck;

		if (currentMovementFigure.getNumberOfMoves() == 1) { // Can't end on
																// water!
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i == x || j == y) {
						if (!(i == x && j == y)) {
							if (i != -1 && i != 4 && j != -1 && j != 4) {
								tileToCheck = map.get(panelNumber).getTiles()[i][j];
								// System.out.println(" " + i + " " + y + "  " +
								// tileToCheck.getTerrain().toString());
								if (!tileToCheck.getTerrain().toString()
										.equals("Water")) {
									Board.this.validTiles.add(tileToCheck);
									System.out.println("Adding tile... i = "
											+ i + " and j = " + j);
								}
							} else if (i == -1) {
								if (panelNumber > 0 && panelNumber != 4) {
									tileToCheck = map.get(panelNumber - 1)
											.getTiles()[3][j];
									if (!map.get(panelNumber - 1)
											.getIsExplored())
										Board.this.validTiles.add(tileToCheck);
									else {
										if (!tileToCheck.getTerrain()
												.toString().equals("Water")) {
											Board.this.validTiles
													.add(tileToCheck);
											System.out
													.println("Adding tile... i = "
															+ i
															+ " and j = "
															+ j);
										}
									}
								}
							} else if (i == 4) {
								if (panelNumber < 7 && panelNumber != 3) {
									tileToCheck = map.get(panelNumber + 1)
											.getTiles()[0][j];
									if (!map.get(panelNumber + 1)
											.getIsExplored())
										Board.this.validTiles.add(tileToCheck);
									else {
										if (!tileToCheck.getTerrain()
												.toString().equals("Water")) {
											Board.this.validTiles
													.add(tileToCheck);
											System.out
													.println("Adding tile... i = "
															+ i
															+ " and j = "
															+ j);
										}
									}
								}
							} else if (j == -1) {
								if (panelNumber > 3) {
									tileToCheck = map.get(panelNumber - 4)
											.getTiles()[i][3];
									if (!map.get(panelNumber - 4)
											.getIsExplored())
										Board.this.validTiles.add(tileToCheck);
									else {
										if (!tileToCheck.getTerrain()
												.toString().equals("Water")) {
											Board.this.validTiles
													.add(tileToCheck);
											System.out
													.println("Adding tile... i = "
															+ i
															+ " and j = "
															+ j);
										}
									}

								}
							} else {
								if (panelNumber < 4) {
									tileToCheck = map.get(panelNumber + 4)
											.getTiles()[i][0];
									if (!map.get(panelNumber + 4)
											.getIsExplored())
										Board.this.validTiles.add(tileToCheck);
									else {
										if (!tileToCheck.getTerrain()
												.toString().equals("Water")) {
											Board.this.validTiles
													.add(tileToCheck);
											System.out
													.println("Adding tile... i = "
															+ i
															+ " and j = "
															+ j);
										}
									}
								}
							}
						}
					}
				}

			}
		} else {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i == x || j == y) {
						if (!(i == x && j == y)) {

							if (i != -1 && i != 4 && j != -1 && j != 4) {
								Board.this.validTiles.add(map.get(panelNumber)
										.getTiles()[i][j]);
								System.out.println("Adding tile... i = " + i
										+ " and j = " + j);
							} else if (i == -1) {
								if (panelNumber > 0 && panelNumber != 4) {
									Board.this.validTiles.add(map.get(
											panelNumber - 1).getTiles()[3][j]);
									System.out.println("Adding tile... i = "
											+ i + " and j = " + j);
								}
							} else if (i == 4) {
								if (panelNumber < 7 && panelNumber != 3) {
									Board.this.validTiles.add(map.get(
											panelNumber + 1).getTiles()[0][j]);
									System.out.println("Adding tile... i = "
											+ i + " and j = " + j);
								}
							} else if (j == -1) {
								if (panelNumber > 3) {
									Board.this.validTiles.add(map.get(
											panelNumber - 4).getTiles()[i][3]);
									System.out.println("Adding tile... i = "
											+ i + " and j = " + j);
								}
							} else {
								if (panelNumber < 4) {
									Board.this.validTiles.add(map.get(
											panelNumber + 4).getTiles()[i][0]);
									System.out.println("Adding tile... i = "
											+ i + " and j = " + j);
								}
							}

						}
					}
				}
			}
		}

		System.out.println("Array size: " + Board.this.validTiles.size());

	}

	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Board.currentClick = new Point(x, y);
			// System.out.printf("\nMouse clicked at %d, %d\n", x, y);

			Panel panel = findPanel(x, y);
			Tile tile = findTile(panel, x, y);
			System.out.println("\nTile located at "
					+ tile.getScreenLocation().x + " , "
					+ tile.getScreenLocation().y);
			Board.currentTile = tile;

			if (Board.this.currentPhase.equals(START_OF_TURN)) {
				startOfTurn(tile);
			} else if (Board.this.currentPhase.equals(CITY_MANAGEMENT)) {
				currentCity = cityManagement(tile, currentCity, currentFigure,
						currentMarker);
			} else if (Board.this.currentPhase.equals(MOVEMENT)) {
				movement(tile, panel);
			} else if (Board.this.currentPhase.equals(TRADE)) {
				// TODO: ask if want to trade
			} else if (Board.this.currentPhase.equals(RESEARCH)) {
				research(); // TODO: techwindow
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Do nothing.

		}
	}

	public void research() {

	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public int getPhase() {
		return this.phase;
	}

	public String getCurrentPhase() {
		return this.currentPhase;
	}

	// for testing purposes
	public Board(Hashtable<String, Panel> map, ArrayList<Player> player) {
		Board.map = new ArrayList<Panel>();
		Board.map.add(map.get("topLeft"));
		Board.map.add(map.get("topRight"));
		Board.map.add(map.get("bottomLeft"));
		Board.map.add(map.get("bottomRight"));

		Board.players = player;
	}

	public Board(ArrayList<String> civilizations) {
		for (String civ : civilizations) {
			players.add(playerConfig(civ));
		}
	}

	// Move to unit class.
	public void setPlayerLocation(int x, int y) {

		this.player1.setLocation(x, y);
	}

	private Player playerConfig(String civ) {

		switch (civ) {
		case "Egypt":
			break;
		case "Russia":
			break;
		case "Rome":
			break;
		case "America":
			break;
		case "Germany":
			break;
		case "China":
			break;
		default:
			break;
		}

		return null;
	}

	static Panel findPanel(Tile location) { // move this somewhere else?
		for (Panel panel : Board.map) {
			for (int i = 0; i < panel.getTiles().length; i++) {
				{
					for (int j = 0; j < panel.getTiles()[0].length; j++) {
						if (panel.getTiles()[i][j].equals(location)) {
							return panel;
						}
					}
				}

			}
		}

		return null;
	}

	static Panel findPanel(int x, int y) {
		int index;
		if (x < 440 && y < 440) {
			index = 0;
		} else if (x < 880 && y < 440) { // Panel 2
			index = 1;
		} else if (x < 1320 && y < 440) { // Panel 3
			index = 2;
		} else if (y < 440) { // Panel 4
								// (top
								// right)
			index = 3;
		} else if (x < 440) { // Panel 5
								// (bottom left)

			index = 4;
		} else if (x < 880) { // Panel 6
			index = 5;
		} else if (x < 1320) { // Panel 7
			index = 6;
		} else {
			index = 7; // Panel 8
		}

		return map.get(index);
	}

	public Tile findTile(Panel p, int x, int y) {
		int mapIndex = map.indexOf(p);
		int relativeX = x - ((mapIndex % 4) * 440);
		int relativeY = y - ((int) (Math.floor(mapIndex / 4)) * 440);
		int tileX = 0;
		int tileY = 0;
		if (relativeX < 110)
			tileX = 0;
		else if (relativeX < 220)
			tileX = 1;
		else if (relativeX < 330)
			tileX = 2;
		else
			tileX = 3;

		if (relativeY < 110)
			tileY = 0;
		else if (relativeY < 220)
			tileY = 1;
		else if (relativeY < 330)
			tileY = 2;
		else
			tileY = 3;

		return p.getTiles()[tileX][tileY];
	}

	public void readFromFile() {

		readFromFile(new File("src/civPanelDetails/" + this.player1Civilization));
		for (int i = 0; i < 6; i++) {
			readFromFile(new File("src/panelDetails/Panel" + (i + 1)));
		}
		readFromFile(new File("src/civPanelDetails/" + this.player2Civilization));

		map.get(0).changeIsExplored(); // Player 1's initial location.
		map.get(7).changeIsExplored(); // Player 2's initial location.
	}

	public void readFromFile(File file) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			int index = 0;
			Panel panel = new Panel();
			Tile[][] tiles = new Tile[4][4];
			while ((text = reader.readLine()) != null) {
				if (text.startsWith(";")) {
					// new panel
				} else {
					String[] stringTiles;
					stringTiles = text.split("_");
					System.out.println(index + "   [" + (index % 4) + "] ["
							+ (int) (Math.floor(index / 4)) + "]");
					tiles[(index % 4)][(int) (Math.floor(index / 4))] = new Tile(
							(index % 4), (int) (Math.floor(index / 4)),
							stringTiles[0], Integer.parseInt(stringTiles[1]),
							Integer.parseInt(stringTiles[2]), stringTiles[3],
							Integer.parseInt(stringTiles[4]), stringTiles[5],
							Integer.parseInt(stringTiles[6]));

					index++;

					if (index == 16) {
						panel.setTiles(tiles);
						map.add(panel);
						panel = new Panel();
						index = 0;
						tiles = new Tile[4][4];
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void setTileLocations() {
		for (int i = 0; i < 8; i++) {
			Panel p = map.get(i);
			int basex = 0, basey = 0, x = 0, y = 0;

			if (i < 4) {
				basey = 0;
			} else {
				basey = 440;
			}
			basex = 440 * (i % 4);

			for (int yp = 0; yp < 4; yp++) {
				for (int xp = 0; xp < 4; xp++) {
					x = (xp * 110) + basex;
					y = (yp * 110) + basey;
					p.getTiles()[xp][yp].setScreenLocation(x, y);
				}
			}
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Rectangle2D.Double bottomSpace = new Rectangle2D.Double(0, 881, 1761,
				24);
		g2.setColor(Color.BLACK);
		g2.fill(bottomSpace);

		drawPlayer1Panel(g2);
		drawPanels(g2);
		drawPlayer2Panel(g2);

		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", g2.getFont().getStyle(), 18));
		g2.drawString(messages.getString("currentPhase") + getPhaseText(), 50,
				900);

		if (this.currentPlayer == this.player1) {
			g2.setColor(Color.RED);
			g2.drawString(messages.getString("player1Turn"), 500, 900);
		} else {
			g2.setColor(Color.ORANGE);
			g2.drawString(messages.getString("player2Turn"), 500, 900);
		}

		for (City cities : player1.cities) {
			cities.draw(g2, Color.red);
		}

		for (City cities : player2.cities) {
			cities.draw(g2, Color.orange);
		}

		for (Figure figure : player1.figures) {
			figure.draw(g2, Color.red);
		}

		for (Figure figure : player2.figures) {
			figure.draw(g2, Color.orange);
		}

		for (City c : player1.cities) {
			for (Tile t : c.getOutskirts()) {
				if (t.getMarker() != null) {
					t.getMarker().draw(g2, Color.red);
				}
			}
		}

		for (City c : player2.cities) {
			for (Tile t : c.getOutskirts()) {
				if (t.getMarker() != null)
					t.getMarker().draw(g2, Color.orange);
			}
		}
	}

	private String getPhaseText() {
		String phase = "";
		switch (this.currentPhase) {
		case START_OF_TURN:
			phase = messages.getString("startOfTurn");
			break;
		case TRADE:
			phase = messages.getString("trade2");
			break;
		case CITY_MANAGEMENT:
			phase = messages.getString("cityManagement");
			break;
		case MOVEMENT:
			phase = messages.getString("movement");
			break;
		case RESEARCH:
			phase = messages.getString("research");
			break;
		}
		return phase;
	}

	private void drawPanels(Graphics2D g2) {

		for (int i = 1; i < 7; i++) {
			if (map.get(i).getIsExplored()) {
				String filename = "src/panels/panel" + i + ".png";

				try {
					BufferedImage image = ImageIO.read(new File(filename));
					switch (i) {
					case 1:
						g2.drawImage(image, 440, 0, null);
						break;
					case 2:
						g2.drawImage(image, 880, 0, null);
						break;
					case 3:
						g2.drawImage(image, 1320, 0, null);
						break;
					case 4:
						g2.drawImage(image, 0, 440, null);
						break;
					case 5:
						g2.drawImage(image, 440, 440, null);
						break;
					case 6:
						g2.drawImage(image, 880, 440, null);
						break;
					}
				} catch (IOException e) {
					System.out.println("did not load image correctly");
					e.printStackTrace();
				}
			} else {
				String filename = "src/panels/UnexploredPanel.png";

				try {
					BufferedImage image = ImageIO.read(new File(filename));
					switch (i) {
					case 1:
						g2.drawImage(image, 440, 0, null);
						break;
					case 2:
						g2.drawImage(image, 880, 0, null);
						break;
					case 3:
						g2.drawImage(image, 1320, 0, null);
						break;
					case 4:
						g2.drawImage(image, 0, 440, null);
						break;
					case 5:
						g2.drawImage(image, 440, 440, null);
						break;
					case 6:
						g2.drawImage(image, 880, 440, null);
						break;
					}
				} catch (IOException e) {
					System.out.println("did not load image correctly");
					e.printStackTrace();
				}
			}
		}

	}

	private void drawPlayer1Panel(Graphics2D g2) {

		String filename = "src/panels/" + this.player1Civilization + ".png";

		try {
			BufferedImage image = ImageIO.read(new File(filename));
			g2.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			System.out.println("did not load image correctly");
			e.printStackTrace();
		}

	}

	private void drawPlayer2Panel(Graphics2D g2) {

		String filename = "src/panels/" + this.player2Civilization + ".png";

		try {
			BufferedImage image = ImageIO.read(new File(filename));
			g2.drawImage(image, 1320, 440, null);
		} catch (IOException e) {
			System.out.println("did not load image correctly");
			e.printStackTrace();
		}

	}

	public void changePlayerTurn() {
		if (this.currentPlayer == this.player1)
			this.currentPlayer = this.player2;
		else
			this.currentPlayer = this.player1;
	}

	public void endPhase() {

		if (this.currentPhase.equals(START_OF_TURN)) {
			if (this.currentPlayer == this.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
				this.currentPhase = TRADE;
				for (City c : Board.this.currentPlayer.cities) {
					Board.this.currentPlayer.trade += c.calcTrade();
				}
				System.out.println(currentPlayer.trade);
			}
		} else if (this.currentPhase.equals(TRADE)) {
			if (this.currentPlayer == this.firstPlayer) {
				this.changePlayerTurn();
				for (City c : Board.this.currentPlayer.cities) {
					Board.this.currentPlayer.trade += c.calcTrade();
				}
				System.out.println(currentPlayer.trade);
			} else {
				this.changePlayerTurn();
				this.currentPhase = CITY_MANAGEMENT;
				for (City c : this.currentPlayer.cities) {
					c.calcProduction();
				}
			}
		} else if (this.currentPhase.equals(CITY_MANAGEMENT)) {
			if (this.currentPlayer == this.firstPlayer) {
				for (City c : this.currentPlayer.cities) {
					c.setHasAction(true);
				}
				this.changePlayerTurn();
				currentCity = null;
				for (City c : this.currentPlayer.cities) {
					c.calcProduction();
				}
			} else {
				for (City c : this.currentPlayer.cities) {
					c.setHasAction(true);
				}
				this.changePlayerTurn();
				currentCity = null;
				this.currentPhase = MOVEMENT;
			}
		} else if (this.currentPhase.equals(MOVEMENT)) {
			for (Figure f : currentPlayer.figures) {
				f.setUsedThisTurn(false);
			}
			this.currentMovementFigure = null;
			if (this.currentPlayer == this.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
				this.currentPhase = RESEARCH;
			}

			for (Figure p1Figs : player1.figures)
				p1Figs.resetMoves(player1.getSpeed());
			for (Figure p2Figs : player2.figures)
				p2Figs.resetMoves(player2.getSpeed());

			this.validTiles.clear();
		} else {
			if (this.currentPlayer == this.firstPlayer)
				this.changePlayerTurn();
			else {
				if (this.firstPlayer == this.player1)
					this.firstPlayer = this.player2;
				else
					this.firstPlayer = this.player1;
				this.currentPhase = START_OF_TURN;
			}
		}
		this.repaint();
	}

	public boolean addFigure(Tile tile, City city, String figureName) {
		if (tile.getTerrain() != Terrain.Water) {
			if (city.getOutskirts().contains(tile)) {
				Figure figure = Figure.getFigure(currentPlayer, figureName,
						tile);
				figure.setScreenLocation(tile.getScreenLocation());
				figure.resetMoves(currentPlayer.getSpeed());
				figure.setTileLocal(tile);
				figure.setScreenLocation(tile.getScreenLocation());
				ArrayList<Figure> figures = tile.getFigures();
				figures.add(figure);
				currentPlayer.figures.add(figure);
				repaint();
				currentChoice = "";
				currentFigure = false;
				return true;
			}
		}
		return false;
	}

	// For testing...
	public ArrayList<Tile> getValidTileList() {
		return this.validTiles;
	}

	// For testing...
	public void setCurrentMovementFigure() {
		this.currentMovementFigure = this.player1.figures.get(0);
	}

	// For testing...
	public void setCurrentMovementFigureMoves() {
		this.currentMovementFigure.decreaseMoves();
	}

	// For testing...
	public void resetValidTileList() {
		this.validTiles.clear();
	}

	void handleBuild(int i, City city) {
		if (items[i].getText().equals(messages.getString("settler"))
				|| items[i].getText().equals(messages.getString("army"))) {
			if (items[i].getText().equals(messages.getString("settler"))
					|| items[i].getText().equals(messages.getString("army"))) {
				if (items[i].getText().equals(messages.getString("settler"))) {
					if (city.getProduction() < 6)
						return;
					int settlers = 0;
					for (Figure f : currentPlayer.figures) {
						if (f instanceof Settler)
							settlers++;
					}
					if (settlers >= 2) {
						return;
					}
				} else {
					if (city.getProduction() < 4)
						return;
					int armies = 0;
					for (Figure f : currentPlayer.figures) {
						if (f instanceof Army)
							armies++;
					}
					if (armies >= 6) {
						return;
					}
				}
				Board.currentFigure = true;
				currentChoice = items[i].getText();
			}
		} else if (items[i].getText().equals(messages.getString("building"))) {
			String[] Choiceitems = { messages.getString("market"),
					messages.getString("bank"), messages.getString("temple"),
					messages.getString("cathedral"),
					messages.getString("granary"),
					messages.getString("aqueduct"),
					messages.getString("library"),
					messages.getString("university"),
					messages.getString("barracks"),
					messages.getString("academy"),
					messages.getString("workshop"),
					messages.getString("ironMine"),
					messages.getString("tradingPost"), "Harbor" };
			makeChoice(Choiceitems, new BuildingHandler(), currentClick);
		}
		repaint();
	}

	public static boolean isGoingForResource() {
		return goingForResource;
	}

	public static void setGoingForResource(boolean goingForResource) {
		Board.goingForResource = goingForResource;
	}

	public static Player getPlayer(int playerNumber) {
		if (playerNumber == 1)
			return player1;
		return player2;
	}

}