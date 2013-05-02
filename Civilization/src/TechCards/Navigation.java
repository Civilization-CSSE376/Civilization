package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Navigation extends TechCard {

	public Navigation(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Harbor");

	}

}
