package TechCards;

import Civ.Player;
import Civ.TechCard;

public class ReplaceableParts extends TechCard {

	public ReplaceableParts() {
		super("ReplaceableParts");
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.stackSize < 6){
			player.stackSize = 6;
		}
		
		if(player.infantryLevel < 4){
			player.infantryLevel = 4;
		}

	}

}
