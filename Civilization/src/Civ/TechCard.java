package Civ;

import Civ.Player;

public abstract class TechCard {

	public String name;
	public int tier;

	public TechCard(String name) {
		
		this.name = name;

	}

	public void takeEffect(Player player) {
		// Should have been overwritten.
	}
	

}
