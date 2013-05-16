package Civ;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Player {

	private Point2D.Double location;

	public ArrayList<Tile.Resource> resources = new ArrayList<Tile.Resource>();
	public ArrayList<Figure> figures = new ArrayList<Figure>();
	public ArrayList<City> cities = new ArrayList<City>();
	public ArrayList<Unit> units = new ArrayList<Unit>();
	// private ArrayList<CultureCard> cultureCards;
	public ArrayList<TechCard> techCards = new ArrayList<TechCard>();
	// private ArrayList<WonderCard> wonderCards;
	public ArrayList<GreatPerson> greatPeople = new ArrayList<GreatPerson>();
	public int cityLimit = 2;
	public int culture = 0;
	public int gold = 0;
	public int speed = 22;
	public int handSize = 2;
	public int stackSize = 2;
	public int battleHandSize = 3;
	public int trade = 0;
	public int combatAdvantage = 0;
	public int cultureTrackProgress = 0; 
	public boolean canBuyTier2TechCard = false;
	public boolean canBuyTier3TechCard = false;
	public boolean canBuyTier4TechCard = false;
	public boolean canBuyTier5TechCard = false;
	public int tier1Cards = 0;
	public int tier2Cards = 0;
	public int tier3Cards = 0;
	public int tier4Cards = 0;
	public boolean hasWon = false;
	public String winCondition = "";

	public int artilleryLevel = 1;
	public int infantryLevel = 1;
	public int cavalryLevel = 1;
	public int airplaneLevel = 1;
	public ArrayList<String> unlockedBuildings = new ArrayList<String>();
	public ArrayList<String> unlockedGovernments = new ArrayList<String>();
	public Government government;
	private static ResourceBundle messages;

	public Player(ResourceBundle messages) {
		Player.messages = messages;
		this.location = new Point2D.Double(55, 55);
		this.units.add(new Unit(messages.getString("infantry"), 1, messages));
		this.units.add(new Unit(messages.getString("cavalry"), 1, messages));
		this.units.add(new Unit(messages.getString("artillery"), 1, messages));
		this.government =  new Government(this);

	}

	public void setCapital(City capital) {

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
		} 
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
	
	public int getPlayerCombatAdvantage(){
		int temp = 0;
		for(City c : this.cities){
			for(Tile t : c.getOutskirts()){
				temp += t.getCombatAdvantage();
			}
		}
		return temp;
	}
	
	public int getResourceAmount(String type){
		int amount = 0;
		for(Tile.Resource r : this.resources){
			if(r.toString().equals(type)){
				amount++;
			}
		}
		return amount;
	}

}
