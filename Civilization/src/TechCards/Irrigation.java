package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Irrigation extends TechCard {

	public Irrigation(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.cityLimit < 3){
			player.cityLimit = 3;
		}

	}

}
