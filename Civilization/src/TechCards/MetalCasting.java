package TechCards;

import Civ.Player;
import Civ.TechCard;

public class MetalCasting extends TechCard {

	public MetalCasting(String name) {
		super(name);
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		player.gold += 1;
		if(player.artilleryLevel < 3){
			player.artilleryLevel = 3;
		}

	}

}
