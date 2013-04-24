import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;



public class Market {
	
	private static final int MARKET_QUANTITY = 5;
	private static final int TEMPLE_QUANTITY = 5;
	private static final int GRANARY_QUANTITY = 6;
	private static final int LIBRARY_QUANTITY = 6;
	private static final int BARRACKS_QUANTITY = 5;
	private static final int WORKSHOP_QUANTITY = 6;
	private static final int TRADINGPOST_QUANTITY = 6;
	private static final int HARBOR_QUANTITY = 10;
	
	private static List<String> ancientWonders = 
			Arrays.asList("Stonehenge", "TheOracle", "TheHangingGardens", "TheColossus");
	private static List<String> medievalWonders =
			Arrays.asList("TheLouvre", "AngkorWat", "HimejiSamuraiCastle", "PorcelainTower");
	private static List<String> modernWonders =
			Arrays.asList("StatueOfLiberty", "SydneyOperaHouse", "UnitedNations", "PanamaCanal");
	
	private Stack<String> wonderDeck;
	
	/*
	 * 0 = start
	 * 1 = culture card level 1
	 * 2 = culture card level 2
	 * 3 = culture card level 3
	 * -8 = great person
	 * 9001 = victory 
	 */
	private static final int[] cultureTrack =
		{0,1,1,-8,1,1,1,-8,2,2,2,2,-8,2,2,3,3,3,-8,3,3,9001};
	
	
	public Market(){
		
		this.buildWonderDeck();
		
	}
	
	private void buildWonderDeck(){
		Collections.shuffle(this.ancientWonders);
		Collections.shuffle(this.medievalWonders);
		Collections.shuffle(this.modernWonders);
		for(String s : this.modernWonders){
			this.wonderDeck.add(s);
		}
		for(String s : this.medievalWonders){
			this.wonderDeck.add(s);
		}
		for(String s : this.ancientWonders){
			this.wonderDeck.add(s);
		}
	}
	
}
