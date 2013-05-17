package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Philosophy extends TechCard {

	private static ResourceBundle messages;
	
	public Philosophy(ResourceBundle messages) {
		super(messages.getString("philosophy"));
		Philosophy.messages = messages;
		this.tier = 1;
		
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("temple"));
		
	}

}
