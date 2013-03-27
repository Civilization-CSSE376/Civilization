import java.util.ArrayList;
import java.util.HashMap;
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

	
	public City(Tile location){
		this.location = location;
	}
	
	public void setCapital(){
		this.isCapital = true;
	}
	
	public boolean getIsCapital(){
		return this.isCapital;
	}
	
	public void setHasAction(boolean b){
		this.hasAction = b;
	}
	
	
	/**
	 * Calculates the production of this city
	 * @return totalProduction
	 */
	private int calcProduction(){
		int totalProduction = 0;
		
		for(Tile t : this.outskirts){
			totalProduction += t.getProduction();
		}
		
		return totalProduction;
	}
	
	/**
	 * Calculates the culture of this city
	 * @return totalCulture
	 */
	private int calcCulture(){
		int totalCulture = 0;
		
		for(Tile t : this.outskirts){
			totalCulture += t.getCulture();
		}
		
		return totalCulture;
	}
	
	/**
	 * Calculates the trade of this city.
	 * @return totalTrade
	 */
	private int calcTrade(){
		int totalTrade = 0;
		
		for(Tile t : this.outskirts){
			totalTrade += t.getTrade();
		}
		
		return totalTrade;
	}
	
	/**
	 * Collects the 8 surrounding tiles that makeup a city's outskirts
	 * @param startTile -the location of the city
	 * @return the 8 tiles of the outskirts in a hashset structure. returns null
	 * 			if the outskirts contain a null tile
	 */
	private HashSet<Tile> getOutskirts(Tile startTile){
		Panel startPanel = Board.findPanel(startTile);
		
		HashMap<String, Panel> neighbors = startPanel.getNeighbors();
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
			
			if(!southPanel.getIsExplored() || !westPanel.getIsExplored() || !southWestPanel.getIsExplored()){
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
					[southWestPanel.getTiles().length - 1][southWestPanel.getTiles().length - 1]);
			
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
			
			if(!southPanel.getIsExplored() || !eastPanel.getIsExplored() || !southEastPanel.getIsExplored()){
				return null;
			}
			
			//east panel
			outskirts.add(eastPanel.getTiles()[0][startY]);
			outskirts.add(eastPanel.getTiles()[0][startY + 1]);
			
			//south panel
			outskirts.add(southPanel.getTiles()[startX - 1][southPanel.getTiles()[0].length - 1]);
			outskirts.add(southPanel.getTiles()[startX][southPanel.getTiles()[0].length - 1]);
			
			//southEast panel
			outskirts.add(southEastPanel.getTiles()[0][southEastPanel.getTiles().length - 1]);
			
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
			
			if(!northPanel.getIsExplored() || !westPanel.getIsExplored() || !northWestPanel.getIsExplored()){
				return null;
			}
			
			//west panel
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY - 1]);
			outskirts.add(westPanel.getTiles()[westPanel.getTiles().length - 1][startY]);
			
			//north panel	
			outskirts.add(northPanel.getTiles()[startX][0]);
			outskirts.add(northPanel.getTiles()[startX + 1][0]);
			
			//northWest panel
			outskirts.add(northWestPanel.getTiles()[northWestPanel.getTiles().length - 1][0]);
			
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
			
			if(!northPanel.getIsExplored() || !eastPanel.getIsExplored() || !northEastPanel.getIsExplored()){
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
			
			if(westPanel == null || !westPanel.getIsExplored()){
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
			
			if(eastPanel == null || !eastPanel.getIsExplored()){
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
			
			if(southPanel == null || !southPanel.getIsExplored()){
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
			
			if(northPanel == null || !northPanel.getIsExplored()){
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
			
			if(!startPanel.getIsExplored()){
				return null;
			}
			
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
	
	/**
	 * Determines whether a city and its outskirts are in a valid location
	 * @param buildingPlayer -the player wanting to build the city
	 * @param startTile -the location tile of the city
	 * @param outskirts -the 8 surrounding tiles of the city
	 * @return true if the startTile and all the tiles in the outskirts pass all restrictions else false
	 */
	private boolean validOutskirts(Player buildingPlayer, Tile startTile, HashSet<Tile> outskirts){
		
		ArrayList<Figure> enemyFigures = new ArrayList<Figure>();
		ArrayList<Player> enemyPlayers = new ArrayList<Player>();
		ArrayList<City> enemyCities = new ArrayList<City>();
		ArrayList<Tile> enemyOutskirts = new ArrayList<Tile>();
		HashSet<Tile> cityTiles = new HashSet<Tile>(outskirts);
		cityTiles.add(startTile);
		
		for(Player p : Board.players){
			if(!p.equals(buildingPlayer)){
				enemyFigures.addAll(p.figures);
				enemyPlayers.add(p);
			}
		}
		
		for(Figure f: enemyFigures){
			if(cityTiles.contains(f.location)){
				return false;
			}
		}
		
		for(Player p : enemyPlayers){
			enemyCities.addAll(p.cities);
		}
		
		for(City c : enemyCities){
			enemyOutskirts.addAll(c.getOutskirts(c.location));
		}
		
		//water test
		if(startTile.getTerrain().equals(Tile.Terrain.Water)){
			return false;
		}
		
		
		for(Tile t : cityTiles){
			
			//village test
//			if(t.getFigure().contains(Village)){
//				return false;
//			}
			
			//hut test
//			if(t.getFigure().contains(Hut)){
//				return false;
//			}
			
			//outskirts test
			if(enemyOutskirts.contains(t)){
				return false;
			}
		}
		
		return true;
	}

	public void setOutskirts(ArrayList<Tile> outskirts) {
		this.outskirts = outskirts;
	}
	
}
