package TechCards;

import Civ.Player;
import Civ.TechCard;

public class CodeOfLaws extends TechCard {

	public CodeOfLaws(String name) {
		super(name);
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add("Republic");
		player.unlockedBuildings.add("TradingPost");

	}

}
