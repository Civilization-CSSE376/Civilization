import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class GameSetup extends JFrame {

	private static final int WINDOW_WIDTH = 1800;
	private static final int WINDOW_HEIGHT = 1000;

	private String language;
	private String player1Civilization;
	private String player2Civilization;

	private JPanel languageManager = new JPanel();
	private JPanel player1CivilizationManager = new JPanel();
	private JPanel player2CivilizationManager = new JPanel();

	public GameSetup() {
		this.setLayout(null);
		this.setTitle("Civilization Setup");
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		this.setIconImage(icon.getImage());

		this.setLayout(new GridLayout(7, 1));

		JLabel languageSelection = new JLabel("Language (Idioma) :",
				JLabel.CENTER);
		JLabel player1CivilizationSelection = new JLabel(
				"Player 1, Choose your civilization. (Jugador 1, Elige tu civilización)", JLabel.CENTER);
		JLabel player2CivilizationSelection = new JLabel(
				"Player 2, Choose your civilization. (Jugador 2, Elige tu civilización)", JLabel.CENTER);

		JButton done = new JButton("Play!");

		// Language Selection
		JRadioButton chooseEnglish = new JRadioButton("English");
		chooseEnglish.addActionListener(new LanguageRadioListener());
		chooseEnglish.setSelected(true);
		this.language = chooseEnglish.getText();

		JRadioButton chooseSpanish = new JRadioButton("Español");
		chooseSpanish.addActionListener(new LanguageRadioListener());

		ButtonGroup languageChoices = new ButtonGroup();
		languageChoices.add(chooseEnglish);
		languageChoices.add(chooseSpanish);
		
		String[] civs = { "Russia/Rusia", "Germany/Alemania", "Egypt/Egipto", "Rome/Roma", "America/América", "China/China"};
		ButtonGroup player1CivilizationChoices = new ButtonGroup();
		ButtonGroup player2CivilizationChoices = new ButtonGroup();

		// Player 1 Civilization Selection
		for(int i = 0; i < 6; i++){
			JRadioButton chooseCiv = new JRadioButton(civs[i]);
			chooseCiv.addActionListener(new Player1CivRadioListener());
			if(i == 0) {
				chooseCiv.setSelected(true);
				this.player1Civilization = chooseCiv.getText();
			}
			player1CivilizationChoices.add(chooseCiv);
			this.player1CivilizationManager.add(chooseCiv);
		}

		// Player 2 Civilization Selection
		for(int i = 0; i < 6; i++){
			JRadioButton chooseCiv = new JRadioButton(civs[i]);
			chooseCiv.addActionListener(new Player2CivRadioListener());
			if(i == 0) {
				chooseCiv.setSelected(true);
				this.player2Civilization = chooseCiv.getText();
			}
			player2CivilizationChoices.add(chooseCiv);
			this.player2CivilizationManager.add(chooseCiv);
		}

		this.languageManager.add(chooseEnglish);
		this.languageManager.add(chooseSpanish);

		this.add(languageSelection);
		this.add(this.languageManager);
		this.add(player1CivilizationSelection);
		this.add(this.player1CivilizationManager);
		this.add(player2CivilizationSelection);
		this.add(this.player2CivilizationManager);
		this.add(done);
		this.setVisible(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameSetup.this.player1Civilization
						.equals(GameSetup.this.player2Civilization)) {
					makeWarningWindow();
				} else {
					GameSetup.this.setVisible(false);
					GameSetup.this.dispose();
					CreateMainWindow(GameSetup.this.language,
							GameSetup.this.player1Civilization,
							GameSetup.this.player2Civilization);
				}
			}

		});
	}

	private void makeWarningWindow() {
		final JFrame warningWindow = new JFrame();
		JPanel text = new JPanel();
		JLabel warning = new JLabel("Player 1 and player 2 cannot choose the same civilization. (Jugador 1 y 2 no pueden elegir la misma civilización.)");
		text.add(warning);
		
		JButton button = new JButton("Ok / Bueno");
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				warningWindow.setVisible(false);
				warningWindow.dispose();
				
			}
			
		});
		
		warningWindow.add(text);
		warningWindow.add(button, BorderLayout.SOUTH);
		warningWindow.pack();
		warningWindow.setVisible(true);
	}

	private void CreateMainWindow(String language, String p1Civilization,
			String p2Civilization) {
		JFrame mainWindow = new MainWindow(language, p1Civilization.split("/")[0],
				p2Civilization.split("/")[0]);

		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setResizable(false);

		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	public class LanguageRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.language = e.getActionCommand();
		}

	}

	public class Player1CivRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.player1Civilization = e.getActionCommand();
		}

	}

	public class Player2CivRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.player2Civilization = e.getActionCommand();

		}

	}
}