import java.util.ArrayList;

public class Board {
	
	private ArrayList<Panel> map;
	private ArrayList<Player> players;
	//private Market market;
	private Player firstPlayer;
	private Player currentPlayer;
	
	public Board(ArrayList<String> civilizations){
		for(String civ : civilizations){
			players.add(playerConfig(civ));
		}
	}
	
	private Player playerConfig(String civ){
		
		switch(civ){
		case "Egypt": 
			break;
		case "Russia":
			break;
		case "Rome":
			break;
		case "America":
			break;
		case "Germany":
			break;
		case "China":
			break;
		default:
			break;
		}
		
		return null;
	}
	
	
}
