import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	// Fields...
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
		this.setLayout(null);
		System.out.println(languageChosen + " was chosen as the language.");
		System.out.printf("Player 1 chose " + player1CivilizationChosen + " as his/her civilization and player 2 chose " + player2CivilizationChosen + " as his/her civilization.\n");
		
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

//		this.board.add(new Board());

		this.content.add(this.board);
		
		this.buttons.setBackground(Color.BLACK);
		this.buttons.setLocation(0, 925);
		this.buttons.setSize(1800, 50);

		this.add(this.buttons);
		
		JPanel leftpad = new JPanel();
		leftpad.setLocation(0, 20);
		leftpad.setSize(20, 905);
		leftpad.setBackground(Color.BLACK);
		
		JPanel rightpad = new JPanel();
		rightpad.setLocation(1781, 20);
		rightpad.setSize(20, 905);
		rightpad.setBackground(Color.BLACK);
		
		JPanel toppad = new JPanel();
		toppad.setLocation(0, 0);
		toppad.setSize(1800, 20);
		toppad.setBackground(Color.BLACK);

		final JPanel map = new Board(this.p1Civilization, this.p2Civilization);
		map.setLocation(20, 20);
		map.setSize(1761, 905);
		
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
		
		this.player1Details.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame player1Window = new JFrame("Player 1 Details");
				player1Window.setResizable(false);
				player1Window.setLayout(null);
				
				ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
				player1Window.setIconImage(icon.getImage());
				
				player1Window.setSize(900, 565);
				player1Window.setAlwaysOnTop(true);
				player1Window.setVisible(true);
				MainWindow.this.setEnabled(false);
				player1Window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				
				JPanel buttonPanel = new JPanel();
				JPanel civPic = new JPanel();
				JPanel info = new JPanel();
				JPanel buffer = new JPanel();
				
				info.setBackground(Color.BLACK);
				buttonPanel.setBackground(Color.BLACK);
				buffer.setBackground(Color.BLACK);
				
				JButton closeButton = new JButton("Close");
				buttonPanel.add(closeButton);
				
				buttonPanel.setLocation(550, 500);
				buttonPanel.setSize(345, 40);
				
				civPic.setLocation(0,-6);
				civPic.setSize(550, 565);
				String picFile = "src/civs/" + MainWindow.this.p1Civilization + ".png";
				JLabel picLabel;
				try {
					BufferedImage civPicture = ImageIO.read(new File(picFile));
					picLabel = new JLabel(new ImageIcon(civPicture));
					civPic.add(picLabel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				info.setLocation(600, 0);
				info.setSize(295, 500);
				info.setLayout(new GridLayout(5, 1));
				String gov = "";
				if(MainWindow.this.p1Civilization.equals("Rome")) gov = "Republic";
				else if(MainWindow.this.p1Civilization.equals("Russia")) gov = "Communism";
				else gov = "Despotism";
				
				JLabel government = new JLabel("Government: " + gov);
				government.setForeground(Color.WHITE);
//				government.setLocation(25, 25);
//				government.setSize(300, 25);
				
				JLabel governmentAbility = new JLabel("Government ability: ");
				governmentAbility.setForeground(Color.WHITE);
//				government.setLocation(25, 100);
//				government.setSize(300, 25);
				
				JLabel trade = new JLabel("Trade: ");
				trade.setForeground(Color.WHITE);
				
				JLabel gold = new JLabel("Gold: ");
				gold.setForeground(Color.WHITE);
				
				JLabel resources = new JLabel("Resources: ");
				resources.setForeground(Color.WHITE);
				
				info.add(government);
				info.add(governmentAbility);
				info.add(trade);
				info.add(gold);
				info.add(resources);
				
				buffer.setLocation(550, 0);
				buffer.setSize(50, 500);
				
				player1Window.add(buttonPanel);
				player1Window.add(civPic);
				player1Window.add(info);
				player1Window.add(buffer);
				
				closeButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						MainWindow.this.setEnabled(true);
						player1Window.dispose();
					}
					
				});
				
				
				
			}
			
		});
		
		this.player2Details.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame player2Window = new JFrame("Player 2 Details");
				player2Window.setResizable(false);
				player2Window.setLayout(null);
				
				ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
				player2Window.setIconImage(icon.getImage());
				
				player2Window.setSize(900, 565);
				player2Window.setAlwaysOnTop(true);
				player2Window.setVisible(true);
				MainWindow.this.setEnabled(false);
				player2Window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				
				JPanel buttonPanel = new JPanel();
				JPanel civPic = new JPanel();
				JPanel info = new JPanel();
				JPanel buffer = new JPanel();
				
				info.setBackground(Color.BLACK);
				buttonPanel.setBackground(Color.BLACK);
				buffer.setBackground(Color.BLACK);
				
				JButton closeButton = new JButton("Close");
				buttonPanel.add(closeButton);
				
				buttonPanel.setLocation(550, 500);
				buttonPanel.setSize(345, 40);
				
				civPic.setLocation(0,-6);
				civPic.setSize(550, 565);
				String picFile = "src/civs/" + MainWindow.this.p2Civilization + ".png";
				JLabel picLabel;
				try {
					BufferedImage civPicture = ImageIO.read(new File(picFile));
					picLabel = new JLabel(new ImageIcon(civPicture));
					civPic.add(picLabel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				info.setLocation(600, 0);
				info.setSize(295, 500);
				info.setLayout(new GridLayout(5, 1));
				String gov = "";
				if(MainWindow.this.p1Civilization.equals("Rome")) gov = "Republic";
				else if(MainWindow.this.p1Civilization.equals("Russia")) gov = "Communism";
				else gov = "Despotism";
				
				JLabel government = new JLabel("Government: " + gov);
				government.setForeground(Color.WHITE);
//				government.setLocation(25, 25);
//				government.setSize(300, 25);
				
				JLabel governmentAbility = new JLabel("Government ability: ");
				governmentAbility.setForeground(Color.WHITE);
//				government.setLocation(25, 100);
//				government.setSize(300, 25);
				
				JLabel trade = new JLabel("Trade: ");
				trade.setForeground(Color.WHITE);
				
				JLabel gold = new JLabel("Gold: ");
				gold.setForeground(Color.WHITE);
				
				JLabel resources = new JLabel("Resources: ");
				resources.setForeground(Color.WHITE);
				
				info.add(government);
				info.add(governmentAbility);
				info.add(trade);
				info.add(gold);
				info.add(resources);
				
				buffer.setLocation(550, 0);
				buffer.setSize(50, 500);
				
				player2Window.add(buttonPanel);
				player2Window.add(civPic);
				player2Window.add(info);
				player2Window.add(buffer);
				
				closeButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						MainWindow.this.setEnabled(true);
						player2Window.dispose();
					}
					
				});
				
				
				
			}	
		});
		
		this.marketDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame marketWindow = new JFrame("Market Details");
				ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
				marketWindow.setIconImage(icon.getImage());
				marketWindow.setSize(700, 800);
				marketWindow.setVisible(true);
				
			}
			
		
		});

	}
}
