package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Combustion extends TechCard {

	public Combustion(ResourceBundle messages) {
		super(messages.getString("combustion"));
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.cavalryLevel < 4){
			player.cavalryLevel = 4;
		}

	}

}
