package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Banking extends TechCard {

	public Banking(String name) {
		super(name);
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Bank");

	}

}
