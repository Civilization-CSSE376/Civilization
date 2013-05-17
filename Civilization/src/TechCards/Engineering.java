package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Engineering extends TechCard {

	private static ResourceBundle messages;
	
	public Engineering(ResourceBundle messages) {
		super(messages.getString("engineering"));
		Engineering.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("aqueduct"));

	}

}
