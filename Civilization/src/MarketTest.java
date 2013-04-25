import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class MarketTest {

	@Test
	public void constructorTest() {
		Market target = new Market();
		assertNotNull(target);
	}
	
	@Test
	public void getWonderDeckTest(){
		Market target = new Market();
		assertEquals(12, target.getWonderDeck().size());
	}
	
	@Test
	public void buildWonderDeckTest(){
		boolean itFailed = false;
		ArrayList<String> ancient = new ArrayList<String>();
		ArrayList<String> medieval = new ArrayList<String>();
		ArrayList<String> modern = new ArrayList<String>();
		
		for(int i = 0; i < 50; i++){
			Market target = new Market();
			
			ancient.add(target.getWonderDeck().pop());
			ancient.add(target.getWonderDeck().pop());
			ancient.add(target.getWonderDeck().pop());
			ancient.add(target.getWonderDeck().pop());
			medieval.add(target.getWonderDeck().pop());
			medieval.add(target.getWonderDeck().pop());
			medieval.add(target.getWonderDeck().pop());
			medieval.add(target.getWonderDeck().pop());
			modern.add(target.getWonderDeck().pop());
			modern.add(target.getWonderDeck().pop());
			modern.add(target.getWonderDeck().pop());
			modern.add(target.getWonderDeck().pop());
			
			if(target.getWonderDeck().size() != 0){
				itFailed = true;
			}
			if(!ancient.contains("Stonehenge")
					||!ancient.contains("TheOracle")
					||!ancient.contains("TheHangingGardens")
					||!ancient.contains("TheColossus")){
				itFailed = true;
			}
			if(!medieval.contains("TheLouvre")
					||!medieval.contains("AngkorWat")
					||!medieval.contains("HimejiSamuraiCastle")
					||!medieval.contains("PorcelainTower")){
				itFailed = true;
			}
			if(!modern.contains("StatueOfLiberty")
					||!modern.contains("SydneyOperaHouse")
					||!modern.contains("UnitedNations")
					||!modern.contains("PanamaCanal")){
				itFailed = true;
			}
			
		}
		
		assertFalse(itFailed);
		
	}

}
