package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import champions.AdamSmith;
import champions.AugustoPinochet;
import champions.AugustusCaesar;
import champions.BernardMontgomery;
import champions.CharlesDarwin;
import champions.Confucius;
import champions.DavidLange;
import champions.DouglasMacarthur;
import champions.DwightEisenhower;
import champions.ElvisPresley;
import champions.FranzFerdinand;
import champions.GeorgeWashington;
import champions.GhengisKhan;
import champions.HarryTruman;
import champions.JoeRogan;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import champions.JohnMKeynes;
import champions.JosefStalin;
import champions.KingGeorge;
import champions.MarcusAurelius;
import champions.MargaretThatcher;
import champions.MarieCurie;
import champions.MarkRickerby;
import champions.MatthiasGalster;
import champions.MikolosHorthy;
import champions.NapoleonBonaparte;
import champions.NeilArmstrong;
import champions.NikitaKrustchev;
import champions.PhilGarland;
import champions.PhilippePetain;
import champions.QueenVictoria;
import champions.RobertMuldoon;
import champions.RudyardKipling;
import champions.ShokoAsahara;
import champions.StephenHawking;
import champions.SunTzu;
import champions.TedKaczynski;
import champions.TimBell;
import champions.WilliamShakespeare;
import display.DisplayStrategy;
import display.DisplayType;
import match.Match;
import model.Champion;
import model.Configuration;
import model.GameEnvironment;
import model.Shop;
import model.Team;
import model.Weapon;
import story.Cutscene;
import story.OpeningCutscene;
import weapons.Axe;
import weapons.BaseballBat;
import weapons.Chainsaw;
import weapons.Dagger;
import weapons.FryingPan;
import weapons.GolfClub;
import weapons.Katana;
import weapons.Mace;
import weapons.Machete;
import weapons.Nunchucks;
import weapons.Pickaxe;
import weapons.Pitchfork;
import weapons.Scythe;
import weapons.Shield;
import weapons.Shovel;
import weapons.Shuriken;
import weapons.Sledgehammer;
import weapons.Spear;
import weapons.Sword;
import weapons.TennisRacket;

/**
 * The GameManager class is an abstract class that defines the basic functionality of a game manager.
 * The GameManager is implemented as a Singleton using the getInstance() method.
 */
public abstract class GameManager
{
	private static DisplayType displayType = DisplayType.CLI;
	private static GameManager instance;
	protected DisplayStrategy displayStrategy;
	private Configuration config = Configuration.getInstance();
	
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
	 * The currently selected enemy team.
	 */
	private Team enemyTeam;
	
	/**
	 * The currently active match.
	 */
	private Match match;
	
	/**
	 * The current cutscene for the game.
	 */
	private Cutscene cutscene;
	
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
		// Game environment
		gameEnvironment = new GameEnvironment();
		
		// All champions
		allChampions = new ArrayList<Champion>(List.of(
				new CharlesDarwin(), new ElvisPresley(), new JoeRogan(), new JosefStalin(),
    			new KingGeorge(), new MarcusAurelius(), new MargaretThatcher(), new MarieCurie(), new SimonHoermann(),
    			new MatthiasGalster(), new MikolosHorthy(), new NapoleonBonaparte(), new NeilArmstrong(), new NikitaKrustchev(),
    			new PhilGarland(), new PhilippePetain(), new QueenVictoria(), new RobertMuldoon(), new RudyardKipling(),
    			new ShokoAsahara(), new StephenHawking(), new SunTzu(), new TedKaczynski(), new TimBell(), new WilliamShakespeare(),
    			new AdamSmith(), new AugustoPinochet(), new AugustusCaesar(), new BernardMontgomery(),
    			new JohnBrowning(), new JohnDoe(), new JohnFKennedy(), new JohnMaynardKeynes(),
    			new FranzFerdinand(), new GeorgeWashington(), new GhengisKhan(), new HarryTruman(),
    			new Confucius(), new DavidLange(), new DouglasMacArthur(), new DwightEisenhower()
    			));
		
		// All weapons
		allWeapons = new ArrayList<Weapon>(List.of(
    			new Axe(), new BaseballBat(), new Chainsaw(), new Dagger(), new FryingPan(),
    			new GolfClub(), new Katana(), new Mace(), new Machete(), new Nunchucks(),
    			new Pickaxe(), new Pitchfork(), new Scythe(), new Shield(), new Shovel(),
    			new Shuriken(), new Sledgehammer(), new Spear(), new Sword(), new TennisRacket()
    			));
    	
