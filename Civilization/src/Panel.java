import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Panel {

	private Tile[][] tiles;
	private boolean isExplored;

	public Panel() {
		this.tiles = new Tile[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tiles[i][j] = new Tile(i, j);
			}
		}
		this.isExplored = false;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public boolean getIsExplored() {
		return this.isExplored;
	}

	public void changeIsExplored() {
		this.isExplored = !this.isExplored;
	}

	public void readFromFile(File file) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			int index = 0;
			while ((text = reader.readLine()) != null) {
				String[] stringTiles;
				stringTiles = text.split("_");

				System.out.println(String.format("i = %d, j = %d",
						(int) (Math.floor(index / 4)), (index % 4)));
				this.tiles[(int) (Math.floor(index / 4))][(index % 4)] = new Tile(
						stringTiles[0], Integer.parseInt(stringTiles[1]),
						Integer.parseInt(stringTiles[2]), stringTiles[3],
						Integer.parseInt(stringTiles[4]), stringTiles[5],
						Integer.parseInt(stringTiles[6]));

				System.out.println(text);
				System.out.println("  ");
				index++;
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
