package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class AtomicTheory extends TechCard {

	public AtomicTheory(ResourceBundle messages) {
		super(messages.getString("atomicTheory"));
		this.tier = 4;
	}

	@Override
	public void takeEffect(Player player) {
		//two resouce abilities

	}

}
