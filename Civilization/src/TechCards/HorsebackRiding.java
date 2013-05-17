package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class HorsebackRiding extends TechCard {

	public HorsebackRiding(ResourceBundle messages) {
		super(messages.getString("horsebackRiding"));
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 3){
			player.speed = 3;
		}

	}

}
