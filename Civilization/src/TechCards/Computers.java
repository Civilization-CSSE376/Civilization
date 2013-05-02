package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Computers extends TechCard {

	public Computers(String name) {
		super(name);
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		player.gold += 1;
		
		/*
		 * your batt and culture hand sizes are increased
		 * by 1 for every 5 coins you possess
		 */

	}

}
