package Civ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainWindowTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MainWindow target = new MainWindow("English", "America", "China");
		assertNotNull(target);
	}

}
