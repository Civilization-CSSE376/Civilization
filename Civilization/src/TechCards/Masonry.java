package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class Masonry extends TechCard {

	public Masonry(ResourceBundle messages) {
		super(messages.getString("masonry"));
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.stackSize < 3){
			player.stackSize = 3;
		}

	}

}
