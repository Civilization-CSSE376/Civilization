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
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
	private JButton rules = new JButton();
	private JButton player1Details = new JButton();
	private JButton player2Details = new JButton();
	private JButton marketDetails = new JButton();
	private JButton endPhase = new JButton();
	private JButton quit = new JButton();
	private String language;
	private String p1Civilization;
	private String p2Civilization;

	
	public MainWindow(String languageChosen, String player1CivilizationChosen, String player2CivilizationChosen) {
		Locale currentLocale;
		final ResourceBundle messages;
		if(languageChosen.equals("English")) currentLocale = new Locale("en", "US");
		else currentLocale = new Locale("sp", "SP");
		
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		
		
		this.setLayout(null);
		System.out.println(languageChosen + " was chosen as the language.");
		System.out.printf("Player 1 chose " + player1CivilizationChosen + " as his/her civilization and player 2 chose " + player2CivilizationChosen + " as his/her civilization.\n");
		
		this.language = languageChosen;
		this.p1Civilization = player1CivilizationChosen;
		this.p2Civilization = player2CivilizationChosen;

		this.setTitle("Civilization");
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		this.setIconImage(icon.getImage());
		
		this.player1Details.setText(messages.getString("player1Details"));
		this.player2Details.setText(messages.getString("player2Details"));
		this.marketDetails.setText(messages.getString("marketDetails"));
		this.endPhase.setText(messages.getString("endPhase"));
		this.rules.setText(messages.getString("rules"));
		this.quit.setText(messages.getString("quit"));
		
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
		
		// Holds coordinates & sizes of the three border JPanels
		int[] nums = {0, 20, 20, 905, 1781, 20, 20, 905, 0, 0, 1800, 20};
		
		for(int i = 0; i < 3; i++){
			JPanel borderPanel = new JPanel();
			borderPanel.setLocation(nums[i*4], nums[i*4 + 1]);
			borderPanel.setSize(nums[i*4 + 2], nums[i*4 + 3]);
			borderPanel.setBackground(Color.BLACK);
			this.add(borderPanel);
		}

		final JPanel map = new Board(this.p1Civilization, this.p2Civilization, messages);
		map.setLocation(20, 20);
		map.setSize(1761, 905);
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
				
				makePlayerWindow(messages.getString("player1Details"), MainWindow.this.p1Civilization, messages);
				
			}
			
		});
		
		this.player2Details.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				makePlayerWindow(messages.getString("player2Details"), MainWindow.this.p2Civilization, messages);
			}	
		});
		
		this.marketDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame marketWindow = new JFrame(messages.getString("marketDetails"));
				ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
				marketWindow.setIconImage(icon.getImage());
				marketWindow.setSize(700, 800);
				marketWindow.setVisible(true);
				
			}
			
		
		});

	}


	private void makePlayerWindow(String windowName, String playerCivilizationField, ResourceBundle messages) {
		
		final JFrame playerWindow = new JFrame(windowName);
		playerWindow.setResizable(false);
		playerWindow.setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		playerWindow.setIconImage(icon.getImage());
		
		playerWindow.setSize(900, 565);
		playerWindow.setAlwaysOnTop(true);
		playerWindow.setVisible(true);
		MainWindow.this.setEnabled(false);
		playerWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel buttonPanel = new JPanel();
		JPanel civPic = new JPanel();
		JPanel info = new JPanel();
		JPanel buffer = new JPanel();
		
		info.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);
		buffer.setBackground(Color.BLACK);
		
		JButton closeButton = new JButton(messages.getString("close"));
		buttonPanel.add(closeButton);
		
		buttonPanel.setLocation(550, 500);
		buttonPanel.setSize(345, 40);
		
		civPic.setLocation(0,-6);
		civPic.setSize(550, 565);
		String picFile = "src/civs/" + playerCivilizationField + ".png";
		JLabel picLabel;
		try {
			BufferedImage civPicture = ImageIO.read(new File(picFile));
			picLabel = new JLabel(new ImageIcon(civPicture));
			civPic.add(picLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		info.setLocation(600, 0);
		info.setSize(295, 500);
		info.setLayout(new GridLayout(5, 1));
		String gov = "";
		if(playerCivilizationField.equals("Rome")) gov = messages.getString("republic");
		else if(playerCivilizationField.equals("Russia")) gov = messages.getString("communism");
		else gov = messages.getString("despotism");
		
		JLabel government = new JLabel(messages.getString("government") + gov);
		government.setForeground(Color.WHITE);
//		government.setLocation(25, 25);
//		government.setSize(300, 25);
		
		JLabel governmentAbility = new JLabel(messages.getString("ability"));
		governmentAbility.setForeground(Color.WHITE);
//		government.setLocation(25, 100);
//		government.setSize(300, 25);
		
		JLabel trade = new JLabel(messages.getString("trade1"));
		trade.setForeground(Color.WHITE);
		
		JLabel gold = new JLabel(messages.getString("gold"));
		gold.setForeground(Color.WHITE);
		
		JLabel culture = new JLabel(messages.getString("culture"));
		culture.setForeground(Color.WHITE);
		
		String resourceString = "";
//		if(windowName.equals("Player 1 Details")) resourceString = getResourceString(1);
//		else resourceString = getResourceString(2);
		JLabel resources = new JLabel(messages.getString("resources") + resourceString);
		resources.setForeground(Color.WHITE);
		
		info.add(government);
		info.add(governmentAbility);
		info.add(trade);
		info.add(gold);
		info.add(resources);
//		info.add(culture);
		
		buffer.setLocation(550, 0);
		buffer.setSize(50, 500);
		
		playerWindow.add(buttonPanel);
		playerWindow.add(civPic);
		playerWindow.add(info);
		playerWindow.add(buffer);
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setEnabled(true);
				playerWindow.dispose();
			}
			
		});
		
	}


	private String getResourceString(int playerNumber) {
		String resources = "";
		int[] numberOfEachResource;
		String[] resourceNames = {"Iron", "Wheat", "Incense", "Silk"};
//		if(playerNumber == 1) numberOfEachResource = getNumberOfEachResource(Board.players.get(0).getPlayerResources());
//		else numberOfEachResource = getNumberOfEachResource(Board.players.get(1).getPlayerResources());
//		for(int i = 0; i < 4; i++) resources = resources + resourceNames[i] + ": " + numberOfEachResource[i] + "  ";
		
		System.out.println(resources);
		return resources;
	}
	
	private int[] getNumberOfEachResource(ArrayList<Tile.Resource> resources){
		int iron = 0;
		int wheat = 0;
		int incense = 0;
		int silk = 0;
		
		if(resources == null) System.out.println("Null object -> resources");
		int counter = 0;
		for(Tile.Resource resource : resources){
			System.out.println(resource.toString() + ", Counter is at " + counter);
			switch (resource.toString()) {
			case "Iron":
				iron++;
				break;
			case "Wheat":
				wheat++;
				break;
			case "Incense":
				incense++;
				break;
			case "Silk":
				silk++;
				break;
			default:
				System.out.println("Unidentified resource: " + resource.toString());
				break;
			}
			counter++;
		}
		
		int[] resourceArray = {iron, wheat, incense, silk};
		return resourceArray;
	}
}
