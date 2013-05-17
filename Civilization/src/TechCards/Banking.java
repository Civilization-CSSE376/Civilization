package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Banking extends TechCard {

	private static ResourceBundle messages;
	
	public Banking(ResourceBundle messages) {
		super(messages.getString("banking"));
		Banking.messages = messages;
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("bank"));

	}

}