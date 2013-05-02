package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Construction extends TechCard {

	public Construction(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Workshop");

	}

}
