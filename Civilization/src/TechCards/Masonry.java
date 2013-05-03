package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Masonry extends TechCard {

	public Masonry() {
		super("Masonry");
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.stackSize < 3){
			player.stackSize = 3;
		}

	}

}
