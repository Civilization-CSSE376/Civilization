package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Currency extends TechCard {

	public Currency(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Market");
	}

}
