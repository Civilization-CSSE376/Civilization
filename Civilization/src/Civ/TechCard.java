package Civ;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import Civ.Player;

public class TechCard {

	public String name;
	public int tier;

	public TechCard(String name) {
		
		this.name = name;
		
		Class<TechCard> tempClass = null;
		Constructor<TechCard> cons = null;

		try {
			tempClass = (Class<TechCard>) Class.forName("TechCards."+name);

			try {
				cons = tempClass.getDeclaredConstructor();

				try {
					TechCard adapter = cons.newInstance();
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	public void takeEffect(Player player) {
		System.out.println("This should have been overridden");
	}
	

}
