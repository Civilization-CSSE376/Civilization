import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

public class Combat extends JPanel {
	private Player attacker;
	private Player defender;
	private Player currentPlayer;
	private Boolean inbetween = true;
	private int defenderBonus;
	private int attackerHandSize = 3;
	private int defenderHandSize = 3;
	private int currentPlayerHandSize = 3;
	private Unit ownUnit = null;
	private ArrayList<Unit> attackerHand = new ArrayList<Unit>();
	private ArrayList<Unit> defenderHand = new ArrayList<Unit>();
	private ArrayList<Unit> currentPlayerHand = new ArrayList<Unit>();
	private ArrayList<Unit> attackerFront = new ArrayList<Unit>();
	private ArrayList<Unit> defenderFront = new ArrayList<Unit>();
	private ArrayList<Unit> currentPlayerFront = new ArrayList<Unit>();
	private JRadioButtonMenuItem[] items;

	public Combat(Player attacker, Player defender, int defenderBonus) {
		this.defenderBonus = defenderBonus;
		this.attacker = attacker;
		this.defender = defender;
		this.currentPlayer = attacker;
		this.switchCurrentPlayer(); // initializes current player properties to
									// defender

		this.calculateHandSize();// defender
		this.switchCurrentPlayer();
		this.calculateHandSize();// attacker

		this.selectCombatHand();// attacker
		this.switchCurrentPlayer();
		this.selectCombatHand();// defender

		EnvironmentHandler mouseHandler = new EnvironmentHandler();
		this.addMouseListener(mouseHandler);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		System.out.println("Made it here");
		if (inbetween) {
			if (this.currentPlayer == attacker) {
				g2.setColor(Color.RED);
				g2.drawString("Attacker's turn.", 100, 300);
			} else {
				g2.setColor(Color.ORANGE);
				g2.drawString("Defender's turn.", 100, 300);
			}
		} else {
			if (ownUnit != null)
				;
			// ownHandGetter();
		}
	}

	private void ownHandGetter() {
		String[] choices = new String[currentPlayerHand.size()];
		int i = 0;
		for (Unit unit : currentPlayerHand) {
			choices[i] = "Unit: " + unit.type + " " + unit.attack;
			i++;
		}
		makeChoice(choices, new chooseOwnUnit(), new Point(100, 100));
	}

