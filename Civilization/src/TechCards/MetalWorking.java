package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class MetalWorking extends TechCard {
	private static ResourceBundle messages;
	
	public MetalWorking(ResourceBundle messages) {
		super(messages.getString("metalWorking"));
		MetalWorking.messages = messages;
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		player.unlockedBuildings.add(messages.getString("barracks"));

	}

}
