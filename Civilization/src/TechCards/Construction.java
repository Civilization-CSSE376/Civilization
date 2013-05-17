package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Construction extends TechCard {

	private static ResourceBundle messages;
	
	public Construction(ResourceBundle messages) {
		super(messages.getString("construction"));
		Construction.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("workshop"));

	}

}
