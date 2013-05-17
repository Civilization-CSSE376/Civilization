package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Pottery extends TechCard {

	private static ResourceBundle messages;
	
	public Pottery(ResourceBundle messages) {
		super(messages.getString("pottery"));
		Pottery.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("granary"));

	}

}
