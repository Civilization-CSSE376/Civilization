package Civ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
import java.util.Map;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

import java.util.Hashtable;

import javax.swing.JPanel;

import TechCards.*;

import Civ.Tile.Resource;

@SuppressWarnings("serial")
public class Board extends JPanel {

	public static Map<String, String> cardDescriptions = new HashMap<String, String>() {
		{
			put("Ballistics",
					"If the player's artillery level is less than, 4 then the player's new artillery level is 4");
			put("Banking", "Unlock the ability to build a bank");
			put("Biology",
					"If the player's stacksize is less than 5, then the player's new stacksize is 5");
			put("Chivalry",
					"Unlock the Feudalism type of government, and if the player's calvary level is less than 2, then the player's new calvary level is 2");
			put("CivilServices",
					"Increase the player's handsize by 1 and the player's gold by 1");
			put("CodeOfLaws",
					"Unlock the republic type of government and unlock the ability to build a trading post");
			put("Combustion",
					"If the player's calvary level is less than 4, then the player's new calvary level is 4");
			put("Communism", "Unlock the communism type of government");
			put("Computers", "Increase the player's gold by 1");
			put("Construction", "Unlock the ability to build a workshop");
			put("Currency", "Unlock the ability to build a market");
			put("Democracy",
					"Unlock the democracy type of government, and if the player's infantry level is less than 2, the player's new infrantry level is 2");
			put("Engineering", "Unlock the ability to build an aqueduct");
			put("Flight",
					"If the player's speed is less than 6, then the player's new speed is 6");
			put("Gunpowder",
					"If the player's infantry level is less than 3, then the player's new infantry level is 3");
			put("HorsebackRiding",
					"If the player's speed is less than 3, then the player's new speed is 3");
			put("Irrigation",
					"If the player's city limit is less than 3, then the player's new city limit is 3");
			put("Masonry",
					"If the player's stacksize is less than 3, then the player's new stacksize is 3");
			put("Mathematics",
					"If the player's artillery level is less than 2, then the player's new artillery level is 2");
			put("MetalCasting",
					"Increase the player's gold by 1, and if the player's artillery level is less than 3, then the player's new artillery level is 3");
			put("MetalWorking", "Unlock the ability to build a barracks");
			put("MilitaryScience", "Unlock the ability to build an academy");
			put("Monarchy", "Unlock the monarchy type of government");
			put("Navigation", "Unlock the ability to build a harbor");
			put("Philosophy", "Unlock the ability to build a temple");
			put("Pottery", "Unlock the ability to build a granary");
			put("PrintingPress",
					"Unlock the ability to build a university, and if the player's stacksize is less than 4, then the player's new stacksize is 4");
			put("Railroad",
					"Unlock the ability to build an iron mine and if the player's calvary level is less than 3, then the player's new calvary level is 3");
			put("ReplaceableParts",
					"If the player's stacksize is less than 6, then the player's new stacksize is 6, and if the player's infantry level is less than 4, then the player's new infantry level is 4");
			put("Sailing",
					"If the player's speed is less than 4, then the player's new speed is 4");
			put("SteamPower",
					"If the player's speed is less than 5, then the player's new speed is 5");
			put("Theology",
					"Increase the player's handsize by 1, unlock the fundamentalism type of government, and unlock the ability to build a cathedral");
			put("Writing", "Unlock the ability to build a library");
			put("SpaceFlight", "You win the game");
		}
	};

	public static Map<String, TechCard> techCards = new HashMap<String, TechCard>() {
		{
			put("AnimalHusbandry", new AnimalHusbandry());
			put("AtomicTheory", new AtomicTheory());
			put("Ballistics", new Ballistics());
			put("Banking", new Banking());
			put("Biology", new Biology());
			put("Chivalry", new Chivalry());
			put("CivilServices", new CivilServices());
			put("CodeOfLaws", new CodeOfLaws());
			put("Combustion", new Combustion());
			put("Communism", new Communism());
			put("Computers", new Computers());
			put("Construction", new Construction());
			put("Currency", new Currency());
			put("Democracy", new Democracy());
			put("Engineering", new Engineering());
			put("Flight", new Flight());
			put("Gunpowder", new Gunpowder());
			put("HorsebackRiding", new HorsebackRiding());
			put("Irrigation", new Irrigation());
			put("Masonry", new Masonry());
			put("MassMedia", new MassMedia());
			put("Mathematics", new Mathematics());
			put("MetalCasting", new MetalCasting());
			put("MetalWorking", new MetalWorking());
			put("MilitaryScience", new MilitaryScience());
			put("Monarchy", new Monarchy());
			put("Navigation", new Navigation());
			put("Philosophy", new Philosophy());
			put("Pottery", new Pottery());
			put("PrintingPress", new PrintingPress());
			put("Railroad", new Railroad());
			put("ReplaceableParts", new ReplaceableParts());
			put("Sailing", new Sailing());
			put("SteamPower", new SteamPower());
			put("Theology", new Theology());
			put("Writing", new Writing());
		}
	};

