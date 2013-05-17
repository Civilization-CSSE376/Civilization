package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Railroad extends TechCard {

	private static ResourceBundle messages;
	
	public Railroad(ResourceBundle messages) {
		super(messages.getString("railroad"));
		Railroad.messages = messages;
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("ironMine"));
		if(player.cavalryLevel < 3){
			player.cavalryLevel = 3;
		}

	}

}
