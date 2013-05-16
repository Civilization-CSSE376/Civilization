package Civ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Civ.Tile.Resource;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	// Fields...
	private JPanel buttons = makeJPanel(1800, 50, 0, 925);
	private JPanel board = new JPanel();
	private JPanel content = new JPanel();
	private JButton rules = new JButton();
	private JButton player1Details = new JButton();
	private JButton player2Details = new JButton();
	private JButton marketDetails = new JButton();
	private JButton endPhase = new JButton();
	private JButton quit = new JButton();
	private JButton tradeCulture = new JButton();
	private JButton tradeResource = new JButton();
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
		this.tradeCulture.setText(messages.getString("tradeCulture"));
		this.tradeResource.setText(messages.getString("tradeResource"));
		
		this.buttons.add(this.player1Details);
		this.buttons.add(this.player2Details);
		this.buttons.add(this.marketDetails);
		this.buttons.add(this.endPhase);
		this.buttons.add(this.tradeCulture);
		this.buttons.add(this.tradeResource);
		this.buttons.add(this.rules);
		this.buttons.add(this.quit);
		
		this.content.add(this.board);
		
		this.buttons.setBackground(Color.BLACK);

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
		
		this.tradeCulture.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				final JFrame tradeCultureWindow = makeNewWindow(250,100, messages.getString("tradeCulture"));
				tradeCultureWindow.setLayout(new BorderLayout());
				int[] cost = MainWindow.this.cultureTradeCost();
				int cultureCost = cost[0];
				int tradeCost = cost[1];
				final JLabel text = new JLabel(messages.getString("itWillCost") + " " + messages.getString("culture") + cultureCost + " and " + 
						messages.getString("trade1") + " " + tradeCost, SwingConstants.CENTER);
				int progress = Board.currentPlayer.cultureTrackProgress;
				if(progress == 3 || progress == 7 || progress == 12 || progress == 18){
					text.setText(text.getText() + " " + messages.getString("getGreatPerson"));
				}
				tradeCultureWindow.add(text, BorderLayout.PAGE_START);
				JPanel buttonPanel = new JPanel();
				JButton buyButton = new JButton(messages.getString("buy"));
				JButton closeButton = new JButton(messages.getString("close"));
				buttonPanel.add(buyButton);
				buttonPanel.add(closeButton);
				tradeCultureWindow.add(buttonPanel, BorderLayout.PAGE_END);
				tradeCultureWindow.setVisible(true);
				
				buyButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						MainWindow.this.setEnabled(true);
						boolean canBuy = MainWindow.this.canBuy(MainWindow.this.cultureTradeCost());
						boolean getsGreatPerson = false;
						int progress = Board.currentPlayer.cultureTrackProgress;
						if(progress == 3 || progress == 7 || progress == 12 || progress == 18){
							getsGreatPerson = true;
						}
						if(canBuy && getsGreatPerson){
							Board.currentPlayer.greatPeople.add(new GreatPerson(messages));
							tradeCultureWindow.dispose();
							MainWindow.this.tradeCulture.doClick();
						}else if(canBuy){
							tradeCultureWindow.dispose();
							MainWindow.this.tradeCulture.doClick();
						}else{
							text.setText(messages.getString("notEnoughResources"));
							Board.currentPlayer.cultureTrackProgress = 15;
						}
					}
				});
				
				closeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.setEnabled(true);
						tradeCultureWindow.dispose();
						
					}
					
				});
			}
		});
		
		this.tradeResource.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				final JFrame tradeResourceWindow = makeNewWindow(400,200, messages.getString("tradeResource"));
				tradeResourceWindow.setLayout(new BorderLayout());
				final int wheatAmount = 1;
				final int ironAmount = 1;
				final int incenseAmount = 1;
				final int silkAmount = 1;
				final int goldAmount = 1;
				final int unitAmount = 1;
				final int cultureAmount = 2;
				final int tradeAmount = 3;
				JPanel buyPanel = new JPanel();
				JButton wheatButton = new JButton(wheatAmount + " " + messages.getString("wheat")
						+ " : " + goldAmount + " " + messages.getString("gold1"));
				JButton ironButton = new JButton(ironAmount + " " + messages.getString("unit")
						+ " : " + unitAmount + " " + messages.getString("unit"));
				JButton incenseButton = new JButton(incenseAmount + " " + messages.getString("incense")
						+ " : " + cultureAmount + " " + messages.getString("culture1"));
				JButton silkButton = new JButton(silkAmount + " " + messages.getString("silk")
						+ " : " + tradeAmount + " " + messages.getString("trade"));
				JButton[] buttonList = {wheatButton, ironButton, incenseButton, silkButton};
				buyPanel.setLayout(new GridLayout(2, 2));
				for(JButton button : buttonList) buyPanel.add(button);
				
				JPanel closePanel = new JPanel();
				JButton closeButton = new JButton(messages.getString("close"));
				closePanel.add(closeButton, BorderLayout.EAST);
				tradeResourceWindow.add(buyPanel, BorderLayout.CENTER);
				tradeResourceWindow.add(closePanel, BorderLayout.SOUTH);
				tradeResourceWindow.setVisible(true);
				
				closeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.setEnabled(true);
						tradeResourceWindow.dispose();
						
					}
					
				});
				
				wheatButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						MainWindow.this.setEnabled(true);
						int amount = Board.currentPlayer.getResourceAmount("Wheat");
						boolean canBuy = amount >= wheatAmount ? true : false;

						if(canBuy){
							Board.currentPlayer.gold += goldAmount;
							Board.currentPlayer.resources.remove(Resource.Wheat);
							tradeResourceWindow.dispose();
						}
					}
					});
				
				ironButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						MainWindow.this.setEnabled(true);
						int amount = Board.currentPlayer.getResourceAmount("Iron");
						boolean canBuy = amount >= ironAmount ? true : false;
						List<Integer> randomChoices = Arrays.asList(1, 2, 3);

						if(canBuy){
							for(int i = 0; i < unitAmount; i++){
								Collections.shuffle(randomChoices);
								int random = randomChoices.get(0);
								switch(random){
								case 1:
									Board.currentPlayer.units.add(new Unit("Infantry", Board.currentPlayer.infantryLevel));
									break;
								case 2:
									Board.currentPlayer.units.add(new Unit("Cavalry", Board.currentPlayer.cavalryLevel));
									break;
								case 3:
									Board.currentPlayer.units.add(new Unit("Artillery", Board.currentPlayer.artilleryLevel));
									break;
								default:
									break;
								}
							}
							Board.currentPlayer.resources.remove(Resource.Iron);
							tradeResourceWindow.dispose();
						}
					}
					});
				
				incenseButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						MainWindow.this.setEnabled(true);
						int amount = Board.currentPlayer.getResourceAmount("Incense");
						boolean canBuy = amount >= incenseAmount ? true : false;

						if(canBuy){
							Board.currentPlayer.culture += cultureAmount;
							Board.currentPlayer.resources.remove(Resource.Incense);
							tradeResourceWindow.dispose();
						}
					}
					});
				
				silkButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						MainWindow.this.setEnabled(true);
						int amount = Board.currentPlayer.getResourceAmount("Silk");
						boolean canBuy = amount >= silkAmount ? true : false;

						if(canBuy){
							Board.currentPlayer.trade += tradeAmount;
							Board.currentPlayer.resources.remove(Resource.Silk);
							tradeResourceWindow.dispose();
						}
					}
					});
				
				}	
			});
			
				
		this.marketDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int[] cultureLocations = { 25, 70, 125, 180, 235, 290, 345, 400, 455, 510, 565, 620, 675, 730, 790, 845, 905, 960, 1015, 1070, 1125, 1180};
				
				final JFrame marketWindow = makeNewWindow(1225, 685, messages.getString("marketDetails"));
				
				JPanel marketBoard = makePicturePanel(0, -5, 1220, 625, "src/marketBoard.png");
				JPanel buttonPanel = makeJPanel(1235, 40, 0, 620);
				buttonPanel.setBackground(Color.BLACK);
				JButton closeButton = new JButton(messages.getString("close"));
				buttonPanel.add(closeButton);
				
				JPanel player1CultureLocation = makeJPanel(cultureLocations[Board.getPlayer(1).cultureTrackProgress], 540, 15, 25);
				player1CultureLocation.setBackground(Color.RED);
				JPanel player2CultureLocation = makeJPanel(cultureLocations[Board.getPlayer(2).cultureTrackProgress], 570, 15, 25);
				player2CultureLocation.setBackground(Color.YELLOW);
				JPanel[] panelList = {player1CultureLocation, player2CultureLocation, marketBoard, buttonPanel};
				for(JPanel panel : panelList) marketWindow.add(panel);
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


	private void makePlayerWindow(String windowName, String playerCivilizationField, final ResourceBundle messages) {
		final int playerNum;
		if(windowName.equals(messages.getString("player1Details"))) playerNum = 1;
		else playerNum = 2;
		
		final JFrame playerWindow = makeNewWindow(900, 565, windowName);
		
		JPanel buttonPanel = makeJPanel(345, 40, 550, 500);
		JPanel civPic = makePicturePanel(0, -6, 550, 565, "src/civs/" + playerCivilizationField + ".png");
		JPanel info = makeJPanel(295, 500, 600, 0);
		JPanel buffer = makeJPanel(50, 500, 550, 0);
		JPanel[] panelList = {buttonPanel, civPic, info, buffer};
		info.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);
		buffer.setBackground(Color.BLACK);
		
		JButton closeButton = new JButton(messages.getString("close"));
		JButton techTree = new JButton(messages.getString("playerTechTree"));
		buttonPanel.add(closeButton);
		buttonPanel.add(techTree);

		info.setLayout(new GridLayout(6, 1));
		String gov = "";
		if(playerCivilizationField.equals("Rome")) gov = messages.getString("republic");
		else if(playerCivilizationField.equals("Russia")) gov = messages.getString("communism");
		else gov = messages.getString("despotism");
		
		JLabel government = new JLabel(messages.getString("government") + gov);
		government.setForeground(Color.WHITE);
		
		JLabel governmentAbility = new JLabel(messages.getString("ability"));
		governmentAbility.setForeground(Color.WHITE);
		
		JLabel trade = new JLabel(messages.getString("trade") + ": " + Board.getPlayer(playerNum).trade);
		trade.setForeground(Color.WHITE);
		
		JLabel gold = new JLabel(messages.getString("gold") + Board.getPlayer(playerNum).gold);
		gold.setForeground(Color.WHITE);
		
		JLabel culture = new JLabel(messages.getString("culture") + Board.getPlayer(playerNum).culture);
		culture.setForeground(Color.WHITE);
		
		String resourceString = "";
		resourceString = getResourceString(playerNum, messages);
		
		JLabel resources = new JLabel(messages.getString("resources") + resourceString);
		resources.setForeground(Color.WHITE);
		JLabel[] labelList = {government, governmentAbility, trade, gold, resources, culture};
		for(JLabel label : labelList) info.add(label);
		
		
		for(JPanel panel : panelList) playerWindow.add(panel);
		
		playerWindow.setVisible(true);
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setEnabled(true);
				playerWindow.dispose();
			}
			
		});
		
		techTree.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame treeWindow = new JFrame(messages.getString("playerTechCardTree"));
				treeWindow.setLayout(null);
				treeWindow.setSize(525, 370);
				
				treeWindow.add(Board.drawTechCardTree(Board.getPlayer(playerNum)));
				
				JPanel button = new JPanel();
				button.setLocation(0, 290);
				button.setSize(525, 50);
				JButton close = new JButton(messages.getString("close"));
				button.add(close);
				treeWindow.add(button);
				
				treeWindow.setAlwaysOnTop(true);
				treeWindow.setVisible(true);
				
				close.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						treeWindow.dispose();
					}
					
				});
				
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
	
	private int[] cultureTradeCost(){
		int[] cost = new int[2];
		int progress = Board.currentPlayer.cultureTrackProgress;
		if(progress < 8){
			cost[0] = 3;
			cost[1] = 0;
		}else if(progress < 15){
			cost[0] = 5;
			cost[1] = 3;
		}else if(progress < 21){
			cost[0] = 7;
			cost[1] = 5;
		}else{
			//they just won by culture
			cost[0] = 7;
			cost[1] = 5;
			Board.currentPlayer.hasWon = true;
			Board.currentPlayer.winCondition = "Culture";
		}
		return cost;
	}
	
	private boolean canBuy(int[] cost){
		if(Board.currentPlayer.culture >= cost[0] && Board.currentPlayer.trade >= cost[1]){
			Board.currentPlayer.culture -= cost[0];
			Board.currentPlayer.trade -= cost[1];
			Board.currentPlayer.cultureTrackProgress += 1;
			return true;
		}
		return false;
	}
	
	public JPanel makeJPanel(int width, int height, int xLocation, int yLocation){
		JPanel panel = new JPanel();
		panel.setLocation(xLocation, yLocation);
		panel.setSize(width, height);
		return panel;
	}
}
