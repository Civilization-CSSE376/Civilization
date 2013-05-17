package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class MilitaryScience extends TechCard {

	private static ResourceBundle messages;
	
	public MilitaryScience(ResourceBundle messages) {
		super(messages.getString("militaryScience"));
		MilitaryScience.messages = messages;
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("academy"));

	}

}
