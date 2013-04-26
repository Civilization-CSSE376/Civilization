
public class Unit {

	private static final double LAND_TIER_ONE_CHANCE = 1/5;
	private static final double LAND_TIER_TWO_CHANCE = 3/5;
	private static final double LAND_TIER_THREE_CHANCE = 1/5;
	private static final double AIR_TIER_ONE_CHANCE = 1/3;
	private static final double AIR_TIER_TWO_CHANCE = 1/3;
	private static final double AIR_TIER_THREE_CHANCE = 1/3;
	
	String type;
	int level;
	int attack;
	int health;
	int cost;
	
	public Unit(String type, int level) {
		this.type = type;
		this.level = level;
		if(type.equals("Airplane")){
			this.attack = this.randomAirAttackBase() + this.level - 1;
			this.cost = 12;
					
		}else{
			this.attack = this.randomLandAttackBase() + this.level - 1;
			this.cost = 5 + (this.level - 1)*2;
		}
		
		this.health = this.attack;
	}
	
	/**
	 * Returns a random attackBase based on weight constants
	 * Method written by Martijn Courteaux at
	 * http://stackoverflow.com/questions/6737283/weighted-randomness-in-java
	 * @return
	 */
	private int randomAirAttackBase(){
		int[] attack = {5,6,7};
		//Compute the total weight of all items together
		double totalWeight = 0.0;
		for(int i = 1; i <= attack.length; i++){
			totalWeight += this.getAirChance(i);
		}
		//Choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for(int i = 1; i <= attack.length; i++){
			random -= this.getAirChance(i);
			if(random <= 0.0){
				randomIndex = i;
				break;
			}
		}
		
		return attack[randomIndex];
	}
	
	private int randomLandAttackBase(){
		int[] attack = {1,2,3};
		//Compute the total weight of all items together
		double totalWeight = 0.0;
		for(int i = 1; i <= attack.length; i++){
			totalWeight += this.getLandChance(i);
		}
		//Choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for(int i = 1; i <= attack.length; i++){
			random -= this.getLandChance(i);
			if(random <= 0.0){
				randomIndex = i;
				break;
			}
		}
		
		return attack[randomIndex];
	}
	
	private double getAirChance(int tier){
		switch(tier){
		case 1:
			return Unit.AIR_TIER_ONE_CHANCE;
		case 2:
			return Unit.AIR_TIER_TWO_CHANCE;
		case 3:
			return Unit.AIR_TIER_THREE_CHANCE;
		default:
			return 0;
		}
	}
	
	private double getLandChance(int tier){
		switch(tier){
		case 1:
			return Unit.LAND_TIER_ONE_CHANCE;
		case 2:
			return Unit.LAND_TIER_TWO_CHANCE;
		case 3:
			return Unit.LAND_TIER_THREE_CHANCE;
		default:
			return 0;
		}
	}

}
