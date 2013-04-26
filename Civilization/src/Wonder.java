import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.util.ResourceBundle;

public class Wonder extends Marker {

	public int tier = 0;

	public Wonder(String name, ResourceBundle messages) {
		super(name, messages);
		this.createWonder(name);
		this.allowedTerrain = Terrain.NotWater;
	}

	private void createWonder(String name) {
		switch (name) {
		case "Stonehenge":
			this.culture = 1;
			this.tier = 1;
			this.cost = 10;
			break;
		case "TheOracle":
			this.culture = 1;
			this.tier = 1;
			this.cost = 15; // 10 w/Code of Laws
			break;
		case "TheHangingGardens":
			this.culture = 1;
			this.tier = 1;
			this.cost = 15; // 10 w/Animal Husbandry
			break;
		case "TheColossus":
			this.culture = 1;
			this.tier = 1;
			this.cost = 15; // 10 w/Metalworking
			break;
		case "TheLouvre":
			this.culture = 2;
			this.tier = 2;
			this.cost = 20; // 15 w/Printing Press
			break;
		case "AngkorWat":
			this.culture = 2;
			this.tier = 2;
			this.cost = 20; // 10 w/Theology
			break;
		case "HimejiSamuraiCastle":
			this.culture = 2;
			this.tier = 2;
			this.cost = 20; // 15 w/Monarchy
			break;
		case "PorcelainTower":
			this.culture = 2;
			this.tier = 2;
			this.cost = 20; // 15 w/Construction
			break;
		case "StatueOfLiberty":
			this.culture = 3;
			this.tier = 3;
			this.cost = 25; // 20 w/Metal Casting
			break;
		case "SydneyOperaHouse":
			this.culture = 3;
			this.tier = 3;
			this.cost = 25;
			break;
		case "UnitedNations":
			this.culture = 3;
			this.tier = 3;
			this.cost = 20;
			break;
		case "PanamaCanal":
			this.culture = 3;
			this.tier = 3;
			this.cost = 25; // 20 w/Engineering
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
