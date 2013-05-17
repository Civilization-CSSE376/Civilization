package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class CivilServices extends TechCard {

	public CivilServices(ResourceBundle messages) {
		super(messages.getString("civilServices"));
		this.tier = 2;
	}

	@Override
	public void takeEffect(Player player) {
		player.handSize += 1;
		player.gold += 1;

	}

}
