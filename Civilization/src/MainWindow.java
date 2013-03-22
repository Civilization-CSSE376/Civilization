import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	private JPanel buttons = new JPanel();
	private JPanel board = new JPanel();
	
	private JButton rules = new JButton("Rules");
	private JButton player1Details = new JButton("Player 1 Details");
	private JButton player2Details = new JButton("Player 2 Details");
	private JButton marketDetails = new JButton("Market Details");
	
	private String language;
	private String civilization;
	
	public MainWindow(String languageChosen, String civilizationChosen){
		this.language = languageChosen;
		this.civilization = civilizationChosen;
		
		this.setTitle("Civilization");
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		this.setIconImage(icon.getImage());
		
		this.buttons.add(this.player1Details);
		this.buttons.add(this.player2Details);
		this.buttons.add(this.marketDetails);
		this.buttons.add(this.rules);
		
		this.add(this.buttons, BorderLayout.SOUTH);
		
		this.rules.setVisible(true);
		this.buttons.setVisible(true);
		
//		this.board.add(new Board());
		
		this.rules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Desktop.isDesktopSupported()){
					try{
						File rulespdf = new File("src/civilization-rules.pdf");
						Desktop.getDesktop().open(rulespdf);
					}
					catch(IOException ex){
						System.out.println("Failed to open pdf.");
					}
				}
				
			}
			
		});
		
	}
}