	final String START_OF_TURN = "Start of Turn";
	final String TRADE = "Trade";
	final String CITY_MANAGEMENT = "City Management";
	final String MOVEMENT = "Movement";
	final String RESEARCH = "Research";
	private String currentPhase;
	public String currentChoice;;

	private static Player player1;
	private static Player player2;
	Figure currentMovementFigure = null;
	public static boolean isGameOver = false;

	public static ArrayList<Panel> map;
	public static ArrayList<Player> players = new ArrayList<Player>();
	// private Market market;
	private static Player firstPlayer;
	public static Player currentPlayer;

	private int phase;
	public JFrame p;

	public static ArrayList<Player> winners = new ArrayList<Player>();

	private String player1Civilization;
	private String player2Civilization;

	public ArrayList<Tile> validTiles = new ArrayList<Tile>();
	private static ResourceBundle messages;

	public Board(String p1Civ, String p2Civ, ResourceBundle messages) {
		Board.messages = messages;
		this.player1Civilization = p1Civ;
		this.player2Civilization = p2Civ;

		writeCardDescriptions();
		writeTechCards();

		players = new ArrayList<Player>();

		map = new ArrayList<Panel>();
		readFromFile();
		setTileLocations();

		setPanelNeighbors();

		Board.player1 = playerConfig(this.player1Civilization);
		Board.player2 = playerConfig(this.player2Civilization);

		players.add(Board.player1);
		players.add(Board.player2);

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
		army2.setScreenLocation((map.get(7).getTiles()[2][3]
				.getScreenLocation()));

		this.player1.figures.add(settler1);
		this.player2.figures.add(settler2);
		 this.player1.figures.add(army1);
		 this.player2.figures.add(army2);

		map.get(0).getTiles()[0][0].getFigures().add(settler1);
		map.get(7).getTiles()[3][3].getFigures().add(settler2);
		 map.get(0).getTiles()[1][0].getFigures().add(army1);
		 map.get(7).getTiles()[2][3].getFigures().add(army2);

		City city1 = new City(map.get(0).getTiles()[1][1], Board.player1);
		City city2 = new City(map.get(7).getTiles()[2][2], Board.player2);

		city1.setLocation(130, 130);
		city2.setLocation((440 * 4) - 115, 750);

		Board.player1.cities.add(city1);
		Board.player2.cities.add(city2);

//		Board.player1.government = new Government(Board.player1, "Fundamentalism");
//		Board.player1.units.add(new Unit(messages.getString("infantry"), 1, messages)); // TODO check we already have this...
//		Board.player1.units.add(new Unit(messages.getString("cavalry"), 1, messages));
//		Board.player1.units.add(new Unit(messages.getString("artillery"), 1, messages));

		map.get(0).getTiles()[1][1].setCity(city1);
		map.get(7).getTiles()[2][2].setCity(city2);

		this.currentPhase = START_OF_TURN;

		Board.firstPlayer = player1;
		Board.currentPlayer = player1;
		this.phase = 1;

		// TODO make this more efficient. quickly copied/pasted initially
		for (City c : Board.player1.cities) {
			Board.player1.trade += c.calcTrade();
		}
		for (City c : Board.player2.cities) {
			Board.player2.trade += c.calcTrade();
		}

		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);

	}

	public void writeTechCards() {
		techCards = new HashMap<String, TechCard>() {
			{
				put(messages.getString("ballistics"), new Ballistics());
				put(messages.getString("banking"), new Banking());
				put(messages.getString("biology"), new Biology());
				put(messages.getString("chivalry"), new Chivalry());
				put(messages.getString("civilServices"), new CivilServices());
				put(messages.getString("codeOfLaws"), new CodeOfLaws());
				put(messages.getString("combustion"), new Combustion());
				put(messages.getString("communism"), new Communism());
				put(messages.getString("computers"), new Computers());
				put(messages.getString("construction"), new Construction());
				put(messages.getString("currency"), new Currency());
				put(messages.getString("democracy"), new Democracy());
				put(messages.getString("engineering"), new Engineering());
				put(messages.getString("flight"), new Flight());
				put(messages.getString("gunpowder"), new Gunpowder());
				put(messages.getString("horsebackRiding"),
						new HorsebackRiding());
				put(messages.getString("irrigation"), new Irrigation());
				put(messages.getString("masonry"), new Masonry());
				put(messages.getString("mathematics"), new Mathematics());
				put(messages.getString("metalCasting"), new MetalCasting());
				put(messages.getString("metalWorking"), new MetalWorking());
				put(messages.getString("militaryScience"),
						new MilitaryScience());
				put(messages.getString("monarchy"), new Monarchy());
				put(messages.getString("navigation"), new Navigation());
				put(messages.getString("philosophy"), new Philosophy());
				put(messages.getString("pottery"), new Pottery());
				put(messages.getString("printingPress"), new PrintingPress());
				put(messages.getString("railroad"), new Railroad());
				put(messages.getString("replaceableParts"),
						new ReplaceableParts());
				put(messages.getString("sailing"), new Sailing());
				put(messages.getString("steamPower"), new SteamPower());
				put(messages.getString("theology"), new Theology());
				put(messages.getString("writing"), new Writing());
			}
		};
	}

	public void writeCardDescriptions() {
		cardDescriptions = new HashMap<String, String>() {
			{
				put(messages.getString("ballistics"),
						messages.getString("ballisticsCard"));
				put(messages.getString("banking"),
						messages.getString("bankingCard"));
				put(messages.getString("biology"),
						messages.getString("biologyCard"));
				put(messages.getString("chivalry"),
						messages.getString("chivalryCard"));
				put(messages.getString("civilServices"),
						messages.getString("civilServicesCard"));
				put(messages.getString("codeOfLaws"),
						messages.getString("codeOfLawsCard"));
				put(messages.getString("combustion"),
						messages.getString("combustionCard"));
				put(messages.getString("communism"),
						messages.getString("communismCard"));
				put(messages.getString("computers"),
						messages.getString("computersCard"));
				put(messages.getString("construction"),
						messages.getString("constructionCard"));
				put(messages.getString("currency"),
						messages.getString("currencyCard"));
				put(messages.getString("democracy"),
						messages.getString("democracyCard"));
				put(messages.getString("engineering"),
						messages.getString("engineeringCard"));
				put(messages.getString("flight"),
						messages.getString("flightCard"));
				put(messages.getString("gunpowder"),
						messages.getString("gunpowderCard"));
				put(messages.getString("horsebackRiding"),
						messages.getString("horsebackRidingCard"));
				put(messages.getString("irrigation"),
						messages.getString("irrigationCard"));
				put(messages.getString("masonry"),
						messages.getString("masonryCard"));
				put(messages.getString("mathematics"),
						messages.getString("mathematicsCard"));
				put(messages.getString("metalCasting"),
						messages.getString("metalCastingCard"));
				put(messages.getString("metalWorking"),
						messages.getString("metalWorkingCard"));
				put(messages.getString("militaryScience"),
						messages.getString("militaryScienceCard"));
				put(messages.getString("monarchy"),
						messages.getString("monarchyCard"));
				put(messages.getString("navigation"),
						messages.getString("navigationCard"));
				put(messages.getString("philosophy"),
						messages.getString("philosophyCard"));
				put(messages.getString("pottery"),
						messages.getString("potteryCard"));
				put(messages.getString("printingPress"),
						messages.getString("printingPressCard"));
				put(messages.getString("railroad"),
						messages.getString("railroadCard"));
				put(messages.getString("replaceableParts"),
						messages.getString("replaceablePartsCard"));
				put(messages.getString("sailing"),
						messages.getString("sailingCard"));
				put(messages.getString("steamPower"),
						messages.getString("steamPowerCard"));
				put(messages.getString("theology"),
						messages.getString("theologyCard"));
				put(messages.getString("writing"),
						messages.getString("writingCard"));
				put(messages.getString("spaceFlight"),
						messages.getString("spaceFlightCard"));
			}
		};
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
		}
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
			}
			if (f instanceof Army && currentPlayer.figures.contains(f)
					&& currentPlayer.government.name.equals("Republic")) {
				newCity = f;
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
			currentMarker = null;
			currentChoice = null;
			goingForResource = false;
			city = tile.getCity();
			String[] choices = { messages.getString("buildSomethingOption"),
					messages.getString("collectResourceOption"),
					messages.getString("devoteArtsOption"),

					"convertTradeOption" };// TODO
											// messages.getString("convertTradeOption")
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
		ArrayList<Tile> playerOutskirtTiles = new ArrayList<Tile>();
		playerOutskirtTiles.addAll(city.getOutskirts());

		for (Figure f : Board.currentPlayer.figures) {
			if (f.getClass().toString().equals("class Civ.Settler")) {
				playerOutskirtTiles.add(f.location);
			}
		}

		if (Board.currentPlayer.government.name.equals("Feudalism")) {
			for (City c : Board.currentPlayer.cities) {
				if (!c.equals(city)) {
					playerOutskirtTiles.addAll(c.getOutskirts());
				}
			}
		}

		if ((tile.getResource() != null && !tile.getResource().toString()
				.equals("None"))
				&& playerOutskirtTiles.contains(tile)) {
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
					handleBuild(items[i].getText(),
							Board.currentCity.getProduction());
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
					} else if (items[i].getText().equals(
					// messages.getString("convertTradeOption")))
							"convertTradeOption")) {
						convertTradeToProduction(currentPlayer, currentCity);
					} else if (items[i].getText().equals(
							messages.getString("devoteArtsOption"))) {
						collectCulture(Board.currentPlayer, Board.currentCity);

					}
					repaint();
					return;
				}
		}
	}

	private class UnitHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					handleUnit(items[i].getText(),
							Board.currentCity.getProduction(), currentPlayer);
					return;
				}
		}
	}

	void handleBuild(String option, int production) {
		if (option.equals(messages.getString("settler"))
				|| option.equals(messages.getString("army"))) {
			if (option.equals(messages.getString("settler"))) {
				if (production < 6)
					return;
				int settlers = 0;
				for (Figure f : currentPlayer.figures) {
					if (f instanceof Settler)
						settlers++;
				}
				if (settlers >= 2) {
					return;
				}
			} else if (option.equals(messages.getString("army"))) {
				if (production < 4)
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
			currentChoice = option;
		} else if (option.equals(messages.getString("building"))) {
			String[] Choiceitems = currentPlayer.unlockedBuildings
					.toArray(new String[currentPlayer.unlockedBuildings.size()]);
			makeChoice(Choiceitems, new BuildingHandler(), currentClick);
		} else if (option.equals(messages.getString("units"))) {
			ArrayList<String> Choiceitems = new ArrayList<String>();
			Choiceitems.add(messages.getString("infantry"));
			Choiceitems.add(messages.getString("artillery"));
			Choiceitems.add(messages.getString("cavalry"));
			if (currentPlayer.techCards.contains(Board.techCards.get(messages
					.getString("flight")))) {
				Choiceitems.add(messages.getString("airplane"));
			}

			String[] choices = Choiceitems.toArray(new String[Choiceitems
					.size()]);
			makeChoice(choices, new UnitHandler(), currentClick);
		}
		repaint();
	}

	public boolean handleUnit(String option, int production, Player player) {
		int level = 0;
		if (option.equals(messages.getString("airplane"))) {
			level = player.airplaneLevel;
		} else if (option.equals(messages.getString("infantry"))) {
			level = player.infantryLevel;
		} else if (option.equals(messages.getString("artillery"))) {
			level = player.artilleryLevel;
		} else if (option.equals(messages.getString("cavalry"))) {
			level = player.cavalryLevel;
		} else {
			return false;
		}
		Unit unit = new Unit(option, level, messages);
		if (unit.cost > production) {
			return false;
		}
		player.units.add(unit);

		return true;
	}

	public void collectCulture(Player p, City c) {
		p.culture += c.calcCulture();
		if (p.government.name.equals("Communism")) {
			p.culture -= 1;
		}
		if (p.government.name.equals("Monarchy")) {
			p.culture += 1;
		}
		c.setHasAction(false);
		c = null;
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
					if (figure.getOwner().equals(Board.currentPlayer)
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
								enemyPlayer = new Player(messages);
							}

							if (enemy instanceof Settler) {
								if (currentMovementFigure
										.takeHut(currentPlayer)) {
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
									} else
										return;
								}
							} else {
								finishCombat(tile);
								calcBattleHandSize(tile, currentPlayer,
										enemyPlayer);
								p = new Combat(currentPlayer, enemyPlayer, 0, messages);
								p.setLocation(10, 10);
								this.setEnabled(false);
							}
							
							return;

						} else if (tile.getCity() != null) {
							City attackingCity = null;
							for (City c : currentPlayer.cities) {
								if (tile.getCity().equals(c)) {
									attackingCity = c;
									break;
								}
							}
							if (attackingCity == null
									&& !currentPlayer.government.name
											.equals("Democracy")) {
								if (currentPlayer == player1) {
									calcBattleHandSize(tile, currentPlayer,
											player2);
									p = new Combat(currentPlayer, player2, 12, messages);
								} else {
									calcBattleHandSize(tile, currentPlayer,
											player1);
									p = new Combat(currentPlayer, player1, 12, messages);
								}
								p.setLocation(10, 10);
								this.setEnabled(false);
								finishCombat(tile);

							}
						}
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

	public void convertTradeToProduction(Player player, City city) {
		if (player.trade >= 3) {
			city.setProduction(city.getProduction() + 1);
			// TODO 'Murica gets more cause it's awesome and stuffs
			player.trade -= 3;
		}
	}

	private void calcBattleHandSize(Tile tile, Player attacker, Player defender) {
		attacker.battleHandSize = 3;
		defender.battleHandSize = 3;

		if (attacker.government.name.equals("Fundamentalism")) {
			attacker.battleHandSize++;
		}
		if (defender.government.name.equals("Fundamentalism")) {
			defender.battleHandSize++;
		}

		for (Figure f : tile.getFigures()) {
			if (f instanceof Army) {
				if (f.getOwner().equals(attacker)) {
					attacker.battleHandSize += 2;
				}
				if (f.getOwner().equals(defender)) {
					defender.battleHandSize += 2;
				}
			}
		}

		for (City c : defender.cities) {
			if (c.getLocation().equals(tile)) {
				defender.battleHandSize += 3;
			}
		}

		// have to subtract because we "over counted" the first army
		attacker.battleHandSize -= 2;
		defender.battleHandSize -= 2;

	}

	private void finishCombat(Tile tile) {
		Tile oldTile = currentMovementFigure.location;
		oldTile.getFigures().remove(currentMovementFigure);
		Board.this.currentMovementFigure.setScreenLocation(tile
				.getScreenLocation());
		currentMovementFigure.setTileLocal(tile);
		tile.getFigures().add(currentMovementFigure);
		Board.this.validTiles.clear();
		currentMovementFigure.setUsedThisTurn(true);
		Board.this.repaint();
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
								if (!tileToCheck.getTerrain().toString()
										.equals("Water")) {
									Board.this.validTiles.add(tileToCheck);
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
		}

	}

	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Board.currentClick = new Point(x, y);

			Panel panel = findPanel(x, y);
			Tile tile = findTile(panel, x, y);
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

		final JFrame researchWindow = buildFrame(505, 380,
				messages.getString("research"));

		JPanel tierOptions = buildJPanel(250, 50, 0, 0);
		JPanel cardOptions = buildJPanel(250, 50, 250, 0);
		JPanel description = buildJPanel(500, 200, 0, 50);
		JPanel warningMessage = buildJPanel(500, 50, 0, 250);
		JPanel buttons = buildJPanel(500, 50, 0, 300);
		JPanel[] panels = { tierOptions, cardOptions, description,
				warningMessage, buttons };
		String[] tiers = { "1", "2", "3", "4", "5" };
		JLabel tierLabel = new JLabel(messages.getString("tier") + ": ");
		final JComboBox<String> tierDropDown = new JComboBox<String>(tiers);
		tierDropDown.setSelectedIndex(0);
		tierOptions.add(tierLabel);
		tierOptions.add(tierDropDown);

		JLabel cardLabel = new JLabel(messages.getString("card") + ": ");
		final JComboBox<String> techCards = new JComboBox<String>();
		
		@SuppressWarnings("unchecked")
		final ComboBoxModel<String>[] tierCards = new ComboBoxModel[5];
		tierCards[0] = new DefaultComboBoxModel<String>(new String[] {
				messages.getString("codeOfLaws"),
				messages.getString("currency"),
				messages.getString("horsebackRiding"),
				messages.getString("masonry"),
				messages.getString("metalWorking"),
				messages.getString("navigation"),
				messages.getString("philosophy"),
				messages.getString("pottery"), messages.getString("writing") });
		tierCards[1] = new DefaultComboBoxModel<String>(new String[] {
				messages.getString("chivalry"),
				messages.getString("civilServices"),
				messages.getString("construction"),
				messages.getString("democracy"),
				messages.getString("engineering"),
				messages.getString("irrigation"),
				messages.getString("mathematics"),
				messages.getString("monarchy"),
				messages.getString("printingPress"),
				messages.getString("sailing") });
		tierCards[2] = new DefaultComboBoxModel<String>(new String[] {
				messages.getString("banking"), messages.getString("biology"),
				messages.getString("communism"),
				messages.getString("gunpowder"),
				messages.getString("metalCasting"),
				messages.getString("militaryScience"),
				messages.getString("railroad"),
				messages.getString("steamPower"),
				messages.getString("theology") });
		tierCards[3] = new DefaultComboBoxModel<String>(new String[] {
				messages.getString("ballistics"),
				messages.getString("combustion"),
				messages.getString("computers"), messages.getString("flight"),
				messages.getString("replaceableParts") });
		tierCards[4] = new DefaultComboBoxModel<String>(
				new String[] { messages.getString("spaceFlight") });
		techCards.setModel(tierCards[0]);
		cardOptions.add(cardLabel);
		cardOptions.add(techCards);

		description.setLayout(new GridLayout(3, 1));

		final JLabel currentTrade = new JLabel(
				messages.getString("tierCardCost") + ": "
						+ getTierCardCost(tierDropDown.getSelectedIndex() + 1)
						+ " , " + messages.getString("playerHas") + " "
						+ currentPlayer.trade + " "
						+ messages.getString("tradeAvailable"));
		final JLabel cardName = new JLabel("     "
				+ messages.getString("cardName") + ":    "
				+ techCards.getItemAt(techCards.getSelectedIndex()));
		final JTextArea cardDescription = new JTextArea("     "
				+ messages.getString("cardDescription")
				+ ":    "
				+ cardDescriptions.get(techCards.getItemAt(techCards
						.getSelectedIndex())));
		
		cardDescription.setBackground(getBackground());
		cardDescription.setLineWrap(true);
		description.add(currentTrade);
		description.add(cardName);
		description.add(cardDescription);

		final JLabel message = new JLabel("");
		message.setForeground(Color.RED);
		warningMessage.add(message);

		final JButton buy = new JButton(messages.getString("buy"));
		JButton cancel = new JButton(messages.getString("cancel"));
		JButton tree = new JButton(messages.getString("playerTechCardTree"));
		
		if (!checkValidTier(tierDropDown.getSelectedIndex() + 1)) {
			message.setText(messages
					.getString("cannotBuyCardTierLevel"));
			buy.setEnabled(false);
		} else if (checkIfPlayerHasCard(techCards
				.getItemAt(techCards.getSelectedIndex()))) {
			message.setText(messages.getString("cardAlreadyOwned"));
			buy.setEnabled(false);
		} else if (!checkPlayerHasEnoughTrade(tierDropDown
				.getSelectedIndex() + 1)) {
			message.setText(messages.getString("notEnoughTrade"));
			buy.setEnabled(false);
		} else {
			buy.setEnabled(true);
			message.setText("");
		}
		
		buttons.add(tree);
		buttons.add(buy);
		buttons.add(cancel);

		for(JPanel panel : panels) researchWindow.add(panel);

		researchWindow.setVisible(true);

		tree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				final JFrame treeWindow = buildFrame(525, 370,
						messages.getString("playerTechCardTree"));

				treeWindow.add(drawTechCardTree(currentPlayer));

				JPanel button = buildJPanel(525, 50, 0, 290);
				JButton close = new JButton(messages.getString("close"));
				button.add(close);
				treeWindow.add(button);

				treeWindow.setAlwaysOnTop(true);
				treeWindow.setVisible(true);

				close.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						treeWindow.dispose();
					}

				});
			}
		});

		techCards.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentTrade.setText(messages.getString("tierCardCost") + ": "
						+ getTierCardCost(tierDropDown.getSelectedIndex() + 1)
						+ " , " + messages.getString("playerHas") + " "
						+ currentPlayer.trade + " "
						+ messages.getString("tradeAvailable"));
				cardName.setText("     " + messages.getString("cardName")
						+ ":    "
						+ techCards.getItemAt(techCards.getSelectedIndex()));
				cardDescription.setText("     "
						+ messages.getString("cardDescription")
						+ ":    "
						+ cardDescriptions.get(techCards.getItemAt(techCards
								.getSelectedIndex())));
				if (!message.getText().equals(
						messages.getString("cannotBuyCardTierLevel"))) {
					if (checkIfPlayerHasCard(techCards.getItemAt(techCards.getSelectedIndex()))) {
						message.setText(messages.getString("cardAlreadyOwned"));
						buy.setEnabled(false);
					} else if (!checkPlayerHasEnoughTrade(tierDropDown
							.getSelectedIndex() + 1)) {
						message.setText(messages.getString("notEnoughTrade"));
						buy.setEnabled(false);
					} else {
						buy.setEnabled(true);
						message.setText("");
					}
				}
			}

		});

		tierDropDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				techCards.setModel(tierCards[tierDropDown.getSelectedIndex()]);

				if (!checkValidTier(tierDropDown.getSelectedIndex() + 1)) {
					message.setText(messages
							.getString("cannotBuyCardTierLevel"));
					buy.setEnabled(false);
				} else if (checkIfPlayerHasCard(techCards
						.getItemAt(techCards.getSelectedIndex()))) {
					message.setText(messages.getString("cardAlreadyOwned"));
					buy.setEnabled(false);
				} else if (!checkPlayerHasEnoughTrade(tierDropDown
						.getSelectedIndex() + 1)) {
					message.setText(messages.getString("notEnoughTrade"));
					buy.setEnabled(false);
				} else {
					buy.setEnabled(true);
					message.setText("");
				}

			}
		});

		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlayer.techCards.add(Board.techCards.get(techCards
						.getItemAt(techCards.getSelectedIndex())));
				updateValidTiersAndCards(tierDropDown.getSelectedIndex() + 1);
				currentPlayer.trade = 0;
				if (checkIfPlayerHasCard(messages
						.getString("spaceFlight"))) {
					currentPlayer.hasWon = true;
					currentPlayer.winCondition = messages.getString("tech");
				}
				researchWindow.dispose();
			}

		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				researchWindow.dispose();
			}

		});
	}
	
	public boolean checkIfPlayerHasCard(String name){
		for(TechCard card : currentPlayer.techCards) if(card.name.equals(name)) return true;
		return false;
	}

	public static JFrame buildFrame(int width, int height, String title) {
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLayout(null);
		return frame;
	}

	public static JPanel buildJPanel(int width, int height, int xLocation,
			int yLocation) {
		JPanel panel = new JPanel();
		panel.setLocation(xLocation, yLocation);
		panel.setSize(width, height);
		return panel;
	}

	public int getTierCardCost(int tier) {
		if (tier == 1)
			return 6;
		else if (tier == 2)
			return 11;
		else if (tier == 3)
			return 16;
		else if (tier == 4)
			return 21;
		else
			return 26;
	}

	public void updateValidTiersAndCards(int tier) {
		if (tier == 1) {
			currentPlayer.tier1Cards++;
			if ((currentPlayer.tier1Cards - currentPlayer.tier2Cards) >= 2)
				currentPlayer.canBuyTier2TechCard = true;
			else
				currentPlayer.canBuyTier2TechCard = false;
		} else if (tier == 2) {
			currentPlayer.tier2Cards++;
			if ((currentPlayer.tier2Cards - currentPlayer.tier3Cards) >= 2)
				currentPlayer.canBuyTier3TechCard = true;
			else
				currentPlayer.canBuyTier3TechCard = false;
		} else if (tier == 3) {
			currentPlayer.tier3Cards++;
			if ((currentPlayer.tier3Cards - currentPlayer.tier4Cards) >= 2)
				currentPlayer.canBuyTier4TechCard = true;
			else
				currentPlayer.canBuyTier4TechCard = false;
		} else {
			currentPlayer.tier4Cards++;
			if (currentPlayer.tier4Cards >= 2)
				currentPlayer.canBuyTier5TechCard = true;
			else
				currentPlayer.canBuyTier5TechCard = false;
		}
	}

	public boolean checkPlayerHasEnoughTrade(int tier) {
		if (tier == 1 && currentPlayer.trade >= 6)
			return true;
		else if (tier == 2 && currentPlayer.trade >= 11)
			return true;
		else if (tier == 3 && currentPlayer.trade >= 16)
			return true;
		else if (tier == 4 && currentPlayer.trade >= 21)
			return true;
		else if (tier == 5 && currentPlayer.trade >= 26)
			return true;
		else
			return false;
	}

	public boolean checkValidTier(int tier) {
		if (tier == 1)
			return true;
		else if (tier == 2 && currentPlayer.canBuyTier2TechCard)
			return true;
		else if (tier == 3 && currentPlayer.canBuyTier3TechCard)
			return true;
		else if (tier == 4 && currentPlayer.canBuyTier4TechCard)
			return true;
		else if (tier == 5 && currentPlayer.canBuyTier5TechCard)
			return true;
		else
			return false;
	}

	public static JPanel drawTechCardTree(Player player) {
		JPanel tree = buildJPanel(510, 290, 0, 0);
		tree.setLayout(null);
		if (player.tier1Cards + player.tier2Cards + player.tier3Cards
				+ player.tier4Cards == 0) {

			JLabel label = new JLabel(messages.getString("noCards"));

			label.setLocation(50, 145);
			label.setSize(400, 20);
			tree.add(label);
		} else {
			int xCoord = 10;
			for (int i = 0; i < player.tier1Cards; i++) {

				tree.add(makeCard(xCoord, 230));
				xCoord += 55;
			}

			xCoord = 37;
			for (int i = 0; i < player.tier2Cards; i++) {
				tree.add(makeCard(xCoord, 175));
				xCoord += 55;
			}

			xCoord = 65;
			for (int i = 0; i < player.tier3Cards; i++) {
				tree.add(makeCard(xCoord, 120));
				xCoord += 55;
			}

			xCoord = 92;
			for (int i = 0; i < player.tier4Cards; i++) {
				tree.add(makeCard(xCoord, 65));
				xCoord += 55;
			}
		}
		return tree;
	}

	public static JPanel makeCard(int x, int y) {
		JPanel card = buildJPanel(50, 50, x, y);
		card.setBackground(Color.BLACK);
		return card;

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

		Board.player1.setLocation(x, y);
	}

	private Player playerConfig(String civ) {

		Player tempPlayer = new Player(messages);

		switch (civ) {
		case "Egypt": // TODO check if needs internationalized
			tempPlayer.techCards.add(new Construction());
			tempPlayer.tier1Cards += 1;
			// free wonder at start of game
			// one free building each turn as an action
			break;
		case "Russia":
			tempPlayer.techCards.add(new Communism());
			tempPlayer.government = new Government(tempPlayer, "Communism");
			tempPlayer.tier1Cards += 1;
			// tempPlayer.stackSize = 3;
			// one extra army
			/*
			 * once per turn the russians may move an army or scout into an
			 * enemy city and sacrifice that figure to research a tech known by
			 * that civilization for free. armies sacrificed this way cannot
			 * also attack
			 */
			break;
		case "Rome":
			tempPlayer.techCards.add(new CodeOfLaws());
			tempPlayer.government = new Government(tempPlayer, "Republic");
			tempPlayer.tier1Cards += 1;
			/*
			 * the romans advance one space on the culture track for free each
			 * time they build a wonder or a city, and each time they conquer a
			 * city or village
			 */
			break;
		case "America":
			tempPlayer.techCards.add(new Currency());
			tempPlayer.tier1Cards += 1;
			// free great person at start of game
			/*
			 * each time the americans convert 3 trade into production, they
			 * recieve 2 production instead of 1
			 */
			break;
		case "Germany":
			tempPlayer.techCards.add(new MetalWorking());
			tempPlayer.tier1Cards += 1;
			// tempPlayer.units.add(new Unit("Infantry", 1));
			// tempPlayer.units.add(new Unit("Infantry", 1));
			/*
			 * after setup, each time the germans research a tech that upgrades
			 * or unlocks a unit, they build one of that unit for free and gain
			 * one resource of their choice from the market
			 */

			break;
		case "China":
			tempPlayer.techCards.add(new Writing());
			tempPlayer.tier1Cards += 1;
			/*
			 * the chinese start with city walls in their capital. the chinese
			 * gain 3 culture each time they explore a hut or conquer a village.
			 * the chinese may save one of their killed units after each battle,
			 * <<<<<<< HEAD returning it to their staning forces. =======
			 * returning it to their standing forces. >>>>>>>
			 * a685e1d7cfca774b593082f85c41a5cdbba8ed0e
			 */
			break;
		default:
			break;
		}

		return tempPlayer;
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

		if (Board.currentPlayer == Board.player1) {
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

	public String getPhaseText() {
		String phase = "";
		switch (this.currentPhase) {
		case START_OF_TURN:
			phase = messages.getString("startOfTurn");
			break;
		case TRADE:
			phase = messages.getString("trade");
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
					// Did not load image correctly
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
					// Did not load image correctly
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
			// Did not load image correctly
			e.printStackTrace();
		}

	}

	private void drawPlayer2Panel(Graphics2D g2) {

		String filename = "src/panels/" + this.player2Civilization + ".png";

		try {
			BufferedImage image = ImageIO.read(new File(filename));
			g2.drawImage(image, 1320, 440, null);
		} catch (IOException e) {
			// Did not load image correctly
			e.printStackTrace();
		}

	}

	public void changePlayerTurn() {
		if (Board.currentPlayer == Board.player1)
			Board.currentPlayer = Board.player2;
		else
			Board.currentPlayer = Board.player1;
	}

	public void endPhase() {

		if (this.currentPhase.equals(START_OF_TURN)) {
			if (Board.currentPlayer == Board.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
				this.currentPhase = TRADE;
				for (City c : Board.currentPlayer.cities) {
					Board.currentPlayer.trade += c.calcTrade();
				}
				if (Board.currentPlayer.government.name
						.equals("Democracy")) {
					Board.currentPlayer.trade += 2;
				}
				if (Board.currentPlayer.government.name
						.equals("Fundamentalism")) {
					Board.currentPlayer.trade -= 2;
				}
				if (Board.currentPlayer.trade < 0) {
					Board.currentPlayer.trade = 0;
				}
			}
		} else if (this.currentPhase.equals(TRADE)) {
			if (Board.currentPlayer == Board.firstPlayer) {
				this.changePlayerTurn();
				for (City c : Board.currentPlayer.cities) {
					Board.currentPlayer.trade += c.calcTrade();
				}
			} else {
				this.changePlayerTurn();
				this.currentPhase = CITY_MANAGEMENT;
				for (City c : Board.currentPlayer.cities) {
					c.calcProduction();
					if (Board.currentPlayer.government.name.equals("Communism")) {
						c.setProduction(c.getProduction() + 2);
					}
				}
			}
		} else if (this.currentPhase.equals(CITY_MANAGEMENT)) {
			if (Board.currentPlayer == Board.firstPlayer) {
				for (City c : Board.currentPlayer.cities) {
					c.setHasAction(true);
				}
				this.changePlayerTurn();
				currentCity = null;
				for (City c : Board.currentPlayer.cities) {
					c.calcProduction();
					if (Board.currentPlayer.government.name.equals("Communism")) {
						c.setProduction(c.getProduction() + 2);
					}
				}
			} else {
				for (City c : Board.currentPlayer.cities) {
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
			if (Board.currentPlayer == Board.firstPlayer)
				this.changePlayerTurn();
			else {
				this.changePlayerTurn();
				this.currentPhase = RESEARCH;
				research();
			}

			for (Figure p1Figs : player1.figures)
				p1Figs.resetMoves(player1.getSpeed());
			for (Figure p2Figs : player2.figures)
				p2Figs.resetMoves(player2.getSpeed());

			this.validTiles.clear();
		} else {
			if (Board.currentPlayer == Board.firstPlayer) {
				this.changePlayerTurn();
				research();
			} else {
				if (Board.firstPlayer == Board.player1)
					Board.firstPlayer = Board.player2;
				else
					Board.firstPlayer = Board.player1;
				this.currentPhase = START_OF_TURN;
				
				Board.isGameOver();
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
		this.currentMovementFigure = Board.player1.figures.get(0);
	}

	// For testing...
	public void setCurrentMovementFigureMoves() {
		this.currentMovementFigure.decreaseMoves();
	}

	// For testing...
	public void resetValidTileList() {
		this.validTiles.clear();
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

	public static void isGameOver() {

		if (Board.isGameOver) {
			// the won militaristically
			winningWindow();
		}

		boolean isOver = false;

		for (Player p : Board.players) {

			if (p.gold >= 15) {
				p.winCondition = messages.getString("econ");
				p.hasWon = true;
			}

			if (p.hasWon == true) {
				isOver = true;
			}
		}
		if (!isOver) {
			return;
		}

		HashMap<Integer, Player> score = new HashMap<Integer, Player>();
		for (Player p : Board.players) {
			score.put(tieBreakerScore(p), p);
		}

		int highestScore = 0;
		for (Integer i : score.keySet()) {
			if (i > highestScore) {
				highestScore = i;
			}
		}

		if (!score.isEmpty()) {
			Board.isGameOver = true;
			Board.winners.add(score.get(highestScore));
		}

		if (Board.isGameOver) {
			winningWindow();
		}
	}

	private static void winningWindow() {
		//look at Board.winners;
		//display who won and how with Player.winCondition
		//exit game
		JFrame winWindow = buildFrame(500, 200, messages.getString("gameOver"));
		winWindow.setLayout(new GridLayout(2, 0));
		JPanel textPanel = buildJPanel(500, 200, 0, 0);
		JLabel text = new JLabel(messages.getString("gameOneWinner") + " Player has won due to " + Board.winners.get(0).winCondition);
		JButton close = new JButton(messages.getString("close"));
		if(Board.winners.size() == 2){
			text.setText("gameTie");
		}
		textPanel.add(text);
		winWindow.add(textPanel);
		winWindow.add(close);
		winWindow.setVisible(true);
		
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
	}

	public static int tieBreakerScore(Player p) {
		int score = 0;
		score += p.cultureTrackProgress;
		score += p.techCards.size();
		score += p.gold;
		return score;
	}

}