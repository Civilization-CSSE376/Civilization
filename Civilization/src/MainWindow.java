import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame{
	
	/**
	 * 
	 */
	// Not sure why this is needed, but it is.
	private static final long serialVersionUID = 1L;

	private JPanel buttons = new JPanel();
	
	private JButton rules = new JButton("Rules");
	
	public MainWindow(){
		this.setTitle("Civilization");
		
		
		
		this.rules.setAlignmentX(RIGHT_ALIGNMENT);
		this.rules.setAlignmentY(BOTTOM_ALIGNMENT);
		this.rules.setVisible(true);
		this.buttons.add(this.rules);
		
		this.buttons.setVisible(true);
		this.add(this.buttons);
		
		
		
		
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
