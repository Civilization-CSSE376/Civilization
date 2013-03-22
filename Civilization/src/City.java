import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;


public class City {
	
	private ArrayList<Tile> outskirts;
	private Tile location;
	private boolean isCapital;
	private boolean hasAction = true;
	private int production = 0;
	private int culture = 0;
	private int trade = 0;

	
	public City(Tile location, ArrayList<Tile> outskirts){
		this.location = location;
		this.outskirts = outskirts;
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
	
	private HashSet<Tile> getOutskirts(Tile startTile){ //move to City probably
		Panel startPanel = Board.findPanel(startTile);
		Hashtable<String, Panel> neighbors = startPanel.getNeighbors();
		int startX = startTile.getxPos();
		int startY = startTile.getyPos();
		HashSet<Tile> outskirts = new HashSet<Tile>();
		
		if(startX - 1 < 0 && startY - 1 < 0){
			//bottom left corner
		}
		
		if(startX + 2 > startPanel.getTiles().length && startY - 1 < 0){
			//bottom right corner
		}
		
		if(startX - 1 < 0 && startY + 2 > startPanel.getTiles()[0].length){
			//top left corner
		}
		
		if(startX + 2 > startPanel.getTiles().length && startY + 2 > startPanel.getTiles()[0].length){
			//top right corner
		}
		
		if(startX - 1 < 0){
			//need west panel
			Panel westPanel = neighbors.get("West");
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY - 1]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY + 1]);
			
		}else{
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
		}
		if(startX + 2 > startPanel.getTiles().length){ //tricky boundaries with .length CHECK
			//need east panel
			Panel eastPanel = neighbors.get("East");
			outskirts.add(eastPanel.getTiles()[0][startY - 1]);
			outskirts.add(eastPanel.getTiles()[0][startY]);
			outskirts.add(eastPanel.getTiles()[0][startY + 1]);
			
		}else{
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
		}
		if(startY - 1 < 0){
			//need south panel
			Panel southPanel = neighbors.get("South");
			outskirts.add(southPanel.getTiles()[startX - 1][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX + 1][southPanel.getTiles()[0].length - 1]);
				
		}else{
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
		}
		if(startY + 2 > startPanel.getTiles()[0].length){ //tricky boundaries with .length CHECK
			//need north panel	
			Panel northPanel = neighbors.get("North");
			outskirts.add(northPanel.getTiles()[startX - 1][0]);
			outskirts.add(northPanel.getTiles()[startX][0]);
			outskirts.add(northPanel.getTiles()[startX + 1][0]);
			
		}else{
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
		}
		
		return null;
	}
	
	private boolean validOutskirts(HashSet<Tile> outskirts){
		return false;
	}
	
}
