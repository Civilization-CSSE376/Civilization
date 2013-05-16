package Civ;

import java.util.HashMap;

public class Panel {

	private Tile[][] tiles;
	private boolean isExplored;
	private HashMap<String, Panel> neighbors;
	

	public Panel() {
		this.tiles = new Tile[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tiles[i][j] = new Tile(i, j);
			}
		}
		this.isExplored = false;
	}
	
	public HashMap<String, Panel> getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors (HashMap<String, Panel> neighbors){
		this.neighbors = neighbors;
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

	
}
