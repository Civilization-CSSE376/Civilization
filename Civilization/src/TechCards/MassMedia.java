package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class MassMedia extends TechCard {

	public MassMedia(ResourceBundle messages) {
		super(messages.getString("massMedia"));
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		/*
		 * your culture events cannot be canceled, regardless of other game effects
		 */

	}

}
