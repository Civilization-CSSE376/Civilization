package Civ;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

public class Army extends Figure {

	public Army(Player player, Tile tile) {
		super(player, tile);
		//this.location = tile;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

//	@Override
//	public Boolean tryToBuildCity(Tile tile, Player player, City city)
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public boolean takeHut(Player player) {
		return true;
	}

	@Override
	public void draw(Graphics2D g2, Color c) {
		super.draw(g2, c);
		g2.drawString("A", (float) this.getLocation().x,
				(float) this.getLocation().y);
		
	}

}
