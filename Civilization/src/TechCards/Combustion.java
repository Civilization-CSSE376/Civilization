package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Combustion extends TechCard {

	public Combustion(String name) {
		super(name);
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.calvaryLevel < 4){
			player.calvaryLevel = 4;
		}

	}

}
