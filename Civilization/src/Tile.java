import java.util.Random;

public class Tile {

	private enum Terrain {
		Mountain, Forest, Grassland, Desert, Water
	};

	private enum Resource {
		Wheat, Silk, Incense, Iron, None
	}

	private int xPos;
	private int yPos;
	private boolean naturalWonder;
	private Terrain terrain;
	private int trade = 0;
	private int production = 0;
	private int culture = 0;
	private int coin = 0;
	private Resource resource;

	// private Marker marker this will hold the abstract object that represents
	// cities, buildings, great people, etc.
	// private ArrayList<Figures> figures This will hold the abstract objects of
	// figures such as scouts and armies

	public Tile() {

	}

	public Tile(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		
		Random randomValue = new Random();
		Terrain[] values = Terrain.values();
		this.terrain = values[randomValue.nextInt(5)];
	}

	public int getxPos() {
		return this.xPos;
	}

	public int getyPos() {
		return this.yPos;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

}
