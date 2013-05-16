package Civ;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;


public class PlayerTest {
	
	private static Locale currentLocale = new Locale("en", "US");
	private static ResourceBundle messages = ResourceBundle.getBundle(
			"MessagesBundle", currentLocale);

	@Test
	public void testConstruct() {
		assertNotNull(new Player(messages));
	}
	
	@Test
	public void testSetCapital(){
		//assertTrue();
	}
	
	@Test
	public void testGetLocation(){
		Player target = new Player(messages);
		assertNotNull(target.getLocation());
	}
	
	@Test
	public void testSetLocation(){
		Player target = new Player(messages);
		target.setLocation(5, 5);
		assertEquals(55, target.getLocation().x, 0);
		assertEquals(55, target.getLocation().y, 0);
		
		target.setLocation(1700, 800);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(-5, -5);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(-5, 30);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(30, -5);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(1800, 30);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(30, 900);
		assertEquals(1705, target.getLocation().x, 0);
		assertEquals(825, target.getLocation().y, 0);
		
		target.setLocation(300, 50);
		assertEquals(275, target.getLocation().x, 0);
		assertEquals(55, target.getLocation().y, 0);
		
		target.setLocation(120, 120);
		assertEquals(165, target.getLocation().x, 0);
		assertEquals(165, target.getLocation().y, 0);
	}
	
	@Test
	public void testPlayerInitialSpeed(){
		Player target = new Player(messages);
		assertEquals(22, target.getSpeed());
	}

}
