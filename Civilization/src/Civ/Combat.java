package Civ;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Combat extends JFrame {
	public Player attacker;
	public Player defender;
	public Player currentPlayer;
	public Boolean inbetween = true;
	public int defenderBonus;
	public int attackerHandSize = 3;
	public int defenderHandSize = 3;
	public int currentPlayerHandSize = 3;
	public Unit ownUnit = null;
	public ArrayList<Unit> attackerHand = new ArrayList<Unit>();
	public ArrayList<Unit> defenderHand = new ArrayList<Unit>();
	public ArrayList<Unit> currentPlayerHand = new ArrayList<Unit>();
	public ArrayList<Unit> attackerFront = new ArrayList<Unit>();
	public ArrayList<Unit> defenderFront = new ArrayList<Unit>();
	public ArrayList<Unit> currentPlayerFront = new ArrayList<Unit>();
	public Unit enemy = null;
	private JPanel enemyFrontManager = new JPanel();
	private JPanel playerHandManager = new JPanel();
	private ArrayList<Unit> enemyFront;

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

		drawCombatWindow(attacker);

	}

	private void drawCombatWindow(Player attacker) {
		this.setLayout(null);
		this.setTitle("Combat!");
		ImageIcon icon = new ImageIcon("src/civilizationicon.jpg");
		this.setIconImage(icon.getImage());

		this.setLayout(new GridLayout(7, 1));

		JButton attack = new JButton("Attack");
		attack.addActionListener(new attackButtonListener());

		JButton front = new JButton("Front");
		front.addActionListener(new frontButtonListener());

		if (this.attacker == attacker) {
			this.add(new JLabel("Attacker's turn", JLabel.CENTER));
		} else {
			this.add(new JLabel("Defender's turn", JLabel.CENTER));
		}
		this.add(new JLabel("Player Hand"), JLabel.CENTER);
		drawHand(this.currentPlayerHand);
		this.add(this.playerHandManager);
		this.add(new JLabel("Enemy Front"), JLabel.CENTER);
		drawFront(this.enemyFront);
		this.add(this.enemyFrontManager);
		this.add(front);
		this.add(attack);
		this.setSize(1000, 600);
		this.setAlwaysOnTop(true);
		this.validate();
		this.setVisible(true);
	}

	public class attackButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (enemy != null && ownUnit != null) {
				Combat.this.attackFront(ownUnit, enemy);
				ownUnit = null;
				enemy = null;
				switchCurrentPlayer();
				Combat.this.enemyFrontManager.removeAll();
				Combat.this.playerHandManager.removeAll();
				Combat.this.getContentPane().removeAll();
				Combat.this.validate();
				Combat.this.drawCombatWindow(Combat.this.currentPlayer);
				Combat.this.repaint();
				Combat.this.revalidate();
			}
		}
	}

	public class frontButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Combat.this.ownUnit != null) {
				Combat.this.makeFront(Combat.this.ownUnit);
				Combat.this.ownUnit = null;
				Combat.this.enemyFrontManager.removeAll();
				Combat.this.playerHandManager.removeAll();
				Combat.this.getContentPane().removeAll();
				Combat.this.validate();
				Combat.this.drawCombatWindow(Combat.this.currentPlayer);
				Combat.this.repaint();
				Combat.this.revalidate();
			}
		}
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	private void drawFront(ArrayList<Unit> front) {
		ButtonGroup frontChoices = new ButtonGroup();

		for (int i = 0; i < front.size(); i++) {
			JRadioButton chooseEnemy = new JRadioButton(front.get(i).toString());
			chooseEnemy.addActionListener(new EnemyFrontRadioListener());
			if (i == 0) {
				chooseEnemy.setSelected(true);
				this.enemy = front.get(i);
			}
			frontChoices.add(chooseEnemy);
			this.enemyFrontManager.add(chooseEnemy);
		}

	}

	public class EnemyFrontRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			for (Unit unit : Combat.this.enemyFront) {
				if (s.equals(unit.toString())) {
					Combat.this.enemy = unit;
				}
			}
		}

	}

	private void drawHand(ArrayList<Unit> hand) {
		ButtonGroup frontChoices = new ButtonGroup();

		for (int i = 0; i < hand.size(); i++) {
			JRadioButton chooseUnit = new JRadioButton(hand.get(i).toString());
			chooseUnit.addActionListener(new PlayerHandRadioListener());
			if (i == 0) {
				chooseUnit.setSelected(true);
				this.enemy = hand.get(i);
			}
			frontChoices.add(chooseUnit);
			this.playerHandManager.add(chooseUnit);
		}
	}

	public class PlayerHandRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			for (Unit unit : Combat.this.currentPlayerHand) {
				if (s.equals(unit.toString())) {
					Combat.this.ownUnit = unit;
				}
			}
		}

	}

	public void switchCurrentPlayer() {
		if (this.currentPlayer.equals(this.attacker)) {
			this.currentPlayer = this.defender;
			this.currentPlayerHand = this.defenderHand;
			this.currentPlayerHandSize = this.defenderHandSize;
			this.currentPlayerFront = this.defenderFront;
			this.enemyFront = this.attackerFront;
		} else {
			this.currentPlayer = this.attacker;
			this.currentPlayerHand = this.attackerHand;
			this.currentPlayerHandSize = this.attackerHandSize;
			this.currentPlayerFront = this.attackerFront;
			this.enemyFront = this.defenderFront;
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
				switchCurrentPlayer();// defender
				if (!this.checkDeath(defending)) {
					attacking.health -= defending.attack;
				}
				switchCurrentPlayer(); // attacker
				if (!this.checkDeath(attacking)) {
					this.currentPlayerFront.add(attacking);
				}
			} else {
				attacking.health -= defending.attack;
				if (!this.checkDeath(attacking)) {
					defending.health -= attacking.attack;
					switchCurrentPlayer();// defender
					this.checkDeath(defending);
					switchCurrentPlayer();// attacker
					this.currentPlayerFront.add(attacking);
				}
			}
		}
	}

	public boolean checkDeath(Unit unit) {
		if (unit.health <= 0) {
			this.currentPlayer.units.remove(unit);
			this.currentPlayerFront.remove(unit);
			return true;
		}

		return false;
	}

	public Unit determineTrump(Unit attacking, Unit defending) {
		switch (attacking.type) {
		case "Infantry":
			if (defending.type.equals("Cavalry")) {
				return attacking;
			} else if (defending.type.equals("Artillery")) {
				return defending;
			}
			break;
		case "Cavalry":
			if (defending.type.equals("Artillery")) {
				return attacking;
			} else if (defending.type.equals("Infantry")) {
				return defending;
			}
			break;
		case "Artillery":
			if (defending.type.equals("Infantry")) {
				return attacking;
			} else if (defending.type.equals("Cavalry")) {
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
			// this.attacker.units.add(u);
		}

		for (Unit u : this.defenderFront) {
			defendingStrength += u.attack;
			// this.defender.units.add(u);
		}
		
		if(this.defenderBonus == 12 || this.defenderBonus == 16){
			this.attacker.hasWon = true;
			this.attacker.winCondition = "Military";
			Board.isGameOver = true;
		}

		return attackingStrength > defendingStrength ? true : false;
	}

}
