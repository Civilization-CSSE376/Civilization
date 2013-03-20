import javax.swing.JFrame;


public class Main {
	private static final int WINDOW_WIDTH = 1900;
	private static final int WINDOW_HEIGHT = 1000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame mainWindow = new MainWindow();
		
		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setResizable(false);
		
		// Change this to a quit button?
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		
	}

}
