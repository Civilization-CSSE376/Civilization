package TechCards;

import Civ.Player;
import Civ.TechCard;

public class AnimalHusbandry extends TechCard {

	public AnimalHusbandry(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		/*
		 * once per battle, heal up to a total of 3 wounds from your units in play
		 */

	}

}
