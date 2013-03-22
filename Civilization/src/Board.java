import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private File file = new File("src/Panel1.txt");
	private int x;
	private int y;

	public ArrayList<Panel> map;
	private ArrayList<Player> players;
	// private Market market;
	private Player firstPlayer;
	private Player currentPlayer;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Rectangle2D.Double rect1 = new Rectangle2D.Double(20, 20, 440, 440);
		// Rectangle2D.Double rect2 = new Rectangle2D.Double(900, 20, 440, 440);
		// Rectangle2D.Double rect3 = new Rectangle2D.Double(20, 460, 440, 440);
		// Rectangle2D.Double rect4 = new Rectangle2D.Double(900, 460, 440,
		// 440);
		// g2.setColor(Color.BLACK);
		// g2.fill(rect1);
		// g2.fill(rect2);
		// g2.fill(rect3);
		// g2.fill(rect4);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					Color rectColor = Color.RED;
					switch (this.map.get(i).getTiles()[j][k].getTerrain()
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

					Rectangle2D.Double rect = new Rectangle2D.Double(
							20 + (440 * i) + (110 * j), 20 + (110 * k), 110, 110);
					g2.setColor(rectColor);
					g2.fill(rect);
				}
			}
		}

	}

	public Board() {
		this.map = new ArrayList<Panel>();

		this.x = 275;
		this.y = 275;
		readFromFile(this.file);
		// System.out.println(this.map.get(0).getTiles()[0][0].getTerrain());
	}

	public Board(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Board(ArrayList<String> civilizations) {
		for (String civ : civilizations) {
			players.add(playerConfig(civ));
		}
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

					tiles[(int) (Math.floor(index / 4))][(index % 4)] = new Tile(
							(int) (Math.floor(index / 4)), (index % 4),
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
}
