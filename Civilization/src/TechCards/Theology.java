package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Theology extends TechCard {

	private static ResourceBundle messages;
	
	public Theology(ResourceBundle messages) {
		super(messages.getString("theology"));
		Theology.messages = messages;
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.handSize += 1;
		player.unlockedBuildings.add(messages.getString("cathedral"));
		player.unlockedGovernments.add(messages.getString("fundamentalism"));

	}

}
