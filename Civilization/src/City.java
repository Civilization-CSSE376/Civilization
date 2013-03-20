import java.util.ArrayList;


public class City {
	
	private ArrayList<Tile> outskirts;
	private Tile location;
	private boolean isCapital;
	private boolean hasAction = true;
	private int production = 0;
	private int culture = 0;
	private int trade = 0;

	
	public City(Tile location){
		this.location = location;
	}
	
	public void setCapital(){
		this.isCapital = true;
	}
	
	public boolean getisCapital(){
		return this.isCapital;
	}
	
	
	/**
	 * Calculates the production of this city
	 * @return production
	 */
	private int calcProduction(){
		return 0;
	}
	
	/**
	 * Calculates the culture of this city
	 * @return culture
	 */
	private int calcCulture(){
		return 0;
	}
	
	/**
	 * Calculates the trade of this city.
	 * @return trade
	 */
	private int calcTrade(){
		return 0;
	}
	
}
