package Civ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameSetupTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		GameSetup target = new GameSetup();
		assertNotNull(target);
	}

}
