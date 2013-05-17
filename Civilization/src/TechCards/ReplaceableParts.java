package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class ReplaceableParts extends TechCard {

	public ReplaceableParts(ResourceBundle messages) {
		super(messages.getString("replaceableParts"));
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
