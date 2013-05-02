package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Democracy extends TechCard {

	public Democracy(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Democracy");
		if(player.infantryLevel < 2){
			player.infantryLevel = 2;
		}

	}

}
