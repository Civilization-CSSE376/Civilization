package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class PrintingPress extends TechCard {

	private static ResourceBundle messages;
	
	public PrintingPress(ResourceBundle messages) {
		super(messages.getString("printingPress"));
		PrintingPress.messages = messages;
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("university"));
		if(player.stackSize < 4){
			player.stackSize = 4;
		}

	}

}
