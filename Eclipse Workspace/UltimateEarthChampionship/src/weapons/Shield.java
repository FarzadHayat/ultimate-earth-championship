package weapons;

public class Shield extends Weapon {
	
	// Name
	public static String name = "Shield";
	
	// Stat Boosts
	private static int damageBoost = 1;
	private static int offenseBoost = -1;
	private static int defenseBoost = 3;
	
	// Price
	private static float startingPrice = 10f;
	private static float priceChangeWeekly = 0.9f;
	
	// Image
	private static String imagePath = "/images/shield.png";
	
	public Shield() {
		super(name, damageBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imagePath);
	}
	
}
