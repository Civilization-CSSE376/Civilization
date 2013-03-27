
public abstract class Figure {
	protected Tile location;
	private Player owner;

	public abstract void move();

	public void makeCity() {

	}

	public abstract void takeHut();
	
	public Player getOwner(){
		return this.owner;
	}
}
