package weapons;

public class Sledgehammer extends Weapon {
	
	// Name
	public static String name = "Sledgehammer";
	
	// Stat Boosts
	private static int damageBoost = 1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 10f;
	private static float priceChangeWeekly = 0.9f;
	
	// Image
	private static String imageFileName = "";
	
	public Sledgehammer() {
		super(name, damageBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName);
	}
	
}
