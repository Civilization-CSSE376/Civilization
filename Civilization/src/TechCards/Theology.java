package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Theology extends TechCard {

	public Theology() {
		super("Theology");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.handSize += 1;
		player.unlockedBuildings.add("Cathedral");
		player.unlockedGovernments.add("Fundamentalism");

	}

}
