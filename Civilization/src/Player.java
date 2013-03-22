import java.util.ArrayList;


public class Player {
	
	private enum Government{
		//fill me in
	}
	
	public ArrayList<Tile.Resource> resources;
	public ArrayList<Figure> figures;
	public ArrayList<City> cities;
	//private ArrayList<Unit> units;
	//private ArrayList<CultureCard> cultureCards;
	//private ArrayList<TechCard> techCards;
	//private ArrayList<WonderCard> wonderCards;
	private int culture = 0;
	private int gold = 0;
	private int speed = 2;
	private int handSize = 2;
	private int stackSize = 2;
	private int trade = 0;
	private int combatAdvantage = 0;
	
	public Player(){
		
	}
	
	private void setCapital(City capital){
		
		boolean alreadyHave = false;
		for(City city : this.cities){
			if(city.getisCapital()){
				
			}
		}
	}

}
