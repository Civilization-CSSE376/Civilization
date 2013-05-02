package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Writing extends TechCard {

	public Writing(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Library");

	}

}
