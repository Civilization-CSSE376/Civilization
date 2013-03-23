import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel buttons = new JPanel();
	private JPanel board = new JPanel();

	private JPanel content = new JPanel();

	private JButton rules = new JButton("Rules");
	private JButton player1Details = new JButton("Player 1 Details");
	private JButton player2Details = new JButton("Player 2 Details");
	private JButton marketDetails = new JButton("Market Details");
	private JButton endPhase = new JButton("EndPhase");
	private JButton quit = new JButton("Quit");

	private String language;
	private String p1Civilization;
	private String p2Civilization;

	public MainWindow(String languageChosen, String player1CivilizationChosen, String player2CivilizationChosen) {
		System.out.println(languageChosen + " was chosen as the language.");
		System.out.printf("Player 1 chose " + player1CivilizationChosen + " as his/her civilization and player 2 chose " + player2CivilizationChosen + " as his/her civilization.");
		
		this.language = languageChosen;
		this.p1Civilization = player1CivilizationChosen;
		this.p2Civilization = player2CivilizationChosen;

		this.setTitle("Civilization");
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		this.setIconImage(icon.getImage());

		this.buttons.add(this.player1Details);
		this.buttons.add(this.player2Details);
		this.buttons.add(this.marketDetails);
		this.buttons.add(this.endPhase);
		this.buttons.add(this.rules);
		this.buttons.add(this.quit);

		this.board.add(new Board());

		this.content.add(this.board);
		
//		this.buttons.setBackground(Color.BLACK);
		this.buttons.setLocation(0, 935);
		this.buttons.setSize(1800, 50);

		this.add(this.buttons);
		
		JPanel leftpad = new JPanel();
		leftpad.setLocation(0, 20);
		leftpad.setSize(20, 880);
//		leftpad.setBackground(Color.BLACK);
		
		JPanel rightpad = new JPanel();
		rightpad.setLocation(1780, 20);
		rightpad.setSize(20, 880);
//		rightpad.setBackground(Color.BLACK);
		
		JPanel toppad = new JPanel();
		toppad.setLocation(0, 0);
		toppad.setSize(1800, 20);
//		toppad.setBackground(Color.BLACK);

		final JPanel map = new Board();
		map.setLocation(20, 20);
		map.setSize(1760, 880);
		
		this.add(leftpad);
		this.add(rightpad);
		this.add(toppad);
		this.add(map);
		
		this.endPhase.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				((Board) map).endPhase();
			}
		});
			
		this.rules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Desktop.isDesktopSupported()) {
					try {
						File rulespdf = new File("src/civilization-rules.pdf");
						Desktop.getDesktop().open(rulespdf);
					} catch (IOException ex) {
						System.out.println("Failed to open pdf.");
					}
				}

			}

		});

		this.quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setVisible(false);
				System.exit(0);
			}

		});

	}
}
