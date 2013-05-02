package Civ;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public abstract class Figure implements Drawable {
	protected Tile location;
	private Player owner;
	protected Point2D.Double screenLocation;
	private boolean usedThisTurn = false;
	private int moves = 0;

	public Figure(Player player, Tile local) {
		this.owner = player;
		this.location = local;
		// this.screenLocation = new Point2D.Double(local.getxPos() * 440,
		// local.getyPos() * 440);
		this.screenLocation = new Point2D.Double(local.getxPos() * 550 + 55,
				local.getyPos() * 256 + 55);
		System.out.println(local.getxPos());
		System.out.println(local.getyPos());
	}

	public int getNumberOfMoves() {
		return this.moves;
	}

	public void resetMoves(int playerSpeed) {
		this.moves = playerSpeed;
	}

	public void decreaseMoves() {
		this.moves -= 1;
	}

	public boolean getUsedThisTurn() {
		return this.usedThisTurn;
	}

	public boolean tryToMove(int Panel1, Tile oldTile, int Panel2, Tile newTile) {

		return false;
	}

	public void setUsedThisTurn(Boolean used) {
		this.usedThisTurn = used;
	}

	public Point2D.Double getLocation() {
		return this.screenLocation;
	}

	public void setTileLocal(Tile newlocal) {
		this.location = newlocal;
	}

	public void setLocation(int x, int y) {
		int newX = 0;
		int newY = 0;
		int checkLocation2 = 220;

		if (x >= 0 && x <= 1760 && y >= 0 && y <= 880) {

			if (x <= 110)
				newX = 55;

			while (newX == 0) {
				if (x <= checkLocation2)
					newX = checkLocation2 + 55;
				else
					checkLocation2 += 110;
			}

			if (y < 110)
				newY = 55;

			checkLocation2 = 220;
			while (newY == 0) {
				if (y < checkLocation2)
					newY = checkLocation2 - 55;
				else
					checkLocation2 += 110;
			}
			this.screenLocation = new Point2D.Double(newX, newY);
		} else
			System.out.println("\nInvalid location -- cannot move player.");
	}

	public abstract void move();

	public Boolean tryToBuildCity(Tile tile, Player player, City city) {
		return false;
	}

	public abstract void takeHut();

	public Player getOwner() {
		return this.owner;
	}

	public void setScreenLocation(Point2D.Double screenLocation2) {
		this.screenLocation = new Point2D.Double(screenLocation2.x + 55,
				screenLocation2.y + 55);
	}

	public static Figure getFigure(Player p, String figureName, Tile tile) {
		if (figureName.equals("Army"))
			return new Army(p, tile);
		else if (figureName.equals("Settler"))
			return new Settler(p, tile);
		else
			return null;
	}
	
	@Override
	public void draw(Graphics2D g2, Color c){
		Ellipse2D.Double player1 = new Ellipse2D.Double(
				this.getLocation().x - 25, this.getLocation().y - 25,
				50, 50);
		g2.setColor(c);
		g2.fill(player1);
		g2.setColor(Color.black);
	}
}
