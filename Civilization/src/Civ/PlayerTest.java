package Civ;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void testConstruct() {
		assertNotNull(new Player());
	}
	
	@Test
	public void testSetCapital(){
		//assertTrue();
	}
	
	@Test
	public void testGetLocation(){
		Player target = new Player();
		assertNotNull(target.getLocation());
	}
	
	@Test
	public void testSetLocation(){
		Player target = new Player();
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
		Player target = new Player();
		assertEquals(22, target.getSpeed());
	}

}
