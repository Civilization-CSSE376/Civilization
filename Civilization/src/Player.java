import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player {

	private Point2D.Double location;

	// private enum Government {
	// // fill me in
	// }

	public ArrayList<Tile.Resource> resources = new ArrayList<Tile.Resource>();
	public ArrayList<Figure> figures = new ArrayList<Figure>();
	public ArrayList<City> cities = new ArrayList<City>();
	public ArrayList<Unit> units = new ArrayList<Unit>();
	// private ArrayList<CultureCard> cultureCards;
	// private ArrayList<TechCard> techCards;
	// private ArrayList<WonderCard> wonderCards;
	public int cityLimit = 2;
	private int culture = 0;
	private int gold = 0;
	private int speed = 22;
	private int handSize = 2;
	private int stackSize = 2;
	int trade = 0;
	private int combatAdvantage = 0;

	public Player() {
		this.location = new Point2D.Double(55, 55);
		this.units.add(new Unit("Infantry", 1));
		this.units.add(new Unit("Cavalry", 1));
		this.units.add(new Unit("Artillery", 1));

	}

	private void setCapital(City capital) {

		boolean alreadyHave = false;

		for (City city : this.cities) {
			if (city.getIsCapital()) {

			}
		}

	}

	// Move this to the separate unit classes.
	public Point2D.Double getLocation() {
		return this.location;
	}

	public void setLocation(int x, int y) {
		int newX = 0;
		int newY = 0;
		int checkLocation2 = 220;

		if (x >= 0 && x <= 1760 && y >= 0 && y <= 880) {

			if (x <= 110)
				newX = 55;

			while (newX == 0) {
				if (x <= checkLocation2)
					newX = checkLocation2 - 55;
				else
					checkLocation2 += 110;
			}

			if (y <= 110)
				newY = 55;

			checkLocation2 = 220;
			while (newY == 0) {
				if (y <= checkLocation2)
					newY = checkLocation2 - 55;
				else
					checkLocation2 += 110;
			}
			this.location = new Point2D.Double(newX, newY);
		} else
			System.out.println("\nInvalid location -- cannot move player.");

	}

	public int getSpeed() {
		return this.speed;
	}
	
	public ArrayList<Tile.Resource> getPlayerResources(){
		return this.resources;
	}
	
	public int getPlayerGold(){
		return this.gold;
	}
	
	public int getPlayerTrade(){
		return this.trade;
	}
	
	public int getPlayerCulture(){
		return this.culture;
	}

}
