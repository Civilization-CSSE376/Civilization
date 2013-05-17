package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Ballistics extends TechCard {

	public Ballistics(ResourceBundle messages) {
		super(messages.getString("ballistics"));
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.artilleryLevel < 4){
			player.artilleryLevel = 4;
		}

	}

}
