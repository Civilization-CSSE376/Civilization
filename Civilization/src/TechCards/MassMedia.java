package TechCards;

import Civ.Player;
import Civ.TechCard;

public class MassMedia extends TechCard {

	public MassMedia() {
		super("MassMedia");
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		/*
		 * your culture events cannot be canceled, regardless of other game effects
		 */

	}

}
