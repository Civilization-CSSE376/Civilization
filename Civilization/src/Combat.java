import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Combat extends JPanel {
	private Player attacker;
	private Player defender;
	private Player currentPlayer;
	private Boolean inbetween;

	public Combat(Player attacker, Player defender) {
		this.attacker = attacker;
		this.defender = defender;
		this.currentPlayer = defender;
		return;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (inbetween) {
			if (this.currentPlayer == attacker) {
				g2.setColor(Color.RED);
				g2.drawString("Attacker's turn.", 500, 900);
			} else {
				g2.setColor(Color.ORANGE);
				g2.drawString("Defender's turn.", 500, 900);
			}
		} else {
			
		}
	}
}
