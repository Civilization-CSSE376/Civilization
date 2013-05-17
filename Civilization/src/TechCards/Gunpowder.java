package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Gunpowder extends TechCard {

	public Gunpowder(ResourceBundle messages) {
		super(messages.getString("gunpowder"));
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.infantryLevel < 3){
			player.infantryLevel = 3;
		}

	}

}
