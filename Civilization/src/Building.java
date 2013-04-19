import java.util.Arrays;
import java.util.List;

public class Building extends Marker {

	private List<String> upgradedBuildings = Arrays.asList("Bank", "Cathedral",
			"Aqueduct", "University", "Academy", "IronMine");

	public boolean isUpgraded = false;

	public Building(String name) {
		super(name);
		this.isUpgraded = isItUpgraded(name);
		this.translateName(name);
		this.createBuilding(this.name);
	}

	public boolean isValid(Tile tile, City city) {
		for (Tile t : city.getOutskirts()) {
			if (t.getMarker() instanceof Building && t.getMarker().hasStar) {
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
	
	private void translateName(String name){
		switch (name){
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
		case "IronMine":
			this.name = "Workshop";
			break;
		default:
			break;
		}
	}

	private void createBuilding(String name) {
		
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
		case "TradingPost":
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

}
