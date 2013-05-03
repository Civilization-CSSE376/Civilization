package TechCards;

import Civ.Player;
import Civ.TechCard;

public class MetalWorking extends TechCard {

	public MetalWorking() {
		super("MetalWorking");
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Barracks");

	}

}
