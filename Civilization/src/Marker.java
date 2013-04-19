import java.util.ArrayList;



public abstract class Marker {
	
	protected boolean hasStar = false;
	protected int cost = 0;
	protected int trade = 0;
	protected int production = 0;
	protected int culture = 0;
	protected int coin = 0;
	protected int combatAdvantage = 0;
	protected String name = "";
	protected Terrain allowedTerrain = null;
	
	public Marker(String name){
		this.name = name;
	}
	
	public boolean isValid(Tile tile){
		if(this.allowedTerrain == Terrain.NotWater){
			return tile.getTerrain() != Terrain.Water;
		}else{
			return this.allowedTerrain == tile.getTerrain();
		}
	}
	
}
