package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Engineering extends TechCard {

	public Engineering(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Aqueduct");

	}

}