		// Teams
		playerTeam = new Team(true, "Player", new ArrayList<Champion>()); // Champions are assigned later, after setup
		teams = generateAITeams();
		teams.add(playerTeam);
		
    	// Shop
    	shop = new Shop();
    	getShop().generateCatalogue();
	}
	
	/**
	 * Starts the game. Handles individually by the GameManager subclasses.
	 */
	public abstract void play();

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

	public static DisplayType getDisplayType() {
		return displayType;
	}

	public static void setDisplayType(DisplayType displayType) {
		GameManager.displayType = displayType;
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
	
	/**
	 * @return the enemyTeam
	 */
	public Team getEnemyTeam() {
		return enemyTeam;
	}

	/**
	 * @param enemyTeam the enemyTeam to set
	 */
	public void setEnemyTeam(Team enemyTeam) {
		this.enemyTeam = enemyTeam;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Cutscene getCutscene() {
		return cutscene;
	}

	public void setCutscene(Cutscene cutscene) {
		this.cutscene = cutscene;
	}

	/**
	 * Get list of all AI teams in the game.
	 * @return teams the list of AI teams
	 */
	public ArrayList<Team> getAITeams() {
		ArrayList<Team> AITeams = new ArrayList<Team>();
		AITeams.remove(playerTeam);
		for (Team team : teams) {
			if (!team.isPlayerTeam()) {
				AITeams.add(team); 	
			}
		}
		return AITeams;
	}
	
	/**
	 * Sets up the playerTeam, the difficulty and the number of weeks, should be called by the setup
	 * @param teamName The team name
	 * @param numWeeks The number of weeks in the season
	 * @param champions List of champions in the player team
	 * @param difficulty The difficulty of the game
	 */
	public void setupPlayerTeam(String teamName, int numWeeks, ArrayList<Champion> champions, float difficulty)
	{
		Team playerTeam = new Team(true, teamName, champions);
		setPlayerTeam(playerTeam);
		
		gameEnvironment.setDifficulty(difficulty);
		gameEnvironment.setMaxWeeks(numWeeks);
	}

	/**
	 * Generates all the AI teams
	 * @return A list of 3 AI teams
	 */
	public ArrayList<Team> generateAITeams()
	{
		ArrayList<Team> teams = new ArrayList<Team>();
		

		// List of champions in use by the AI
		// We use this to make sure that duplicate champions are not chosen for the teams
		ArrayList<Champion> setupChampionsInUse = new ArrayList<Champion>();
		
		ArrayList<String> possibleTeamNames = new ArrayList<String>(config.AI_TEAM_NAMES);
		
		Random rand = new Random();
		
		int teamNum = 0;
		while (teamNum < 3)
		{
			// get team name
			String name = possibleTeamNames.get(rand.nextInt(possibleTeamNames.size()));
			possibleTeamNames.remove(name);
			
			// get team champions
			ArrayList<Champion> champions = new ArrayList<Champion>();
			
			int championNum = 0;
			while (championNum < 4)
			{
				// Get champion
				@SuppressWarnings("static-access")
				Champion newChamp = shop.getRandomChampion(getAllChampions());
				
				if (!setupChampionsInUse.contains(newChamp))
				{
					// Remember that this champion is in-use
					setupChampionsInUse.add(newChamp);
					
					champions.add(newChamp);
					championNum++;
				}
			}
			
			teams.add(new Team(false, name, champions));
			teamNum++;
			
		}
		
		return teams;
	}
	

	public void finishedCutscene()
	{
		displayStrategy.displaySetup();
	}
	
	public void finishedSetup()
	{
		displayStrategy.displayTeam();
	}
	
	public void startMatchSetup(Team team) {
		enemyTeam = team;
		displayStrategy.displayChampionSetup();
	}
	
	public void finishedChampionSetup() {
		displayStrategy.displayWeaponSetup();
	}
	
	public void finishedWeaponSetup() {
		for (int i = 0; i < getPlayerTeam().getChosenWeapons().size(); i++) {
			Champion champion = getPlayerTeam().getChosenChampions().get(i); 
			Weapon weapon = getPlayerTeam().getChosenWeapons().get(i);
			champion.setWeapon(weapon);
		}
		// TODO: create live match. waiting for LiveMatch class to be created.
		displayStrategy.displayLiveMatch();
	}
	
	public void finishedMatch() {
		//TODO: Process match results and go to next week
		finishedWeek();
	}
	
	public void finishedWeek() {
		//TODO: Process week results and upcoming random events
		displayStrategy.displayTeam();
	}
	
}
