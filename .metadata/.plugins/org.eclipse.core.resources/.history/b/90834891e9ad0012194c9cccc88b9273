import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public abstract class Marker {

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

	public boolean isValid(Tile tile) {
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

}
