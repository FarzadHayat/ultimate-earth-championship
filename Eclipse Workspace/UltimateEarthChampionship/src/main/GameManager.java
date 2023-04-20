package main;

import java.util.ArrayList;
import java.util.List;

import champion.Champion;
import champion.champions.AdamSmith;
import champion.champions.BernardMontgomery;
import champion.champions.CharlesDarwin;
import champion.champions.Confucius;
import champion.champions.GeorgeWashington;
import champion.champions.JohnDoe;
import champion.champions.JohnFKennedy;
import champion.champions.JohnMKeynes;
import champion.champions.JosefStalin;
import champion.champions.KingGeorge;
import weapon.Weapon;
import weapon.weapons.Chainsaw;
import weapon.weapons.GolfClub;
import weapon.weapons.Pickaxe;
import weapon.weapons.Shield;
import weapon.weapons.Sledgehammer;

/**
 * The GameManager class is an abstract class that defines the basic functionality of a game manager.
 * The GameManager is implemented as a Singleton using the getInstance() method.
 */
public abstract class GameManager
{
	private static DisplayType displayType = DisplayType.CLI;
	private static GameManager instance;
	
	/**
	 * The GameEnvironment instance for this GameManager.
	 */
	private GameEnvironment gameEnvironment;
	
	/**
	 * The Shop instance for this GameManager.
	 */
	private Shop shop;
	
	/**
	 * The list of all available champions.
	 */
	private ArrayList<Champion> allChampions; 
	
	/**
	 * The list of all available weapons.
	 */
	private ArrayList<Weapon> allWeapons;
	
	/**
	 * The player's team for ease of access.
	 */
	private Team playerTeam;
	
	/**
	 * All the teams including the player team.
	 */
	private ArrayList<Team> teams; 
	
	/**
	 * Starts the game.
	 */
	public static void start() {
		GameManager.getInstance();
		instance.initialize();
		instance.play();
	}
	
	/**
	 * Initialize the necessary starting objects for the game.
	 */
	public void initialize() {
		shop = new Shop();
		gameEnvironment = new GameEnvironment(1);
		teams = new ArrayList<Team>();
		
		allChampions = new ArrayList<Champion>(
    			List.of(new AdamSmith(), new BernardMontgomery(), new CharlesDarwin(), new Confucius(), new GeorgeWashington(),
    					new JohnDoe(), new JohnFKennedy(), new JohnMKeynes(), new JosefStalin(), new KingGeorge())
    			);
    	allWeapons = new ArrayList<Weapon>(
    			List.of(new Chainsaw(), new GolfClub(), new Pickaxe(), new Sledgehammer(), new Shield())
    			);
    	playerTeam = new Team(true, new ArrayList<Champion>(allChampions.subList(0, 4)));
    	teams.add(playerTeam);
    	
    	getShop().generateCatalogue();
	}
	
	/**
	 * Starts the game. Handles individually by the GameManager subclasses.
	 */
	public abstract void play();

	public static DisplayType getDisplayType() {
		return displayType;
	}

	public static void setDisplayType(DisplayType displayType) {
		GameManager.displayType = displayType;
	}

	/**
	 * Gets the GameManager instance.
	 * If the instance is null, creates a new instance based on the DisplayType specified in GameInitializer.
	 * @return the GameManager instance.
	 */
	public static GameManager getInstance() {
		if (instance == null) {
			switch (getDisplayType()) {
			case GUI: {
				instance = new GraphicalGameManager();
				break;
			}
			case CLI: {
				instance = new CommandLineGameManager();
				break;
			}
			}
		}
		return instance;
	}
	
	/**
	 * @return the gameEnvironment
	 */
	public GameEnvironment getGameEnvironment() {
		return gameEnvironment;
	}

	/**
	 * @param gameEnvironment the gameEnvironment to set
	 */
	public void setGameEnvironment(GameEnvironment gameEnvironment) {
		this.gameEnvironment = gameEnvironment;
	}

	/**
	 * Gets the Shop instance.
	 * @return the Shop instance.
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * Sets the Shop instance.
	 * @param shop the Shop instance to set.
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * Gets the list of all champions.
	 * @return the list of all champions.
	 */
	public ArrayList<Champion> getAllChampions() {
		return allChampions;
	}

	/**
	 * Sets the list of all champions.
	 * @param allChampions the list of all champions to set.
	 */
	public void setAllChampions(ArrayList<Champion> allChampions) {
		this.allChampions = allChampions;
	}

	/**
	 * Gets the list of all weapons.
	 * @return the list of all weapons.
	 */
	public ArrayList<Weapon> getAllWeapons() {
		return allWeapons;
	}

	/**
	 * Sets the list of all weapons.
	 * @param allWeapons the list of all weapons to set.
	 */
	public void setAllWeapons(ArrayList<Weapon> allWeapons) {
		this.allWeapons = allWeapons;
	}

	/**
	 * gets the player's team.
	 * @return the playerTeam
	 */
	public Team getPlayerTeam() {
		return playerTeam;
	}

	/**
	 * Sets the player's team to the given team.
	 * @param playerTeam the playerTeam to set
	 */
	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}

	/**
	 * Get list of all the teams in the game.
	 * @return teams the list of teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * set  list of all the teams in the game.
	 * @param teams the list of teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

}
