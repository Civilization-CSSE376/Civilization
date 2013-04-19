import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
	private Point2D.Double screenLocation;
	
	public boolean isValid = false;
	
	public City(Tile location){
		this.location = location;
	}
	
	public ArrayList<Tile> getOutskirts(){
		return this.outskirts;
	}

	
	public City(Tile location, Player player){
		this.location = location;
		this.outskirts = this.getOutskirts(location);
		if(this.outskirts != null)
			this.isValid = this.validOutskirts(player);
	}
	
	public int getProduction(){
		return this.production;
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
	
	public Point2D.Double getLocation(){
		return this.screenLocation;//new Point2D.Double(this.location.getxPos(), this.location.getyPos());
	}
	
	
	/**
	 * Calculates the production of this city
	 * @return totalProduction
	 */
	int calcProduction(){
		int totalProduction = 0;
		
		for(Tile t : this.outskirts){
			totalProduction += t.getProduction();
		}
		this.production = totalProduction;
		return totalProduction;
	}
	
	/**
	 * Calculates the culture of this city
	 * @return totalCulture
	 */
	int calcCulture(){
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
	int calcTrade(){
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
	private ArrayList<Tile> getOutskirts(Tile startTile){//written assuming typical plot (x,y) rather than array (row, column) 
		Panel startPanel = Board.findPanel(startTile);//so every direction has to be replaced with the clockwise direction
		
		HashMap<String, Panel> neighbors = startPanel.getNeighbors();
		ArrayList<Tile> outskirts = new ArrayList<Tile>();
		
		Panel westPanel = new Panel();
		Panel eastPanel = new Panel();
		Panel southPanel = new Panel();
		Panel northPanel = new Panel();
		
		try{
			westPanel = neighbors.get("North");
		} catch (NullPointerException e){
			westPanel = null;
		}
		try{
			eastPanel = neighbors.get("South");
			} catch (NullPointerException e){
				eastPanel = null;
			}
		try{
			southPanel = neighbors.get("West");
			} catch (NullPointerException e){
				southPanel = null;
			}
		try{
			northPanel = neighbors.get("East");
			} catch (NullPointerException e){
				northPanel = null;
			}

		int startX = startTile.getxPos();
		int startY = startTile.getyPos();
		
		
		if(startX - 1 < 0 && startY - 1 < 0){
			//bottom left corner
			
			Panel southWestPanel = new Panel();
					
			try{
				southWestPanel = neighbors.get("West").getNeighbors().get("North");
			}catch (NullPointerException e){
				southWestPanel = null;
			}
			
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
			
			Panel southEastPanel = new Panel();
					try{
						southEastPanel = neighbors.get("West").getNeighbors().get("South");
					}catch (NullPointerException e){
						southEastPanel = null;
					}
			
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
			
			Panel northWestPanel = new Panel();
					try{
						northWestPanel = neighbors.get("East").getNeighbors().get("North");
					}catch (NullPointerException e){
						northWestPanel = null;
					}
			
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
			
			Panel northEastPanel = new Panel();
					try{
						northEastPanel = neighbors.get("East").getNeighbors().get("South");
					}catch (NullPointerException e){
						northEastPanel = null;
					}
			
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
	private boolean validOutskirts(Player buildingPlayer){
		
		ArrayList<Figure> enemyFigures = new ArrayList<Figure>();
		ArrayList<Player> enemyPlayers = new ArrayList<Player>();
		ArrayList<City> enemyCities = new ArrayList<City>();
		ArrayList<Tile> enemyOutskirts = new ArrayList<Tile>();
		HashSet<Tile> cityTiles = new HashSet<Tile>(this.outskirts);
		cityTiles.add(this.location);
		
		for(Player p : Board.players){
			
			enemyPlayers.add(p);
			if(!p.equals(buildingPlayer)){
				enemyFigures.addAll(p.figures);
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
		if(this.location.getTerrain().equals(Terrain.Water)){
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

	public void setScreenLocation(Point2D.Double screen){
		this.screenLocation = screen;
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
			this.screenLocation = new Point2D.Double(newX, newY);
		} else
			System.out.println("\nInvalid location -- cannot move player.");
	}


	public boolean getHasAction() {
		return this.hasAction;
	}
	
}
