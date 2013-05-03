package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Writing extends TechCard {

	public Writing() {
		super("Writing");
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Library");

	}

}
