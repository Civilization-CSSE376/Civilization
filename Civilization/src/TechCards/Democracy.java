package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Democracy extends TechCard {

	private static ResourceBundle messages;
	
	public Democracy(ResourceBundle messages) {
		super(messages.getString("democracy"));
		Democracy.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add(messages.getString("democracy"));
		if(player.infantryLevel < 2){
			player.infantryLevel = 2;
		}

	}

}
