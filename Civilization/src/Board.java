import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private File file = new File("src/Panel1.txt");
	private Player player1;
	private Player player2;


	public static ArrayList<Panel> map;
	private ArrayList<Player> players;
	// private Market market;
	private Player firstPlayer;
	private Player currentPlayer;
	private int phase;

	public Board() {
		map = new ArrayList<Panel>();
		readFromFile(this.file);

		this.player1 = new Player();
		this.player2 = new Player();
		this.player2.setLocation(1, 1);
		

		this.firstPlayer = player1;
		this.currentPlayer = player1;
		this.phase = 1;

		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);
	}
	
	private void checkUnexploredPanel(int x, int y){

		if(x >= 440 && x < 880 && y >= 0 && y < 440) { // Panel 2
			if(!map.get(1).getIsExplored()) map.get(1).changeIsExplored();
		}
		else if(x >= 880 && x < 1320 && y >= 0 && y < 440){ // Panel 3
			if(!map.get(2).getIsExplored()) map.get(2).changeIsExplored();
		}
		else if(x >= 1320 && x <= 1760 && y >= 0 && y < 440) { // Panel 4 (top right)
			if(!map.get(3).getIsExplored()) map.get(3).changeIsExplored();
		}
		else if(x >= 0 && x < 440 && y >= 440 && y <= 880) { // Panel 5 (bottom left)
			if(!map.get(4).getIsExplored()) map.get(4).changeIsExplored();
		}
		else if(x >= 440 && x < 880 && y >= 440 && y <= 880) { // Panel 6
			if(!map.get(5).getIsExplored()) map.get(5).changeIsExplored();
		}
		else if(x >= 880 && x < 1320 && y >= 440 && y <= 880) { // Panel 7
			if(!map.get(6).getIsExplored()) map.get(6).changeIsExplored();
		}
		else
			System.out.println("Error.");			
	}
	
	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			 System.out.printf("\nMouse clicked at %d, %d", x, y);
			Board.this.currentPlayer.setLocation(x, y);
			checkUnexploredPanel(x, y);
			Board.this.repaint();

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
		return phase;
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
							Integer.parseInt(stringTiles[6]), map.size());

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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					Color rectColor = Color.RED;
					if(map.get(i).getIsExplored()){
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
						Rectangle2D.Double rect = new Rectangle2D.Double((440 * (i - 4)) + (110 * j),
								440 + (110 * k), 110, 110);
						g2.setColor(rectColor);
						g2.fill(rect);
						g2.setColor(Color.WHITE);
						g2.draw(rect);
					} else {
						Rectangle2D.Double rect = new Rectangle2D.Double((440 * i) + (110 * j), (110 * k), 110,
								110);
						g2.setColor(rectColor);
						g2.fill(rect);
						g2.setColor(Color.WHITE);
						g2.draw(rect);
					}
					// System.out.println("Tile[" + j + "][" + k +
					// "] created at location " + (20 + (440 * i) + (110 * j)) +
					// " " + (20 + nextRow + (110 * k)));
					}
					else{
						if (i > 3) {
							Rectangle2D.Double rect = new Rectangle2D.Double((440 * (i - 4)) + (110 * j),
									440 + (110 * k), 110, 110);
							g2.setColor(Color.BLACK);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						} else {
							Rectangle2D.Double rect = new Rectangle2D.Double((440 * i) + (110 * j), (110 * k), 110,
									110);
							g2.setColor(Color.BLACK);
							g2.fill(rect);
							g2.setColor(Color.WHITE);
							g2.draw(rect);
						}
					}
				}
			}
		}
		
		
		Ellipse2D.Double player1 = new Ellipse2D.Double(
				this.player1.getLocation().x - 25,
				this.player1.getLocation().y - 25, 50, 50);
		g2.setColor(Color.RED);
		g2.fill(player1);
		
		Ellipse2D.Double player2 = new Ellipse2D.Double(
				this.player2.getLocation().x - 25,
				this.player2.getLocation().y - 25, 50, 50);
		g2.setColor(Color.ORANGE);
		g2.fill(player2);

//		System.out.println("Player drawn at " + (this.location.x - 25) + ", " + (this.location.y - 25));

	}

	public void endPhase() {

		if (this.currentPlayer != this.firstPlayer) {
			if (this.phase < 5) {
				if (this.currentPlayer.equals(this.player1)) {
					this.currentPlayer = player2;
				} else {
					this.currentPlayer = player1;
				}
				phase++;
			} else {
				phase = 1;
				this.firstPlayer = this.currentPlayer;
			}
		} else {
			if (this.currentPlayer.equals(this.player1)) {
				this.currentPlayer = player2;
			} else {
				this.currentPlayer = player1;
			}
		}

	}
}
