package TechCards;

import java.util.ResourceBundle;

import Civ.Player;
import Civ.TechCard;

public class AnimalHusbandry extends TechCard {

	public AnimalHusbandry(ResourceBundle messages) {
		super(messages.getString("animalHusbandry"));
		this.tier = 1;
	}

	@Override
	public void takeEffect(Player player) {
		/*
		 * once per battle, heal up to a total of 3 wounds from your units in play
		 */

	}

}
