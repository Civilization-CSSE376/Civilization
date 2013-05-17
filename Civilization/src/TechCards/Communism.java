package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Communism extends TechCard {

	private static ResourceBundle messages;
	
	public Communism(ResourceBundle messages) {
		super(messages.getString("communism"));
		Communism.messages = messages;
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add(messages.getString("communism"));

	}

}
