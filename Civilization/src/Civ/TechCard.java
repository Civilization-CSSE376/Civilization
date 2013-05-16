package Civ;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
