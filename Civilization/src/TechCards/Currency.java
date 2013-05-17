package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Currency extends TechCard {

	private static ResourceBundle messages;
	
	public Currency(ResourceBundle messages) {
		super(messages.getString("currency"));
		Currency.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("market"));
	}

}
