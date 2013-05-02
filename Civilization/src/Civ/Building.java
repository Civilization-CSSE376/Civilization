package Civ;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class Building extends Marker {

	private List<String> upgradedBuildings;

	public boolean isUpgraded = false;
	
	private ResourceBundle messages;

	public Building(String name, ResourceBundle messages) {
		super(name, messages);
		this.messages = messages;
		this.upgradedBuildings = Arrays.asList(messages.getString("bank"), messages.getString("cathedral"), messages.getString("aqueduct"), messages.getString("university"), messages.getString("academy"), messages.getString("ironMine"));
		this.isUpgraded = isItUpgraded(name);
		this.translateName(name);
		this.createBuilding(this.name, this.messages);
	}

	public boolean isValid(Tile tile, City city) {
		for (Tile t : city.getOutskirts()) {
			if (t.getMarker() instanceof Building && t.getMarker().hasStar) {
				if (this.hasStar)
					return false;
			}
		}

		if (this.allowedTerrain == Terrain.NotWater) {
			return tile.getTerrain() != Terrain.Water;
		} else {
			return this.allowedTerrain == tile.getTerrain();
		}
	}

	private boolean isItUpgraded(String name) {
		return this.upgradedBuildings.contains(name);
	}

	private void translateName(String name) {
		switch (name) {
		case "Bank":
			this.name = "Market";
			break;
		case "Cathedral":
			this.name = "Temple";
			break;
		case "Aqueduct":
			this.name = "Granary";
			break;
		case "University":
			this.name = "Library";
			break;
		case "Academy":
			this.name = "Barracks";
			break;
		case "Iron Mine":
			this.name = "Workshop";
			break;
		default:
			break;
		}
	}

	private void createBuilding(String name, ResourceBundle messages) {

		switch (name) {
		case "Market":
			this.culture = 1;
			this.production = 1;
			this.trade = 1;
			this.allowedTerrain = Terrain.NotWater;
			this.hasStar = true;
			this.cost = 7;

			if (this.isUpgraded) {
				this.coin = 1;
				this.cost = 10;
			}

			break;
		case "Temple":
			this.culture = 2;
			this.cost = 7;
			this.allowedTerrain = Terrain.NotWater;
			this.hasStar = true;

			if (this.isUpgraded) {
				this.culture = 3;
				this.cost = 10;
			}

			break;
		case "Granary":
			this.production = 1;
			this.trade = 1;
			this.cost = 5;
			this.allowedTerrain = Terrain.Grassland;
			this.hasStar = false;

			if (this.isUpgraded) {
				this.production = 2;
				this.trade = 2;
				this.cost = 8;
			}

			break;
		case "Library":
			this.culture = 1;
			this.trade = 1;
			this.cost = 5;
			this.allowedTerrain = Terrain.Grassland;
			this.hasStar = false;

			if (this.isUpgraded) {
				this.culture = 2;
				this.trade = 2;
				this.cost = 8;
			}

			break;
		case "Barracks":
			this.combatAdvantage = 2;
			this.trade = 2;
			this.hasStar = true;
			this.cost = 7;
			this.allowedTerrain = Terrain.NotWater;

			if (this.isUpgraded) {
				this.combatAdvantage = 4;
				this.cost = 10;
			}

			break;
		case "Workshop":
			this.production = 3;
			this.cost = 7;
			this.allowedTerrain = Terrain.Mountain;

			if (this.isUpgraded) {
				this.production = 4;
				this.cost = 10;
			}

			break;
		case "Trading Post":
			this.culture = 1;
			this.trade = 2;
			this.cost = 7;
			this.allowedTerrain = Terrain.Desert;
			break;
		case "Harbor":
			this.production = 1;
			this.trade = 2;
			this.cost = 7;
			this.allowedTerrain = Terrain.Water;
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		String filename = "src/buildings/" + this.name + ".png";
		try{
			BufferedImage buildingImage = ImageIO.read(new File(filename));
			g2.drawImage(buildingImage, (int)this.getScreenLocation().x - 42, (int)this.getScreenLocation().y - 42, null);
		}
		catch (IOException e){
			System.out.println("did not load building image correctly");
			e.printStackTrace();
		}
	}

}
