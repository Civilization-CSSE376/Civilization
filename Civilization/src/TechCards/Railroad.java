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
		if(player.calvaryLevel < 3){
			player.calvaryLevel = 3;
		}

	}

}
