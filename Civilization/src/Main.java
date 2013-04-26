import javax.swing.JFrame;


public class Main {
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame setup = new GameSetup();
		setup.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setup.setVisible(true);
	}

}
