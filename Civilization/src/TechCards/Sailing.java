package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Sailing extends TechCard {

	public Sailing(ResourceBundle messages) {
		super(messages.getString("sailing"));
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 4){
			player.speed = 4;
		}

	}

}