	private class chooseOwnUnit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					String[] choices = { "Make Front", "Attack" };
					ownUnit = currentPlayerHand.get(i);
					makeChoice(choices, new choiceHandler(),
							new Point(100, 100));
					return;
				}
		}
	}

	private class chooseEnemyUnit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which menu item was selected
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					ArrayList<Unit> opponentFront;
					if (currentPlayer.equals(attacker))
						opponentFront = defenderFront;
					else
						opponentFront = attackerFront;
					Unit enemyUnit = opponentFront.get(i);
					attackFront(ownUnit, enemyUnit);
					ownUnit = null;
					switchCurrentPlayer();
					return;
				}
		}
	}

	private class choiceHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < items.length; i++)
				if (e.getSource() == items[i]) {
					dealWithChoice(items[i].getText());
				}
		}
	}

	public void makeChoice(String[] choices, ActionListener handler, Point point) {
		JPopupMenu menu = new JPopupMenu();
		ButtonGroup group = new ButtonGroup();
		items = new JRadioButtonMenuItem[choices.length + 1];
		for (int i = 0; i < choices.length; i++) {
			items[i] = new JRadioButtonMenuItem(choices[i]);
			menu.add(items[i]);
			group.add(items[i]);
			items[i].addActionListener(handler);
		}
		items[items.length - 1] = new JRadioButtonMenuItem("Cancel");
		menu.add(items[items.length - 1]);
		group.add(items[items.length - 1]);
		items[items.length - 1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				return;
			}
		});
		menu.show(this, point.x, point.y);
	}

	public void dealWithChoice(String text) {
		if (text.equals("Make Front")) {
			makeFront(ownUnit);
			ownUnit = null;
		} else {
			chooseEnemy();
		}
	}

	private void chooseEnemy() {
		ArrayList<Unit> otherPlayersHand;
		if (currentPlayer.equals(attacker))
			otherPlayersHand = attackerHand;
		else
			otherPlayersHand = defenderHand;
		String[] choices = new String[otherPlayersHand.size()];
		int i = 0;
		for (Unit unit : otherPlayersHand) {
			choices[i] = "Unit: " + unit.type + " " + unit.attack;
		}
		makeChoice(choices, new chooseEnemyUnit(), new Point(100, 100));
	}

	private void switchCurrentPlayer() {
		if (this.currentPlayer.equals(this.attacker)) {
			this.currentPlayer = this.defender;
			this.currentPlayerHand = this.defenderHand;
			this.currentPlayerHandSize = this.defenderHandSize;
			this.currentPlayerFront = this.defenderFront;
		} else {
			this.currentPlayer = this.attacker;
			this.currentPlayerHand = this.attackerHand;
			this.currentPlayerHandSize = this.attackerHandSize;
			this.currentPlayerFront = this.attackerFront;
		}
		repaint();
	}

	private void calculateHandSize() {
		// to be implemented when we have variable handsizes
	}

	private void selectCombatHand() {
		int numberOfUnits = this.currentPlayer.units.size() < this.currentPlayerHandSize ? this.currentPlayer.units
				.size() : this.currentPlayerHandSize;

		Collections.shuffle(this.currentPlayer.units);

		for (int i = 0; i < numberOfUnits; i++) {
			this.currentPlayerHand.add(this.currentPlayer.units.get(i));
		}

	}

	public void makeFront(Unit unit) {
		this.currentPlayerFront.add(unit);
		this.currentPlayerHand.remove(unit);
		switchCurrentPlayer();
	}

	public void attackFront(Unit attacking, Unit defending) {
		this.currentPlayerHand.remove(attacking);
		Unit goesFirst = this.determineTrump(attacking, defending);
		if (goesFirst == null) {// no trump
			attacking.health -= defending.attack;
			defending.health -= attacking.attack;

			this.checkDeath(attacking);// attacker
			this.switchCurrentPlayer();
			this.checkDeath(defending);// defender
			this.switchCurrentPlayer();// attacker
		} else {
			if (goesFirst.equals(attacking)) {
				defending.health -= attacking.attack;
				if (!this.checkDeath(defending)) {
					attacking.health -= defending.attack;
					this.checkDeath(attacking);
				}
			} else {
				attacking.health -= defending.attack;
				if (!this.checkDeath(attacking)) {
					defending.health -= attacking.attack;
					this.checkDeath(defending);
				}
			}
		}
	}

	private boolean checkDeath(Unit unit) {
		if (unit.health <= 0) {
			this.currentPlayer.units.remove(unit);
			this.currentPlayerFront.remove(unit);
			return true;
		}

		return false;
	}

	private Unit determineTrump(Unit attacking, Unit defending) {
		switch (attacking.type) {
		case "Infantry":
			if (defending.type.equals("Calvary")) {
				return attacking;
			} else if (defending.type.equals("Artillery")) {
				return defending;
			}
			break;
		case "Calvary":
			if (defending.type.equals("Artillery")) {
				return attacking;
			} else if (defending.type.equals("Infantry")) {
				return defending;
			}
			break;
		case "Artillery":
			if (defending.type.equals("Infantry")) {
				return attacking;
			} else if (defending.type.equals("Calvary")) {
				return defending;
			}
			break;
		default:
			return null;
		}

		return null;

	}

	public boolean successfulAttack() {
		int attackingStrength = this.attacker.getPlayerCombatAdvantage();
		int defendingStrength = this.defender.getPlayerCombatAdvantage();
		defendingStrength += this.defenderBonus;

		for (Unit u : this.attackerFront) {
			attackingStrength += u.attack;
		}

		for (Unit u : this.defenderFront) {
			defendingStrength += u.attack;
		}

		return attackingStrength > defendingStrength ? true : false;
	}

	public class EnvironmentHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (currentPlayerHand.size() > 0) {
				if (attackerHand.size() == 0 && defenderHand.size() == 0) {
					switchCurrentPlayer();
					repaint();
				} else {
					ownHandGetter();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Do nothing.

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Do nothing.

		}
	}

}
