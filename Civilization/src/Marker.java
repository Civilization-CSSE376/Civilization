import java.util.ArrayList;


public class Marker {

	public enum MarkerType{
		Building, GreatPerson, Wonder
	};
	
	
	public ArrayList<String> buildingType = new ArrayList<String>();
	public ArrayList<String> greatPersonType = new ArrayList<String>();
	public ArrayList<String> wonderType = new ArrayList<String>();
	
	
	private MarkerType mType;
	private String specificType;
	private int trade = 0;
	private int production = 0;
	private int culture = 0;
	private int coin = 0;
	
	public Marker(MarkerType mType,  String specificType){
		
	}
}
