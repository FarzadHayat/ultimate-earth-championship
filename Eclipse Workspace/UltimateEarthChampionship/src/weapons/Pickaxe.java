package weapons;

public class Pickaxe extends Weapon {
	
	// Name
	public static String name = "Pickaxe";
	
	// Stat Boosts
	private static int damageBoost = 1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 10f;
	private static float priceChangeWeekly = 0.9f;
	
	// Image
	private static String imagePath = "";
	
	public Pickaxe() {
		super(name, damageBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imagePath);
	}
	
}
