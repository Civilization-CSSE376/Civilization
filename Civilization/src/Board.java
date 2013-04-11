import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Hashtable;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	final String START_OF_TURN = "Start of Turn";
	final String TRADE = "Trade";
	final String CITY_MANAGEMENT = "City Management";
	final String MOVEMENT = "Movement";
	final String RESEARCH = "Research";
	private String currentPhase;

	private File file = new File("src/1stEight.txt");

	private Player player1;
	private Player player2;
	private Figure currentMovementFigure = null;

	public static ArrayList<Panel> map;
	public static ArrayList<Player> players;
	// private Market market;
	private Player firstPlayer;
	private Player currentPlayer;

	private int phase;

	private String player1Civilization;
	private String player2Civilization;

	private ArrayList<Tile> validTiles = new ArrayList<Tile>();

	public Board(String p1Civ, String p2Civ) {
		map = new ArrayList<Panel>();
		readFromFile(this.file);

		this.player1Civilization = p1Civ;
		this.player2Civilization = p2Civ;

		this.player1 = new Player();
		this.player2 = new Player();

		Settler settler1 = new Settler(player1, map.get(0).getTiles()[0][0]);
		Settler settler2 = new Settler(player2, map.get(7).getTiles()[3][3]);

		settler1.resetMoves(player1.getSpeed());
		settler2.resetMoves(player2.getSpeed());

		settler1.setLocation(10, 10);
		settler2.setLocation((440 * 4) - 5, 830);

		this.player1.figures.add(settler1);
		this.player2.figures.add(settler2);

		map.get(0).getTiles()[0][0].getFigures().add(settler1);
		map.get(7).getTiles()[3][3].getFigures().add(settler2);

		City city1 = new City(map.get(0).getTiles()[1][1]);
		City city2 = new City(map.get(7).getTiles()[2][2]);

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

		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);
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
		if (!panel.getIsExplored())
			panel.changeIsExplored();
	}

	public void makeMovementWindow(final ArrayList<Figure> figures) {
		int answer = JOptionPane.showConfirmDialog(null,
				"Do you want move this unit?", "Movement",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			currentMovementFigure = figures.get(0);
			return;
		} else {
			currentMovementFigure = null;
			return;
		}
	}

	public Boolean makeNewCityWindow(final Figure figure) {
		int answer = JOptionPane.showConfirmDialog(null,
				"Do you want to create a new city using this unit?",
				"Create New City", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.printf("\nMouse clicked at %d, %d\n", x, y);

			Panel panel = findPanel(x, y);
			Tile tile = findTile(panel, x, y);
			System.out.printf("Tile clicked was: Panel: %d, i: %d j: %d\n",
					map.indexOf(panel), tile.getxPos(), tile.getyPos());
			// displayTileInfoWindow(tile);

			if (Board.this.currentPhase.equals(START_OF_TURN)) {
				Figure newCity = null;
				for (Figure f : tile.getFigures()) {
					if (f instanceof Settler) {
						newCity = f;
						break;
					}
				}
				if (newCity == null) {
					return;
				}
				if (makeNewCityWindow(newCity)) {

					City city = new City(tile);// new City(tile, currentPlayer);
					if (true) {// city.isValid()) {
						city.setLocation(x, y);
						currentPlayer.cities.add(city);
						tile.setCity(city);
						currentPlayer.figures.remove(newCity);
						tile.getFigure().remove(newCity);
						repaint();
					}

				}
				return;

			} else if (Board.this.currentPhase.equals(CITY_MANAGEMENT)) {
				if (tile.getTerrain() != Tile.Terrain.Water) {
					ArrayList<Figure> figures = tile.getFigures();
					Figure settler = new Settler(currentPlayer, tile);
					settler.setLocation(x, y);
					settler.resetMoves(currentPlayer.getSpeed());
					figures.add(settler);
					currentPlayer.figures.add(settler);
					repaint();
				}
			} else if (Board.this.currentPhase.equals(MOVEMENT)) {
				if (currentMovementFigure == null
						|| currentMovementFigure.location.equals(tile)) {

					ArrayList<Figure> figures = tile.getFigures();
					if (!figures.isEmpty()) {
						Figure figure = figures.get(0);
						if (figure.getOwner() != null) {
							if (figure.getOwner().equals(
									Board.this.currentPlayer)
									&& !figure.getUsedThisTurn()) {
								makeMovementWindow(figures);
								getValidTiles(panel, tile);
							}

						}
					}
				}

				else {
					System.out.printf("Player has %d moves.\n",
							currentMovementFigure.getNumberOfMoves());
					if (currentMovementFigure.getNumberOfMoves() > 0) {
						if (Board.this.validTiles.contains(tile)) {
							System.out.println("Tile valid! Moving figure.");
							Tile oldTile = currentMovementFigure.location;
							oldTile.getFigures().remove(currentMovementFigure);
							Board.this.currentMovementFigure.setLocation(x, y);
							currentMovementFigure.setTileLocal(tile);
							tile.getFigures().add(currentMovementFigure);
							// currentMovementFigure.setUsedThisTurn(true);
							currentMovementFigure.decreaseMoves();
							currentMovementFigure = null;
							checkUnexploredPanelNew(x, y);
							Board.this.validTiles.clear();
							Board.this.repaint();
						}
					}
				}
			}
		}

		private void getValidTiles(Panel panel, Tile tile) {
			int panelNumber = map.indexOf(panel);
			int x = tile.getxPos();
			int y = tile.getyPos();
			Tile tileToCheck;
			if (currentMovementFigure.getNumberOfMoves() == 1) { // Can't end
																	// on water!
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j <= y + 1; j++) {

						if (!(i == x && j == y)) {

							if (i != -1 && i != 4 && j != -1 && j != 4) {
								tileToCheck = map.get(panelNumber).getTiles()[i][j];
								if (!tileToCheck.getTerrain().toString()
										.equals("Water"))
									Board.this.validTiles.add(tileToCheck);
							} else if (i == -1 && j == -1) {
								if (panelNumber > 4) {
									tileToCheck = map.get(panelNumber - 5)
											.getTiles()[3][3];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (i == -1 && j == 4) {
								if (panelNumber > 0 && panelNumber < 4) {
									tileToCheck = map.get(panelNumber + 3)
											.getTiles()[0][3];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (i == 4 && j == -1) {
								if (panelNumber > 3 && panelNumber < 7) {
									tileToCheck = map.get(panelNumber - 3)
											.getTiles()[3][0];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (i == 4 && j == 4) {
								if (panelNumber < 3) {
									tileToCheck = map.get(panelNumber + 5)
											.getTiles()[0][0];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (i == -1) {
								if (panelNumber > 0 && panelNumber != 4) {
									tileToCheck = map.get(panelNumber - 1)
											.getTiles()[3][j];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (i == 4) {
								if (panelNumber < 7 && panelNumber != 3) {
									tileToCheck = map.get(panelNumber + 1)
											.getTiles()[0][j];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else if (j == -1) {
								if (panelNumber > 3) {
									tileToCheck = map.get(panelNumber - 4)
											.getTiles()[i][3];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							} else {
								if (panelNumber < 4) {
									tileToCheck = map.get(panelNumber + 4)
											.getTiles()[i][0];
									if (!tileToCheck.getTerrain().toString()
											.equals("Water"))
										Board.this.validTiles.add(tileToCheck);
								}
							}

						}
					}

				}
			} else {
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j <= y + 1; j++) {

						if (!(i == x && j == y)) {
							System.out.println("Adding tile... i = " + i
									+ " and j = " + j);
							if (i != -1 && i != 4 && j != -1 && j != 4) {
								Board.this.validTiles.add(map.get(panelNumber)
										.getTiles()[i][j]);
							} else if (i == -1 && j == -1) {
								if (panelNumber > 4) {
									Board.this.validTiles.add(map.get(
											panelNumber - 5).getTiles()[3][3]);
								}
							} else if (i == -1 && j == 4) {
								if (panelNumber > 0 && panelNumber < 4) {
									Board.this.validTiles.add(map.get(
											panelNumber + 3).getTiles()[0][3]);
								}
							} else if (i == 4 && j == -1) {
								if (panelNumber > 3 && panelNumber < 7) {
									Board.this.validTiles.add(map.get(
											panelNumber - 3).getTiles()[3][0]);
								}
							} else if (i == 4 && j == 4) {
								if (panelNumber < 3) {
									Board.this.validTiles.add(map.get(
											panelNumber + 5).getTiles()[0][0]);
								}
							} else if (i == -1) {
								if (panelNumber > 0 && panelNumber != 4) {
									Board.this.validTiles.add(map.get(
											panelNumber - 1).getTiles()[3][j]);
								}
							} else if (i == 4) {
								if (panelNumber < 7 && panelNumber != 3) {
									Board.this.validTiles.add(map.get(
											panelNumber + 1).getTiles()[0][j]);
								}
							} else if (j == -1) {
								if (panelNumber > 3) {
									Board.this.validTiles.add(map.get(
											panelNumber - 4).getTiles()[i][3]);
								}
							} else {
								if (panelNumber < 4) {
									Board.this.validTiles.add(map.get(
											panelNumber + 4).getTiles()[i][0]);
								}
							}

						}
					}

				}
			}

			System.out.println("Array size: " + Board.this.validTiles.size());

		}

		private void displayTileInfoWindow(Tile tile) {
			JFrame frame = new JFrame("Tile info");
			frame.setLayout(new GridLayout(6, 1));
			JLabel terrain = new JLabel("Terrain is "
					+ tile.getTerrain().toString());
			JLabel trade = new JLabel("Trade is " + tile.getTrade());
			JLabel production = new JLabel("Production is "
					+ tile.getProduction());
			JLabel resource = new JLabel("Resource is "
					+ tile.getResource().toString());
			JLabel culture = new JLabel("Culture is " + tile.getCulture());
			JLabel coin = new JLabel("Coin is " + tile.getCoins());
			frame.add(terrain);
			frame.add(trade);
			frame.add(production);
			frame.add(resource);
			frame.add(culture);
			frame.add(coin);
			frame.pack();
			frame.setVisible(true);

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
	public Board(Hashtable<String, Panel> map) {
		this.map = new ArrayList<Panel>();
		this.map.add(map.get("topLeft"));
		this.map.add(map.get("topRight"));
		this.map.add(map.get("bottomLeft"));
		this.map.add(map.get("bottomRight"));
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

		map.get(0).changeIsExplored(); // Player 1's initial location
		map.get(7).changeIsExplored(); // Player 2's initial location
	}

	public void drawTerrain(Graphics2D g2) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					Color rectColor = Color.RED;
					if (map.get(i).getIsExplored()) {
						switch (map.get(i).getTiles()[j][k].getTerrain()
								.toString()) {
						case "Desert":
							rectColor = Color.YELLOW;
							break;
						case "Mountain":
							rectColor = Color.DARK_GRAY;
							break;
						case "Forest":
							rectColor = Color.WHITE;
							break;
						case "Grassland":
							rectColor = Color.GREEN;
							break;
						case "Water":
							rectColor = Color.BLUE;
							break;
						}
						if (i > 3) {
							Rectangle2D.Double rect = new Rectangle2D.Double(
									(440 * (i - 4)) + (110 * j),
									440 + (110 * k), 110, 110);
							g2.setColor(rectColor);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						} else {
							Rectangle2D.Double rect = new Rectangle2D.Double(
									(440 * i) + (110 * j), (110 * k), 110, 110);
							g2.setColor(rectColor);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						}
						// System.out.println("Tile[" + j + "][" + k +
						// "] created at location " + (20 + (440 * i) + (110 *
						// j)) +
						// " " + (20 + nextRow + (110 * k)));
					} else {
						if (i > 3) {
							Rectangle2D.Double rect = new Rectangle2D.Double(
									(440 * (i - 4)) + (110 * j),
									440 + (110 * k), 110, 110);
							g2.setColor(Color.BLACK);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						} else {
							Rectangle2D.Double rect = new Rectangle2D.Double(
									(440 * i) + (110 * j), (110 * k), 110, 110);
							g2.setColor(Color.BLACK);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						}
					}
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
		g2.drawString("Current Phase: " + this.currentPhase, 50, 900);

		if (this.currentPlayer == this.player1) {
			g2.setColor(Color.RED);
			g2.drawString("Player 1's turn.", 500, 900);
		} else {
			g2.setColor(Color.ORANGE);
			g2.drawString("Player 2's turn.", 500, 900);
		}

		for (City cities : player1.cities) {
			Rectangle2D.Double p1City = new Rectangle2D.Double(
					cities.getLocation().x - 25, cities.getLocation().y - 25,
					50, 50);
			g2.setColor(Color.RED);
			g2.fill(p1City);

		}

		for (City cities : player2.cities) {
			Rectangle2D.Double p2City = new Rectangle2D.Double(
					cities.getLocation().x - 25, cities.getLocation().y - 25,
					50, 50);
			g2.setColor(Color.ORANGE);
			g2.fill(p2City);
		}

		for (Figure figure : player1.figures) {
			Ellipse2D.Double player1 = new Ellipse2D.Double(
					figure.getLocation().x - 25, figure.getLocation().y - 25,
					50, 50);
			g2.setColor(Color.RED);
			g2.fill(player1);
		}

		for (Figure figure : player2.figures) {
			Ellipse2D.Double player2 = new Ellipse2D.Double(
					figure.getLocation().x - 25, figure.getLocation().y - 25,
					50, 50);
			g2.setColor(Color.ORANGE);
			g2.fill(player2);
		}
		// System.out.println("Player drawn at " + (this.location.x - 25) + ", "
		// + (this.location.y - 25));

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

	private void changePlayerTurn() {
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
			}
		} else if (this.currentPhase.equals(TRADE)) {
			if (this.currentPlayer == this.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
				this.currentPhase = CITY_MANAGEMENT;
			}
		} else if (this.currentPhase.equals(CITY_MANAGEMENT)) {
			if (this.currentPlayer == this.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
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

		// if (this.currentPlayer != this.firstPlayer) {
		// if (this.phase < 5) {
		// if (this.currentPlayer.equals(this.player1)) {
		// this.currentPlayer = player2;
		// } else {
		// this.currentPlayer = player1;
		// }
		// phase++;
		// } else {
		// phase = 1;
		// this.firstPlayer = this.currentPlayer;
		// }
		// } else {
		// if (this.currentPlayer.equals(this.player1)) {
		// this.currentPlayer = player2;
		// } else {
		// this.currentPlayer = player1;
		// }
		// }
		this.repaint();

	}
}
