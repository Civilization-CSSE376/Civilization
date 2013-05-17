package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Navigation extends TechCard {

	private static ResourceBundle messages;
	
	public Navigation(ResourceBundle messages) {
		super(messages.getString("navigation"));
		Navigation.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("harbor"));

	}

}
