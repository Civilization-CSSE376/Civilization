import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public abstract class Marker implements Drawable {

	protected boolean hasStar = false;
	protected int cost = 0;
	protected int trade = 0;
	protected int production = 0;
	protected int culture = 0;
	protected int coin = 0;
	protected int combatAdvantage = 0;
	protected String name = "";
	protected Terrain allowedTerrain = null;
	protected Tile location;
	private Point2D.Double screenLocation;

	public Marker(String name) {
		this.name = name;
	}

	public boolean isValid(Tile tile, City city) {
		if (this.allowedTerrain == Terrain.NotWater) {
			return tile.getTerrain() != Terrain.Water;
		} else {
			return this.allowedTerrain == tile.getTerrain();
		}
	}

	public void setTileLocal(Tile tile) {
		this.location = tile;
	}

	public void setScreenLocation(Double screenLocation) {
		this.screenLocation = screenLocation;
	}

	public Point2D.Double getScreenLocation() {
		return new Point2D.Double(this.screenLocation.x + 55,
				this.screenLocation.y + 55);
	}

	public Tile getLocation() {
		return this.location;
	}

	public int getCost() {
		return this.cost;
	}

	public static Marker makeMarker(String markerType, String marker) {
		if (markerType.equals("Building"))
			return new Building(marker);
		else if (markerType.equals("Wonder"))
			return new Wonder(marker);
		else if (markerType.equals("GreatPerson"))
			return new GreatPerson(marker);
		else
			return null;
	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		Rectangle2D.Double marker = new Rectangle2D.Double(
				this.getScreenLocation().x - 25,
				this.getScreenLocation().y - 25, 50, 50);
		g2.setColor(c);
		g2.draw(marker);

	}

}
