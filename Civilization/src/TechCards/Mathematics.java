package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Mathematics extends TechCard {

	public Mathematics() {
		super("Mathematics");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.artilleryLevel < 2){
			player.artilleryLevel = 2;	
		}

	}

}
