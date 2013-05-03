package Civ;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
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
	private String p1Civilization;
	private String p2Civilization;
	
	public MainWindow(String languageChosen, String player1CivilizationChosen, String player2CivilizationChosen) {
		Locale currentLocale;
		final ResourceBundle messages;
		if(languageChosen.equals("English")) currentLocale = new Locale("en", "US");
		else currentLocale = new Locale("sp", "SP");
		
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		this.setLayout(null);
		
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
				
				int[] cultureLocations = { 25, 70, 125, 180, 235, 290, 345, 400, 455, 510, 565, 620, 675, 730, 790, 845, 905, 960, 1015, 1070, 1125, 1180};
				
				final JFrame marketWindow = makeNewWindow(1225, 685, messages.getString("marketDetails"));
				
				JPanel marketBoard = makePicturePanel(0, -5, 1220, 625, "src/marketBoard.png");
				JPanel buttonPanel = new JPanel();
				buttonPanel.setBackground(Color.BLACK);
				JButton closeButton = new JButton(messages.getString("close"));
				buttonPanel.add(closeButton);
				buttonPanel.setLocation(0, 620);
				buttonPanel.setSize(1235, 40);
				
				JPanel player1CultureLocation = new JPanel();
				player1CultureLocation.setBackground(Color.RED);
				player1CultureLocation.setLocation(cultureLocations[Board.getPlayer(1).culture], 540);
				player1CultureLocation.setSize(15, 25);
				JPanel player2CultureLocation = new JPanel();
				player2CultureLocation.setBackground(Color.YELLOW);
				player2CultureLocation.setLocation(cultureLocations[Board.getPlayer(2).culture], 570);
				player2CultureLocation.setSize(15, 25);
				
				marketWindow.add(player1CultureLocation);
				marketWindow.add(player2CultureLocation);
				marketWindow.add(marketBoard);
				marketWindow.add(buttonPanel);
				marketWindow.setVisible(true);
				
				closeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.setEnabled(true);
						marketWindow.dispose();
						
					}
					
				});
			}
			
		
		});

	}
	
	private JFrame makeNewWindow(int width, int height, String title){
		JFrame window = new JFrame(title);
		window.setResizable(false);
		window.setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		window.setIconImage(icon.getImage());
		
		window.setSize(width, height);
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		MainWindow.this.setEnabled(false);
		
		return window;
	}
	
	private JPanel makePicturePanel(int locationX, int locationY, int width, int height, String fileName){
		JPanel picturePanel = new JPanel();
		picturePanel.setLocation(locationX, locationY);
		picturePanel.setSize(width, height);
		JLabel picLabel;
		try {
			BufferedImage civPicture = ImageIO.read(new File(fileName));
			picLabel = new JLabel(new ImageIcon(civPicture));
			picturePanel.add(picLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return picturePanel;
	}


	private void makePlayerWindow(String windowName, String playerCivilizationField, ResourceBundle messages) {
		int playerNum;
		if(windowName.equals(messages.getString("player1Details"))) playerNum = 1;
		else playerNum = 2;
		
		final JFrame playerWindow = makeNewWindow(900, 565, windowName);
		
		JPanel buttonPanel = new JPanel();
		JPanel civPic = makePicturePanel(0, -6, 550, 565, "src/civs/" + playerCivilizationField + ".png");
		JPanel info = new JPanel();
		JPanel buffer = new JPanel();
		
		info.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);
		buffer.setBackground(Color.BLACK);
		
		JButton closeButton = new JButton(messages.getString("close"));
		buttonPanel.add(closeButton);
		
		buttonPanel.setLocation(550, 500);
		buttonPanel.setSize(345, 40);
		
		info.setLocation(600, 0);
		info.setSize(295, 500);
		info.setLayout(new GridLayout(6, 1));
		String gov = "";
		if(playerCivilizationField.equals("Rome")) gov = messages.getString("republic");
		else if(playerCivilizationField.equals("Russia")) gov = messages.getString("communism");
		else gov = messages.getString("despotism");
		
		JLabel government = new JLabel(messages.getString("government") + gov);
		government.setForeground(Color.WHITE);
		
		JLabel governmentAbility = new JLabel(messages.getString("ability"));
		governmentAbility.setForeground(Color.WHITE);
		
		JLabel trade = new JLabel(messages.getString("trade1") + Board.getPlayer(playerNum).trade);
		trade.setForeground(Color.WHITE);
		
		JLabel gold = new JLabel(messages.getString("gold") + Board.getPlayer(playerNum).gold);
		gold.setForeground(Color.WHITE);
		
		JLabel culture = new JLabel(messages.getString("culture") + Board.getPlayer(playerNum).culture);
		culture.setForeground(Color.WHITE);
		
		String resourceString = "";
		resourceString = getResourceString(playerNum, messages);
		
		JLabel resources = new JLabel(messages.getString("resources") + resourceString);
		resources.setForeground(Color.WHITE);
		
		info.add(government);
		info.add(governmentAbility);
		info.add(trade);
		info.add(gold);
		info.add(resources);
		info.add(culture);
		
		buffer.setLocation(550, 0);
		buffer.setSize(50, 500);
		
		playerWindow.add(buttonPanel);
		playerWindow.add(civPic);
		playerWindow.add(info);
		playerWindow.add(buffer);
		
		playerWindow.setVisible(true);
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setEnabled(true);
				playerWindow.dispose();
			}
			
		});
		
	}


	private String getResourceString(int playerNumber, ResourceBundle messages) {
		String resources = "";
		int[] numberOfEachResource;
		String[] resourceNames = {messages.getString("iron"), messages.getString("wheat"), messages.getString("incense"), messages.getString("silk")};
		if(playerNumber == 1) numberOfEachResource = getNumberOfEachResource(Board.getPlayer(1).resources);
		else numberOfEachResource = getNumberOfEachResource(Board.getPlayer(2).resources);
		for(int i = 0; i < 4; i++) resources = resources + resourceNames[i] + ": " + numberOfEachResource[i] + "  ";
		return resources;
	}
	
	private int[] getNumberOfEachResource(ArrayList<Tile.Resource> resources){
		int[] resourceArray = {0, 0, 0, 0};
		for(Tile.Resource resource : resources){
			switch (resource.toString()) {
			case "Iron":
				resourceArray[0] += 1;
				break;
			case "Wheat":
				resourceArray[1] += 1;
				break;
			case "Incense":
				resourceArray[2] += 1;
				break;
			case "Silk":
				resourceArray[3] += 1;
				break;
			default:
				System.out.println("Unidentified resource: " + resource.toString());
				break;
			}
		}
		
		return resourceArray;
	}
}
