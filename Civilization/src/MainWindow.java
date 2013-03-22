import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel buttons = new JPanel();
	private JPanel board = new JPanel();

	private JPanel content = new JPanel();

	private JButton rules = new JButton("Rules");
	private JButton player1Details = new JButton("Player 1 Details");
	private JButton player2Details = new JButton("Player 2 Details");
	private JButton marketDetails = new JButton("Market Details");
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
		this.buttons.add(this.rules);
		this.buttons.add(this.quit);

		// this.add(this.buttons, BorderLayout.SOUTH);

		this.board.add(new Board());

		this.content.setSize(1760, 880);
		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);

		this.content
				.setLayout(new BoxLayout(this.content, BoxLayout.PAGE_AXIS));
		this.content.add(this.board);
//		this.content.add(this.buttons);
//		this.add(this.content);
		this.add(this.buttons, BorderLayout.SOUTH);
//		this.add(new Board());

		// this.add(this.board, BorderLayout.CENTER);

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

	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.printf("Mouse clicked at %d, %d", x, y);
			MainWindow.this.add(new Board(x, y));

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
