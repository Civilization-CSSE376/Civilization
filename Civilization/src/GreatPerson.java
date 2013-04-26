import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.util.ResourceBundle;

public class GreatPerson extends Marker {

	public boolean isInReserve = false; // I don't know if we need this but this
										// keeps
										// track of if the GP is on the board or
										// not

	public GreatPerson(String name, ResourceBundle messages) {
		super(name, messages);
		this.createGreatPerson(name);
		this.allowedTerrain = Terrain.NotWater;
	}

	private void createGreatPerson(String name) {
		switch (name) {
		case "Poet":
			this.trade = 1;
			this.culture = 2;
			break;
		case "Prophet":
			this.production = 1;
			this.culture = 1;
			this.trade = 1;
			this.coin = 1;
			break;
		case "Explorer":
			this.culture = 2;
			this.coin = 1;
			break;
		case "General":
			this.combatAdvantage = 4;
			break;
		case "Scientist":
			this.production = 1;
			this.trade = 2;
			break;
		case "Merchant":
			this.production = 2;
			this.coin = 1;
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		super.draw(g2, c);
	}

}
