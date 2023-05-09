package model;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

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
	 * Whether to print debugging messages to console.
	 */
	public static final boolean DEBUG = false; 
	
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
	 *  The default max stamina of each champion, before skill points are accounted for
	 */
	public final float MAX_STAMINA_DEFAULT = 100f;
	
	/**
	 *  The default max regen of each champion, before skill points are accounted for 
	 */
	public final float MAX_REGEN_DEFAULT = 30f;
	
	/**
	 *  The amount that a skill point into stamina will increment maximum stamina of a champion
	 */
	public final float SKILL_STAMINA_INCREMENT = 10f;
	
	/**
	 *  The amount that a skill point into regen will increment regen of a champion
	 */
	public final float SKILL_REGEN_INCREMENT = 2f;
	
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
	 *  Path to folder containing champion images
	 */
	public static final String CHAMPION_IMAGE_FOLDER_PATH = "src/images/champions/";
	
	/**
	 *  Path to folder containing weapon images
	 */
	public static final String WEAPON_IMAGE_FOLDER_PATH = "src/images/weapons/";
	
	/**
	 *  Path to folder containing icon images
	 */
	public static final String ICON_IMAGE_FOLDER_PATH = "src/images/icons/";
	
	/**
	 *  Path to folder containing background images
	 */
	public static final String BACKGROUND_IMAGE_FOLDER_PATH = "src/images/backgrounds/";
	
	/**
	 * Number of teams, including player team
	 */
	public final int NUM_TEAMS = 4;
	
	/**
	 * The base amount of money given as a prize to the winning team
	 */
	public final float PRIZE_MONEY_BASE = 25f;
	
	/**
	 * The amount that both teams score increases the prize money allotted
	 */
	public final float PRIZE_MONEY_SCORE_SCALE_FACTOR = 0.25f;
	
	/**
	 * The multiplier of the prize money for the losing team
	 */
	public final float PRIZE_MONEY_FOR_LOSER = 0.80f;
	
	/**
	 * The base amount of score given to the winning team
	 */
	public final int PRIZE_SCORE_BASE = 8;
	
	/**
	 * The amount that the prize score of matches increases each week
	 */
	public final int PRIZE_SCORE_WEEKLY_MODIFIER = 2;

	/**
	 * Number of champions a team can hold as chosen.
	 */
	public final int NUM_CHAMPIONS = 9;
	
	/**
	 * Number of champions a team can hold as chosen.
	 */
	public final int NUM_CHOSEN_CHAMPIONS = 4;
	
	/**
	 * Number of weapons a team can hold.
	 */
	public final int NUM_WEAPONS = 9;
	
	/**
	 * The starting money for each team.
	 */
	public final float STARTING_MONEY = 100f;
	
	/**
	 * The minimum number of characters a teams name can be (inclusive)
	 */
	public final int MIN_TEAM_NAME_CHARS = 3;
	
	/**
	 * The maximum number of characters a teams name can be (inclusive)
	 */
	public final int MAX_TEAM_NAME_CHARS = 15;
	
	/**
	 * The minimum number of weeks the player can choose for the season to be
	 */
	public final int MIN_NUM_GAME_WEEKS = 5;
	
	/**
	 * The maximum number of weeks the player can choose for the season to be
	 */
	public final int MAX_NUM_GAME_WEEKS = 15;
	
	/**
	 * List of possible names for enemy teams
	 */
	public final ArrayList<String> AI_TEAM_NAMES = 
			new ArrayList<String>(List.of("Team Butter", "Team Green", "Team Duty", "Team Truffle",
										"Team Garfield", "Team Buxley", "Team Brisbane", "Team Eagle"));

	/**
	 * The font family for the graphical game.
	 */
	public static final String FONT_FAMILY = "Ubuntu";
	
	/**
	 * The font used for headers.
	 */
	public static final Font HEADER_FONT = new Font(FONT_FAMILY, Font.PLAIN, 24);
	
	/**
	 * The font used for regular text.
	 */
	public static final Font TEXT_FONT = new Font(FONT_FAMILY, Font.BOLD, 14);
	
	/**
	 * The lower bound of AI aggression, 
	 */
	public final int AGRESSION_RANDOM_LOWER_BOUND = -50;
	
	/**
	 * The upper bound of AI aggression
	 */
	public final int AGRESSION_RANDOM_UPPER_BOUND = 50;
	
	/**
	 *  The base amount damage each attack will deal, this is modified by champion stats
	 */
	public final int DAMAGE_BASE = 20;
	
	/**
	 * The amount of extra damage added to an attack per offense point
	 */
	public final int OFFENSE_STAT_DAMAGE_MULTIPLER = 2;
	
	/**
	 * The number of milliseconds that each AI champion waits before moving.
	 * Note that 1 second = 1000 Ms
	 */
	public final int AI_WAIT_TIME_MS = 450;
	
	/**
	 * The amount that stamina increases when a champion levels up
	 */
	public final int LEVEL_UP_STAMINA_INCREASE = 10;
	
	/**
	 * The amount that regen increases when a champion levels up
	 */
	public final int LEVEL_UP_REGEN_INCREASE = 2;
	
	/**
	 * The amount that offense increases when a champion levels up
	 */
	public final int LEVEL_UP_OFFENSE_INCREASE = 1;
	
	/**
	 * The amount that defense increases when a champion levels up
	 */
	public final int LEVEL_UP_DEFENSE_INCREASE = 1;

	/**
	 * Multiplier of AI damage, affected by difficulty
	 */
	public float AI_DAMAGE_MULTIPLIER;
	
	/**
	 * Flat additive modifier to AI aggression factor
	 */
	public int AI_AGRESSION_BOOST;
	
	public float getDifficulty()
	{
		return difficulty;
	}
	
	public void setDifficulty(float newDifficulty)
	{
		System.out.println("Setting difficulty to " + newDifficulty);
		difficulty = newDifficulty;
		
		AI_DAMAGE_MULTIPLIER = difficulty;
		
		AI_AGRESSION_BOOST = (int) (50f * (difficulty - 1f));
	}
}
