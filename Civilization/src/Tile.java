import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Tile {

	public enum Resource {
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
	private Resource resource = Resource.None;
	private Point screenLocation;

	// This will hold the abstract objects of figures such as scouts and armies.
	// It also contains the huts and villages

	private ArrayList<Figure> figures = new ArrayList<Figure>();

	private Marker marker = null; // this will hold the abstract object that
									// represents
	// cities, buildings, great people, etc.

	private City city = null;

	public Marker getMarker() {
		return marker;
	}

	public City getCity() {
		return city;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Tile() {

	}

	public Tile(int x, int y, String terrain, int trade, int production,
			String resource, int culture, String inhabitant, int coin) {
		this.xPos = x;
		this.yPos = y;
		this.trade = trade;
		this.production = production;
		this.culture = culture;
		this.coin = coin;

		this.setTerrain(terrain);

		if (resource.equals("W"))
			this.resource = Resource.Wheat;
		else if (resource.equals("S"))
			this.resource = Resource.Silk;
		else if (resource.equals("Ir"))
			this.resource = Resource.Iron;
		else if (resource.equals("In"))
			this.resource = Resource.Incense;
		else
			System.out.println("Error in Tile: wrong resource.");

		if (inhabitant.equals("H"))
			this.figures.add(new Settler(null, this));
		else if (inhabitant.equals("V"))
			this.figures.add(new Army(null, this));
		else
			System.out.println("Error in Tile: wrong inhabitant.");

	}

	public void setTerrain(String terrain) {
		if (terrain.equals("M"))
			this.terrain = Terrain.Mountain;
		else if (terrain.equals("F"))
			this.terrain = Terrain.Forest;
		else if (terrain.equals("G"))
			this.terrain = Terrain.Grassland;
		else if (terrain.equals("D"))
			this.terrain = Terrain.Desert;
		else if (terrain.equals("W"))
			this.terrain = Terrain.Water;
		else
			System.out.println("Error in Tile: wrong terrain.");

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

	public Resource getResource() {
		return this.resource;
	}

	public int getTrade() {
		return this.trade;
	}

	public int getCulture() {
		return this.culture;
	}

	public int getCoins() {
		return this.coin;
	}

	public int getProduction() {
		return this.production;
	}

	public ArrayList<Figure> getFigure() {
		return this.figures;
	}

	public ArrayList<Figure> getFigures() {
		return this.figures;
	}

}
