package TechCards;

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

	}

}
