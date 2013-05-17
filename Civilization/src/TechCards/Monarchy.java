package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Monarchy extends TechCard {

	private static ResourceBundle messages;
	
	public Monarchy(ResourceBundle messages) {
		super(messages.getString("monarchy"));
		Monarchy.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add(messages.getString("monarchy"));

	}

}
