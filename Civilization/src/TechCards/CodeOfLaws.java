package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class CodeOfLaws extends TechCard {

	private static ResourceBundle messages;
	
	public CodeOfLaws(ResourceBundle messages) {
		super(messages.getString("codeOfLaws"));
		CodeOfLaws.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add(messages.getString("republic"));
		player.unlockedBuildings.add(messages.getString("tradingPost"));

	}

}
