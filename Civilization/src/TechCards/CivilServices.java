package TechCards;

import Civ.Player;
import Civ.TechCard;

public class CivilServices extends TechCard {

	public CivilServices(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.handSize += 1;
		player.gold += 1;

	}

}
