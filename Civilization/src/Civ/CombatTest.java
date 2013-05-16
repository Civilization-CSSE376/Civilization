package Civ;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

public class CombatTest {
	private static Locale currentLocale = new Locale("en", "US");
	private static ResourceBundle messages = ResourceBundle.getBundle(
			"MessagesBundle", currentLocale);

	Combat combat;

	@Before
	public void setup() {
		ArrayList<Unit> p1Units = new ArrayList<Unit>();
		p1Units.add(new Unit("Cavalry", 2, messages));
		p1Units.add(new Unit("Artillery", 3, messages));
		p1Units.add(new Unit("Infantry", 1, messages));
		Player player1 = new Player(messages);
		// player1.units = p1Units;
		ArrayList<Unit> p2Units = new ArrayList<Unit>();
		p2Units.add(new Unit("Cavalry", 2, messages));
		p2Units.add(new Unit("Artillery", 3, messages));
		p2Units.add(new Unit("Infantry", 1, messages));
		Player player2 = new Player(messages);
		// player2.units = p2Units;
		this.combat = new Combat(player1, player2, 0, messages);
		this.combat.attacker.units = p1Units;
		this.combat.defender.units = p2Units;

	}

	@Test
	public void testMakeFront() {
		Unit frontUnit = combat.getCurrentPlayer().units.get(0);
		this.combat.makeFront(frontUnit);
		assertEquals(frontUnit, combat.defenderFront.get(0));
		assertEquals(combat.attacker, combat.getCurrentPlayer());
		assertEquals(-1, combat.defenderHand.indexOf(frontUnit));
	}

	@Test
	public void testSwitchCurrentPlayer() {
		assertEquals(combat.defender, combat.getCurrentPlayer());
		combat.switchCurrentPlayer();
		assertEquals(combat.attacker, combat.getCurrentPlayer());
		combat.switchCurrentPlayer();
		assertEquals(combat.defender, combat.getCurrentPlayer());
	}

	@Test
	public void testDetermineTrumpCavalry() {
		Unit attacking = combat.attacker.units.get(0);
		Unit defending = combat.defender.units.get(0);
		assertNull(combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(1);
		assertEquals(attacking, combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(2);
		assertEquals(defending, combat.determineTrump(attacking, defending));
	}

	@Test
	public void testDetermineTrumpArtillary() {
		Unit attacking = combat.attacker.units.get(1);
		Unit defending = combat.defender.units.get(0);
		assertEquals(defending, combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(1);
		assertNull(combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(2);
		assertEquals(attacking, combat.determineTrump(attacking, defending));
	}

	@Test
	public void testDetermineTrumpInfantry() {
		Unit attacking = combat.attacker.units.get(2);
		Unit defending = combat.defender.units.get(0);
		assertEquals(attacking, combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(1);
		assertEquals(defending, combat.determineTrump(attacking, defending));
		defending = combat.defender.units.get(2);
		assertNull(combat.determineTrump(attacking, defending));
	}

	@Test
	public void testCheckDeathDead() {
		Unit unit = this.combat.getCurrentPlayer().units.get(0);
		this.combat.currentPlayerFront.add(unit);
		unit.health = 0;
		assertTrue(combat.checkDeath(unit));
		assertEquals(-1, this.combat.currentPlayer.units.indexOf(unit));
		assertEquals(-1, this.combat.currentPlayerFront.indexOf(unit));
	}

	@Test
	public void testCheckDeathAlive() {
		Unit unit = this.combat.getCurrentPlayer().units.get(0);
		this.combat.currentPlayerFront.add(unit);
		unit.health = 1;
		assertFalse(combat.checkDeath(unit));
		assertEquals(0, this.combat.currentPlayer.units.indexOf(unit));
		assertEquals(0, this.combat.currentPlayerFront.indexOf(unit));
	}

	@Test//this test is brittle due to the randomness
	public void testAttackFrontNoTrump() {
		this.combat.makeFront(combat.defender.units.get(0));
		Unit attacking = combat.attacker.units.get(0);
		Unit defending = combat.defenderFront.get(0);
		combat.attackFront(attacking, defending);
		assertEquals(0, combat.defenderFront.size());
		assertEquals(-1, combat.attackerHand.indexOf(attacking));
		assertEquals(-1, combat.defenderFront.indexOf(defending));
	}

	@Test//this test is brittle due to the randomness
	public void testAttackFrontAttackingFirst() {
		this.combat.makeFront(combat.defender.units.get(1));
		Unit attacking = combat.attacker.units.get(0);
		Unit defending = combat.defenderFront.get(0);
		combat.attackFront(attacking, defending);
		assertEquals(1, combat.defenderFront.size());
		assertEquals(-1, combat.attackerHand.indexOf(attacking));
		assertEquals(0, combat.defenderFront.indexOf(defending));
	}

	@Test//this test is brittle due to the randomness
	public void testAttackFrontAttackingSecond() {
		this.combat.makeFront(combat.defender.units.get(2));
		Unit attacking = combat.attacker.units.get(0);
		Unit defending = combat.defenderFront.get(0);
		combat.attackFront(attacking, defending);
		assertEquals(0, combat.defenderFront.size());
		assertEquals(-1, combat.attackerHand.indexOf(attacking));
		assertEquals(-1, combat.defenderFront.indexOf(defending));
	}

	@Test//this test is brittle due to the randomness
	public void testSuccessfulAttack() {
		this.combat.attackerFront.add(this.combat.attackerHand.get(0));
		this.combat.attackerFront.add(this.combat.attackerHand.get(1));
		this.combat.defenderFront.add(this.combat.defenderHand.get(0));

		assertTrue(this.combat.successfulAttack());

		this.combat.defenderFront.add(this.combat.defenderHand.get(1));
		this.combat.defenderFront.add(this.combat.defenderHand.get(2));

		assertFalse(this.combat.successfulAttack());
	}

}
