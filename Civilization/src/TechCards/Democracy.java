package TechCards;

import Civ.Government;
import Civ.Player;
import Civ.TechCard;

public class Democracy extends TechCard {

	public Democracy() {
		super("Democracy");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Democracy");
		if(player.infantryLevel < 2){
			player.infantryLevel = 2;
		}
		
		player.government = new Government(player, "Democracy");

	}

}
