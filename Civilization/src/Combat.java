import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class Combat extends JPanel {
	private Player attacker;
	private Player defender;
	private Player currentPlayer;
	private Boolean inbetween;
	private int defenderBonus;
	private int attackerHandSize = 3;
	private int defenderHandSize = 3;
	private int currentPlayerHandSize = 3;
	private ArrayList<Unit> attackerHand = new ArrayList<Unit>();
	private ArrayList<Unit> defenderHand = new ArrayList<Unit>();
	private ArrayList<Unit> currentPlayerHand = new ArrayList<Unit>();
	private ArrayList<Unit> attackerFront = new ArrayList<Unit>();
	private ArrayList<Unit> defenderFront = new ArrayList<Unit>();
	private ArrayList<Unit> currentPlayerFront = new ArrayList<Unit>();

	public Combat(Player attacker, Player defender, int defenderBonus) {
		this.defenderBonus = defenderBonus;
		this.attacker = attacker;
		this.defender = defender;
		this.currentPlayer = attacker;
		this.switchCurrentPlayer(); //initializes current player properties to defender
		
		this.calculateHandSize();//defender
		this.switchCurrentPlayer();
		this.calculateHandSize();//attacker
		
		this.selectCombatHand();//attacker
		this.switchCurrentPlayer();
		this.selectCombatHand();//defender
		
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
	
	private void switchCurrentPlayer(){
		if(this.currentPlayer.equals(this.attacker)){			
			this.currentPlayer = this.defender;
			this.currentPlayerHand = this.defenderHand;
			this.currentPlayerHandSize = this.defenderHandSize;
			this.currentPlayerFront = this.defenderFront;
		}else{
			this.currentPlayer = this.attacker;
			this.currentPlayerHand = this.attackerHand;
			this.currentPlayerHandSize = this.attackerHandSize;
			this.currentPlayerFront = this.attackerFront;
		}
	}
	
	private void calculateHandSize(){
		//to be implemented when we have variable handsizes
	}
	
	private void selectCombatHand(){
		int numberOfUnits = this.currentPlayer.units.size() < this.currentPlayerHandSize
				? this.currentPlayer.units.size() : this.currentPlayerHandSize;
		
		Collections.shuffle(this.currentPlayer.units);
		
		for(int i = 0; i < numberOfUnits; i++){
			this.currentPlayerHand.add(this.currentPlayer.units.get(i));
		}
		
	}	
	
	public void makeFront(Unit unit){
		this.currentPlayerFront.add(unit);
		this.currentPlayerHand.remove(unit);
	}
	
	public void attackFront(Unit attacking, Unit defending){
		this.currentPlayerHand.remove(attacking);
		Unit goesFirst = this.determineTrump(attacking, defending);
		if(goesFirst == null){//no trump
			attacking.health -= defending.attack;
			defending.health -= attacking.attack;
			
			this.checkDeath(attacking);//attacker
			this.switchCurrentPlayer();
			this.checkDeath(defending);//defender
			this.switchCurrentPlayer();//attacker
		}else{
			if(goesFirst.equals(attacking)){
				defending.health -= attacking.attack;
				if(!this.checkDeath(defending)){
					attacking.health -= defending.attack;
					this.checkDeath(attacking);
				}
			}else{
				attacking.health -= defending.attack;
				if(!this.checkDeath(attacking)){
					defending.health -= attacking.attack;
					this.checkDeath(defending);
				}
			}
		}
	}
	
	private boolean checkDeath(Unit unit){
		if(unit.health <= 0){
			this.currentPlayer.units.remove(unit);
			this.currentPlayerFront.remove(unit);
			return true;
		}
		
		return false;
	}
	
	private Unit determineTrump(Unit attacking, Unit defending){
		switch(attacking.type){
		case "Infantry":
			if(defending.type.equals("Calvary")){
				return attacking;
			}else if(defending.type.equals("Artillery")){
				return defending;
			}
			break;
		case "Calvary":
			if(defending.type.equals("Artillery")){
				return attacking;
			}else if(defending.type.equals("Infantry")){
				return defending;
			}
			break;
		case "Artillery":
			if(defending.type.equals("Infantry")){
				return attacking;
			}else if(defending.type.equals("Calvary")){
				return defending;
			}
			break;
		default:
			return null;
		}
		
		return null;
		
	}
	
	public boolean successfulAttack(){
		int attackingStrength = this.attacker.getPlayerCombatAdvantage();
		int defendingStrength = this.defender.getPlayerCombatAdvantage();
		defendingStrength += this.defenderBonus;
		
		for(Unit u : this.attackerFront){
			attackingStrength += u.attack;
		}
		
		for(Unit u : this.defenderFront){
			defendingStrength += u.attack;
		}
		
		return attackingStrength > defendingStrength ? true : false;
	}
	
}
