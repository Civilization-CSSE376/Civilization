package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Biology extends TechCard {

	public Biology(String name) {
		super(name);
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.stackSize < 5){
			player.stackSize = 5;
		}
		
		/*
		 * once per battle heal all wounds from your units in play
		 */

	}

}
