package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Sailing extends TechCard {

	public Sailing(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 4){
			player.speed = 4;
		}

	}

}
