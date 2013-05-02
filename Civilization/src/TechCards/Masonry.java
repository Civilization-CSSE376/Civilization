package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Masonry extends TechCard {

	public Masonry(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.stackSize < 3){
			player.stackSize = 3;
		}

	}

}
