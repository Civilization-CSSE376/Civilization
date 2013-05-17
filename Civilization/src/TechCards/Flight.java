package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Flight extends TechCard {

	public Flight(ResourceBundle messages) {
		super(messages.getString("flight"));
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 6){
			player.speed = 6;
		}

	}

}
