import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel{

	private Tile[][] tiles;
	private boolean isExplored;
	private Hashtable<String, Panel> neighbors;
	

	public Panel() {
		this.tiles = new Tile[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tiles[i][j] = new Tile(i, j);
			}
		}
		this.isExplored = false;
	}
	
	public Hashtable<String, Panel> getNeighbors() {
		return neighbors;
	}

	public void paintComponent(Graphics2D g2){
		super.paintComponent(g2);
		Rectangle2D.Double rect = new Rectangle2D.Double(100, 100, 100, 100);
		g2.draw(rect);
	}
	
	public void setNeighbors (Hashtable<String, Panel> neighbors){
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
