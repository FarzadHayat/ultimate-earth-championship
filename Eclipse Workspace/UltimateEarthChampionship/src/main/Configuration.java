package main;

public class Configuration {
	
	/**
	 *  This class holds many base configuration values used in calculations across the game
	 *  This class also holds information regarding difficulty.
	 *  
	 *  Follows the singleton design pattern
	 */
	
	private static Configuration instance;
	
	private Configuration()
	{
		// Prevent other classes from instantiating this class
	}
	
	// Called at loading time
	static {
		instance = new Configuration();
	}
	
	public static Configuration getInstance()
	{
		return instance;
	}
	
	
	/**
	 *  Global difficulty modifier, should exist on a scale between 0.1 - 2.
	 */
	private float difficulty;
	
	
	/**
	 *  How much the maximum xp of a champion is multiplied by upon a level up, e.g: 1.2f for a 20% increase each level up
	 */
	public final float XP_INCREMENT_MODIFIER = 1.2f;
	
	/**
	 *  The maximum amount of XP each champion needs to reach level 1
	 */
	public final float XP_DEFAULT_MAX = 100f;
	
	/**
	 *  Modifier of XP gained, Multiplied by damage done and damaged blocked to find xp gained.
	 */
	public final float XP_GAIN_MODIFIER = 0.5f;
	
	/**
	 *  The default max health of each champion, before skill points are accounted for
	 */
	public final float MAX_HEALTH_DEFAULT = 100f;
	
	/**
	 *  The default max stamina of each champion, before skill points are accounted for 
	 */
	public final float MAX_STAMINA_DEFAULT = 30f;
	
	/**
	 *  The amount that a skill point into health will increment maximum health of a champion
	 */
	public final float SKILL_HEALTH_INCREMENT = 10f;
	
	/**
	 *  The amount that a skill point into stamina will increment stamina health of a champion
	 */
	public final float SKILL_STAMINA_INCREMENT = 5f;
	
	/**
	 *  The default amount of offense stat that a champion has, before skill points are accounted for
	 */
	public final int SKILL_DEFAULT_OFFENSE = 1;
	
	/**
	 *  The default amount of defense stat that a champion has, before skill points are accounted for
	 */
	public final int SKILL_DEFAULT_DEFENSE = 1;
	
	/**
	 *  Global modifier for price for champions
	 */
	public final float CHAMPION_PRICE_MODIFIER = 1f;

	/**
	 *  Global modifier for weekly change of champions price;
	 */
	public final float CHAMPION_PRICE_WEEKLY_CHANGE_MODIFIER = 1f;
	
	/**
	 *  Global Modifier for price for weapons
	 */
	public final float WEAPON_PRICE_MODIFIER = 1f;
	
	/**
	 *  Global modifier for weekly change in price of weapons
	 */
	public final float WEAPON_PRICE_WEEKLY_CHANGE_MODIFIER = 1f;
	
	/**
	 *  Path to folder containing weapon images
	 */
	public final String WEAPON_IMAGE_FOLDER_PATH = "src/images/";
	
	/**
	 * Number of teams, including player team
	 */
	public final int NUM_TEAMS = 4;
	
	/**
	 * Number of champions a team can hold as chosen.
	 */
	public final int NUM_CHOSEN_CHAMPIONS = 4;
	
	/**
	 * Number of champions a team can hold as reserve.
	 */
	public final int NUM_RESERVE_CHAMPIONS = 5;
	
	/**
	 * Number of weapons a team can hold as reserve.
	 */
	public final int NUM_RESERVE_WEAPONS = 4;
	
	/**
	 * The starting money for each team.
	 */
	public final float STARTING_MONEY = 100f;
	
	
	public float getDifficulty()
	{
		return difficulty;
	}
	
	public void setDifficulty(float newDifficulty)
	{
		difficulty = newDifficulty;
	}
}
