package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Irrigation extends TechCard {

	public Irrigation(ResourceBundle messages) {
		super(messages.getString("irrigation"));
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.cityLimit < 3){
			player.cityLimit = 3;
		}

	}

}
