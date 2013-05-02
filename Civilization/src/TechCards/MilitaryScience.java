package TechCards;

import Civ.Player;
import Civ.TechCard;

public class MilitaryScience extends TechCard {

	public MilitaryScience(String name) {
		super(name);
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Academy");

	}

}
