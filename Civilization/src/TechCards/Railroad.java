package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Railroad extends TechCard {

	public Railroad() {
		super("Railroad");
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("IronMine");
		if(player.cavalryLevel < 3){
			player.cavalryLevel = 3;
		}

	}

}
