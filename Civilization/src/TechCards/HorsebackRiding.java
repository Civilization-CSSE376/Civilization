package TechCards;

import Civ.Player;
import Civ.TechCard;

public class HorsebackRiding extends TechCard {

	public HorsebackRiding(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 3){
			player.speed = 3;
		}

	}

}
