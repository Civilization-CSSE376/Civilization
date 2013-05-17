package TechCards;

import Civ.Player;
import Civ.TechCard;

public class PrintingPress extends TechCard {

	public PrintingPress() {
		super("PrintingPress");
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add("University");
		if(player.stackSize < 4){
			player.stackSize = 4;
		}

	}

}
