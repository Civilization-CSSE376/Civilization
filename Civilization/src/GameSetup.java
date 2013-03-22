import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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

		this.setLayout(new GridLayout(7, 1));
		
//		JPanel options = new JPanel();
		
		JLabel languageSelection = new JLabel("Choose a language.", JLabel.CENTER);
		JLabel player1CivilizationSelection = new JLabel("Player 1, Choose your civilization.", JLabel.CENTER);
		JLabel player2CivilizationSelection = new JLabel("Player 2, Choose your civilization.", JLabel.CENTER);
		
		JButton done = new JButton("Ok");
		
		// Language Selection
		JRadioButton chooseEnglish = new JRadioButton("English");
		chooseEnglish.addActionListener(new LanguageRadioListener());
		chooseEnglish.setSelected(true);
		this.language = chooseEnglish.getText();

		JRadioButton chooseSpanish = new JRadioButton("Spanish");
		chooseSpanish.addActionListener(new LanguageRadioListener());

		ButtonGroup languageChoices = new ButtonGroup();
		languageChoices.add(chooseEnglish);
		languageChoices.add(chooseSpanish);
		
		// Player 1 Civilization Selection
		JRadioButton chooseRussia = new JRadioButton("Russia");
		chooseRussia.addActionListener(new Player1CivRadioListener());
		chooseRussia.setSelected(true);
		this.player1Civilization = chooseRussia.getText();
		
		JRadioButton chooseGermany = new JRadioButton("Germany");
		chooseGermany.addActionListener(new Player1CivRadioListener());
		
		JRadioButton chooseEgypt = new JRadioButton("Egypt");
		chooseEgypt.addActionListener(new Player1CivRadioListener());
		
		JRadioButton chooseRome = new JRadioButton("Rome");
		chooseRome.addActionListener(new Player1CivRadioListener());
		
		JRadioButton chooseAmerica = new JRadioButton("America");
		chooseAmerica.addActionListener(new Player1CivRadioListener());
		
		JRadioButton chooseChina = new JRadioButton("China");
		chooseChina.addActionListener(new Player1CivRadioListener());
		
		ButtonGroup player1CivilizationChoices = new ButtonGroup();
		player1CivilizationChoices.add(chooseRussia);
		player1CivilizationChoices.add(chooseGermany);
		player1CivilizationChoices.add(chooseEgypt);
		player1CivilizationChoices.add(chooseRome);
		player1CivilizationChoices.add(chooseAmerica);
		player1CivilizationChoices.add(chooseChina);
		
		// Player 2 Civilization Selection
		JRadioButton chooseRussia2 = new JRadioButton("Russia");
		chooseRussia2.addActionListener(new Player2CivRadioListener());
		chooseRussia2.setSelected(true);
		this.player2Civilization = chooseRussia2.getText();
		
		JRadioButton chooseGermany2 = new JRadioButton("Germany");
		chooseGermany2.addActionListener(new Player2CivRadioListener());
		
		JRadioButton chooseEgypt2 = new JRadioButton("Egypt");
		chooseEgypt2.addActionListener(new Player2CivRadioListener());
		
		JRadioButton chooseRome2 = new JRadioButton("Rome");
		chooseRome2.addActionListener(new Player2CivRadioListener());
		
		JRadioButton chooseAmerica2 = new JRadioButton("America");
		chooseAmerica2.addActionListener(new Player2CivRadioListener());
		
		JRadioButton chooseChina2 = new JRadioButton("China");
		chooseChina2.addActionListener(new Player2CivRadioListener());
		
		ButtonGroup player2CivilizationChoices = new ButtonGroup();
		player2CivilizationChoices.add(chooseRussia2);
		player2CivilizationChoices.add(chooseGermany2);
		player2CivilizationChoices.add(chooseEgypt2);
		player2CivilizationChoices.add(chooseRome2);
		player2CivilizationChoices.add(chooseAmerica2);
		player2CivilizationChoices.add(chooseChina2);

//		options.add(languageSelection);
//		options.add(chooseEnglish);
//		options.add(chooseSpanish);
//		options.add(civilizationSelection);
//		options.add(chooseRussia);
//		options.add(chooseGermany);
//		options.add(chooseEgypt);
//		options.add(chooseRome);
//		options.add(chooseAmerica);
//		options.add(chooseChina);
//		options.add(done, BorderLayout.SOUTH);
		this.languageManager.add(chooseEnglish);
		this.languageManager.add(chooseSpanish);
		
		this.player1CivilizationManager.add(chooseRussia);
		this.player1CivilizationManager.add(chooseGermany);
		this.player1CivilizationManager.add(chooseEgypt);
		this.player1CivilizationManager.add(chooseRome);
		this.player1CivilizationManager.add(chooseAmerica);
		this.player1CivilizationManager.add(chooseChina);
		
		this.player2CivilizationManager.add(chooseRussia2);
		this.player2CivilizationManager.add(chooseGermany2);
		this.player2CivilizationManager.add(chooseEgypt2);
		this.player2CivilizationManager.add(chooseRome2);
		this.player2CivilizationManager.add(chooseAmerica2);
		this.player2CivilizationManager.add(chooseChina2);

//		this.add(options);
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
				GameSetup.this.setVisible(false);
				GameSetup.this.dispose();
				CreateMainWindow(GameSetup.this.language, GameSetup.this.player1Civilization, GameSetup.this.player2Civilization);
			}

		});
	}

	private void CreateMainWindow(String language, String p1Civilization, String p2Civilization) {
		JFrame mainWindow = new MainWindow(language, p1Civilization, p2Civilization);
		
		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setResizable(false);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	public class LanguageRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.language = e.getActionCommand();
		}
		
	}
	
	public class Player1CivRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.player1Civilization = e.getActionCommand();
		}
		
	}
	
	public class Player2CivRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 GameSetup.this.player2Civilization = e.getActionCommand();
			
		}
		
	}
}