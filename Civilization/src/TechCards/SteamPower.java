package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class SteamPower extends TechCard {

	public SteamPower(ResourceBundle messages) {
		super(messages.getString("steamPower"));
		this.tier = 3;
	}

	@Override
	public void takeEffect(Player player) {
		if(player.speed < 5){
			player.speed = 5;
		}

	}

}
