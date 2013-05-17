package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Gunpowder extends TechCard {

	public Gunpowder() {
		super("Gunpowder");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.infantryLevel < 3){
			player.infantryLevel = 3;
		}

	}

}
