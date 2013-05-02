package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Chivalry extends TechCard {

	public Chivalry(String name) {
		super(name);
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Feudalism");
		if(player.calvaryLevel < 2){
			player.calvaryLevel = 2;
		}
	}

}
