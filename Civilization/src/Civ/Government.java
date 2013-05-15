package Civ;

public class Government {

	String name = "";
	
	public Government(Player p){
		this.name = "Despotism";
		this.becomeGovernment(p, this.name);
	}
	
	public Government(Player p, String name) {
		this.name = name;
		this.becomeGovernment(p, this.name);
	}
	
	private void becomeGovernment(Player p, String name){
		this.clear(p, name);
		switch(name){
		case "Feudalism":
			p.gold++;
			break;
		case "Monarchy":
			p.handSize++;
			break;
		case "Anarachy":
			City capital = getCapital(p);
			capital.setHasAction(false);
			break;
		case "Fundamentalism":
			p.battleHandSize += 1;
		default:
			break;
		}
	}
	
	private void clear(Player p, String name){
		
		switch(name){
		case "Feudalism":
			p.gold--;
			break;
		case "Monarchy":
			p.handSize--;
			break;
		case "Anarchy":
			City capital = getCapital(p);
			capital.setHasAction(true);
		case "Fundamentalism":
			p.battleHandSize -= 1;
		default:
			break;
		}
	}
	
	private City getCapital(Player p){
		City capital = null;
		for(City c: p.cities){
			if(c.getIsCapital()){
				capital = c;
			}
		}
		
		return capital;
	}

}
