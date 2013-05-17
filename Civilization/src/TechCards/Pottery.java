package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Pottery extends TechCard {

	public Pottery() {
		super("Pottery");
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Granary");

	}

}
