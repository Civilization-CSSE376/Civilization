import java.awt.BorderLayout;
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
	private String civilization;

	public GameSetup() {

		JPanel options = new JPanel();
		
		JLabel languageSelection = new JLabel("Choose a language.");
		JLabel civilizationSelection = new JLabel("Choose your civilization.");
		languageSelection.setAlignmentX(CENTER_ALIGNMENT);
		civilizationSelection.setAlignmentX(CENTER_ALIGNMENT);
		
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
		
		// Civlization Selection
		JRadioButton chooseRussia = new JRadioButton("Russia");
		chooseRussia.addActionListener(new CivRadioListener());
		chooseRussia.setSelected(true);
		this.civilization = chooseRussia.getText();
		
		JRadioButton chooseGermany = new JRadioButton("Germany");
		chooseGermany.addActionListener(new CivRadioListener());
		
		JRadioButton chooseEgypt = new JRadioButton("Egypt");
		chooseEgypt.addActionListener(new CivRadioListener());
		
		JRadioButton chooseRome = new JRadioButton("Rome");
		chooseRome.addActionListener(new CivRadioListener());
		
		JRadioButton chooseAmerica = new JRadioButton("America");
		chooseAmerica.addActionListener(new CivRadioListener());
		
		JRadioButton chooseChina = new JRadioButton("China");
		chooseChina.addActionListener(new CivRadioListener());
		
		ButtonGroup civilizationChoices = new ButtonGroup();
		civilizationChoices.add(chooseRussia);
		civilizationChoices.add(chooseGermany);
		civilizationChoices.add(chooseEgypt);
		civilizationChoices.add(chooseRome);
		civilizationChoices.add(chooseAmerica);
		civilizationChoices.add(chooseChina);

		options.add(languageSelection);
		options.add(chooseEnglish);
		options.add(chooseSpanish);
		options.add(civilizationSelection);
		options.add(chooseRussia);
		options.add(chooseGermany);
		options.add(chooseEgypt);
		options.add(chooseRome);
		options.add(chooseAmerica);
		options.add(chooseChina);
		options.add(done, BorderLayout.SOUTH);

		this.add(options);
		this.setVisible(true);

		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameSetup.this.setVisible(false);
				CreateMainWindow(GameSetup.this.language, GameSetup.this.civilization);
			}

		});
	}

	private void CreateMainWindow(String language, String civilization) {
		JFrame mainWindow = new MainWindow(language, civilization);
		
		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setResizable(false);
		
		// Change this to a quit button?
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	public class LanguageRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.language = e.getActionCommand();
		}
		
	}
	
	public class CivRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSetup.this.civilization = e.getActionCommand();
		}
		
	}
}