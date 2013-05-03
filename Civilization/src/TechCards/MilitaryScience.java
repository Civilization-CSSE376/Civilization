package TechCards;

import Civ.Player;
import Civ.TechCard;

public class MilitaryScience extends TechCard {

	public MilitaryScience() {
		super("MilitaryScience");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Academy");

	}

}
