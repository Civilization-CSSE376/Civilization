package Civ;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

public class Settler extends Figure {

	/*
	 * public Settler(Tile tile) { this.location = tile; }
	 */

	public Settler(Player player, Tile local) {
		super(player, local);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean tryToBuildCity(Tile tile, Player player, City city) {
		if (city.isValid
				&& player.cities.size() + 1 <= player.cityLimit) {
			city.setScreenLocation(this.screenLocation);
			player.cities.add(city);
			tile.setCity(city);
			player.figures.remove(this);
			tile.getFigure().remove(this);
			return true;
		}
		else return false;
	}

	@Override
	public void takeHut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		super.draw(g2, c);
		g2.drawString("S", (float) this.getLocation().x,
				(float) this.getLocation().y);
	}

}
