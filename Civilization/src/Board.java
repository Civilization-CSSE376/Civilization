import java.awt.Component;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel{
	
	private JPanel map = new JPanel();
	
//	private ArrayList<Panel> map;
	private ArrayList<Player> players;
	//private Market market;
	private Player firstPlayer;
	private Player currentPlayer;
	
	public Board(){
		
		
		Rectangle2D.Double rect = new Rectangle2D.Double(100, 100, 100, 100);
		this.map.add(new Panel());
	}
	
	
	
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
