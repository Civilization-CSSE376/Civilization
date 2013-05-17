package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Mathematics extends TechCard {

	public Mathematics(ResourceBundle messages) {
		super(messages.getString("mathematics"));
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.artilleryLevel < 2){
			player.artilleryLevel = 2;	
		}

	}

}
