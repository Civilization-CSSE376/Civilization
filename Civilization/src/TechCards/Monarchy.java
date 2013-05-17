package TechCards;

import Civ.Government;
import Civ.Player;
import Civ.TechCard;

public class Monarchy extends TechCard {

	public Monarchy() {
		super("Monarchy");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Monarchy");

		player.government = new Government(player, "Monarchy");
	}

}
