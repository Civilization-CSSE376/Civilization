package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Flight extends TechCard {

	public Flight(String name) {
		super(name);
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 6){
			player.speed = 6;
		}

	}

}
