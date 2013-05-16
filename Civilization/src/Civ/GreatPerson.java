package Civ;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GreatPerson extends Marker {
	private List<String> possibleGreatPeople = 
			Arrays.asList("Poet", "Prophet", "Explorer", "General", "Scientist", "Merchant");
	public boolean isInReserve = true; // I don't know if we need this but this
										// keeps
										// track of if the GP is on the board or
										// not

	public GreatPerson(String name, ResourceBundle messages) {
		super(name);
		this.createGreatPerson(name);
		this.allowedTerrain = Terrain.NotWater;
	}
	
	public GreatPerson(ResourceBundle messages){
		super();
		Collections.shuffle(this.possibleGreatPeople);
		this.name = this.possibleGreatPeople.get(0);
		this.createGreatPerson(name);
		this.allowedTerrain = Terrain.NotWater;
	}

	private void createGreatPerson(String name) {
		switch (name) {
		case "Poet":
			this.trade = 1;
			this.culture = 2;
			break;
		case "Prophet":
			this.production = 1;
			this.culture = 1;
			this.trade = 1;
			this.coin = 1;
			break;
		case "Explorer":
			this.culture = 2;
			this.coin = 1;
			break;
		case "General":
			this.combatAdvantage = 4;
			break;
		case "Scientist":
			this.production = 1;
			this.trade = 2;
			break;
		case "Merchant":
			this.production = 2;
			this.coin = 1;
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		super.draw(g2, c);
	}

}
