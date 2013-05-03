package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Ballistics extends TechCard {

	public Ballistics() {
		super("Ballistics");
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.artilleryLevel < 4){
			player.artilleryLevel = 4;
		}

	}

}
