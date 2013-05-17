package TechCards;

import Civ.Government;
import Civ.Player;
import Civ.TechCard;

public class Communism extends TechCard {

	public Communism() {
		super("Communism");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Communism");
		
		player.government = new Government(player, "Communism");

	}

}
