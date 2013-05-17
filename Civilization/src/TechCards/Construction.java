package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Construction extends TechCard {

	public Construction() {
		super("Construction");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Workshop");

	}

}
