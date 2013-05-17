package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Chivalry extends TechCard {

	private static ResourceBundle messages;
	
	public Chivalry(ResourceBundle messages) {
		super(messages.getString("chivalry"));
		Chivalry.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedGovernments.add(messages.getString("feudalism"));
		if(player.cavalryLevel < 2){
			player.cavalryLevel = 2;
		}
	}

}
