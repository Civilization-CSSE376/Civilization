package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Writing extends TechCard {

	private static ResourceBundle messages;
	
	public Writing(ResourceBundle messages) {
		super(messages.getString("writing"));
		Writing.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("library"));

	}

}
