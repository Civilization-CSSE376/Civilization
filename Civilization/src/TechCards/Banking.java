package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Banking extends TechCard {

	public Banking() {
		super("Banking");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Bank");

	}

}
