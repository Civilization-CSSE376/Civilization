package TechCards;

import Civ.Player;
import Civ.TechCard;

public class Philosophy extends TechCard {

	public Philosophy() {
		super("Philosophy");
		this.tier = 1;
		
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("Temple");
		
	}

}
