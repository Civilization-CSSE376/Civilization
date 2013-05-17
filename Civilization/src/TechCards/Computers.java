package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Computers extends TechCard {

	public Computers() {
		super("Computers");
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		player.gold += 1;
		
		/*
		 * your battle and culture hand sizes are increased
		 * by 1 for every 5 coins you possess
		 */

	}

}
