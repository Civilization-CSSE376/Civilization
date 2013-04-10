import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player {

	private Point2D.Double location;

//	private enum Government {
//		// fill me in
//	}

	public ArrayList<Tile.Resource> resources = new ArrayList<Tile.Resource>();
	public ArrayList<Figure> figures = new ArrayList<Figure>();
	public ArrayList<City> cities = new ArrayList<City>();
	// private ArrayList<Unit> units;
	// private ArrayList<CultureCard> cultureCards;
	// private ArrayList<TechCard> techCards;
	// private ArrayList<WonderCard> wonderCards;
	private int culture = 0;
	private int gold = 0;
	private int speed = 2;
	private int handSize = 2;
	private int stackSize = 2;
	private int trade = 0;
	private int combatAdvantage = 0;
	private int totalMoves = 2;
	private int moves = totalMoves;

	public Player() {
		this.location = new Point2D.Double(55, 55);
		
	}
	

	private void setCapital(City capital) {

		boolean alreadyHave = false;

		for(City city : this.cities){
			if(city.getIsCapital()){
				
			}
		}
				
	}
	
	// Move this to the separate unit classes.
	public Point2D.Double getLocation() {
		return this.location;
	}
	
	public int getNumberOfMoves(){
		return this.moves;
	}
	
	public void setNumberMoves(int number){
		this.totalMoves = number;
	}
	
	public void resetMoves(){
		this.moves = this.totalMoves;
	}
	
	public void decreaseMoves(){
		this.moves -= 1;
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

}
