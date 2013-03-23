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
	
	private HashSet<Tile> getOutskirts(Tile startTile){
		Panel startPanel = Board.findPanel(startTile);
		
		Hashtable<String, Panel> neighbors = startPanel.getNeighbors();
		HashSet<Tile> outskirts = new HashSet<Tile>();
		
		Panel westPanel = neighbors.get("West");
		Panel eastPanel = neighbors.get("East");
		Panel southPanel = neighbors.get("South");
		Panel northPanel = neighbors.get("North");

		int startX = startTile.getxPos();
		int startY = startTile.getyPos();
		
		
		if(startX - 1 < 0 && startY - 1 < 0){
			//bottom left corner
			
			Panel southWestPanel = neighbors.get("South").getNeighbors().get("West");
			
			if(southPanel == null || westPanel == null || southWestPanel == null){
				return null;
			}
			
			//west panel
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY + 1]);
			
			//south panel
			outskirts.add(southPanel.getTiles()[startX][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX + 1][southPanel.getTiles()[0].length - 1]);
			
			//southWest panel
			outskirts.add(southWestPanel.getTiles()
					[southWestPanel.getTiles().length][southWestPanel.getTiles().length]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			
			
		}else if(startX + 2 > startPanel.getTiles().length && startY - 1 < 0){
			//bottom right corner
			
			Panel southEastPanel = neighbors.get("South").getNeighbors().get("East");
			
			if(southPanel == null || eastPanel == null || southEastPanel == null){
				return null;
			}
			
			//east panel
			outskirts.add(eastPanel.getTiles()[0][startY]);
			outskirts.add(eastPanel.getTiles()[0][startY + 1]);
			
			//south panel
			outskirts.add(southPanel.getTiles()[startX - 1][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX][southPanel.getTiles()[0].length - 1]);
			
			//southEast panel
			outskirts.add(southEastPanel.getTiles()[0][southEastPanel.getTiles().length]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
		}else if(startX - 1 < 0 && startY + 2 > startPanel.getTiles()[0].length){
			//top left corner
			
			Panel northWestPanel = neighbors.get("North").getNeighbors().get("West");
			
			if(northPanel == null || westPanel == null || northWestPanel == null){
				return null;
			}
			
			//west panel
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY - 1]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY]);
			
			//north panel	
			outskirts.add(northPanel.getTiles()[startX][0]);
			outskirts.add(northPanel.getTiles()[startX + 1][0]);
			
			//northWest panel
			outskirts.add(northWestPanel.getTiles()[northWestPanel.getTiles().length][0]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
		}else if(startX + 2 > startPanel.getTiles().length && startY + 2 > startPanel.getTiles()[0].length){
			//top right corner
			
			Panel northEastPanel = neighbors.get("North").getNeighbors().get("East");
			
			if(northPanel == null || eastPanel == null || northEastPanel == null){
				return null;
			}
			
			//east panel
			outskirts.add(eastPanel.getTiles()[0][startY - 1]);
			outskirts.add(eastPanel.getTiles()[0][startY]);
			
			//north panel	
			outskirts.add(northPanel.getTiles()[startX - 1][0]);
			outskirts.add(northPanel.getTiles()[startX][0]);
			
			//northEast panel
			outskirts.add(northEastPanel.getTiles()[0][0]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
		}else if(startX - 1 < 0){
			//west edge
			
			if(westPanel == null){
				return null;
			}
			
			//west panel
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY - 1]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY + 1]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			
		}else if(startX + 2 > startPanel.getTiles().length){
			//east edge
			
			if(eastPanel == null){
				return null;
			}
			
			//east panel
			outskirts.add(eastPanel.getTiles()[0][startY - 1]);
			outskirts.add(eastPanel.getTiles()[0][startY]);
			outskirts.add(eastPanel.getTiles()[0][startY + 1]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			
		}else if(startY - 1 < 0){
			//south edge
			
			if(southPanel == null){
				return null;
			}
			
			//south panel
			outskirts.add(southPanel.getTiles()[startX - 1][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX + 1][southPanel.getTiles()[0].length - 1]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
				
		}else if(startY + 2 > startPanel.getTiles()[0].length){
			//north edge
			
			if(northPanel == null){
				return null;
			}
			
			//north panel	
			outskirts.add(northPanel.getTiles()[startX - 1][0]);
			outskirts.add(northPanel.getTiles()[startX][0]);
			outskirts.add(northPanel.getTiles()[startX + 1][0]);
			
			//start panel
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
			
		}else{
			//start panel
			outskirts.add(startPanel.getTiles()[startX - 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY + 1]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY]);
			outskirts.add(startPanel.getTiles()[startX + 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY - 1]);
			outskirts.add(startPanel.getTiles()[startX - 1][startY]);
		}
		
		return outskirts;
	}
	
	private boolean validOutskirts(HashSet<Tile> outskirts){
		
		return false;
	}
	
}
