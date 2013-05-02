package Civ;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class TechCard {

	protected String name;
	protected int tier;

	public TechCard(String name) {
		
		this.name = name;
		
		Class<TechCard> tempClass = null;
		Constructor<TechCard> cons = null;

		try {
			tempClass = (Class<TechCard>) Class.forName(name);

			try {
				cons = tempClass.getDeclaredConstructor(String.class);

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
	
	public abstract void takeEffect(Civ.Player player);

}
