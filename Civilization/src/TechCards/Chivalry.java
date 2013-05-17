package TechCards;

import Civ.Government;
import Civ.Player;
import Civ.TechCard;

public class Chivalry extends TechCard {

	public Chivalry() {
		super("Chivalry");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Feudalism");
		if(player.cavalryLevel < 2){
			player.cavalryLevel = 2;
		}
		player.government = new Government(player ,"Feudalism");
	}

}
