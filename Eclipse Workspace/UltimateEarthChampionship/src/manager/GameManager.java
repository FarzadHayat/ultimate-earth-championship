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
import champions.DouglasMacArthur;
import champions.DwightEisenhower;
import champions.ElvisPresley;
import champions.FranzFerdinand;
import champions.GeorgeWashington;
import champions.GhengisKhan;
import champions.HarryTruman;
import champions.JoeRogan;
import champions.JohnBrowning;
import champions.JohnFKennedy;
import champions.JohnMaynardKeynes;
import champions.JosefStalin;
import champions.KingGeorge;
import champions.MarcusAurelius;
import champions.MargaretThatcher;
import champions.MarieCurie;
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
import champions.SimonHoermann;
import champions.StephenHawking;
import champions.SunTzu;
import champions.TedKaczynski;
import champions.TimBell;
import champions.WilliamShakespeare;
import display.DisplayStrategy;
import display.DisplayType;
import exception.FullTeamException;
import exception.GameFinishedException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;
import match.DumbMatch;
import match.LiveMatch;
import match.Match;
import match.MatchResult;
import model.Champion;
import model.Configuration;
import model.GameEnvironment;
import model.LevelUpStat;
import model.Shop;
import model.Team;
import model.Weapon;
import story.Cutscene;
import views.LevelUpView;
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
 * The GameManager class is an abstract class that defines the basic
 * functionality of a game manager. The GameManager is implemented as a
 * Singleton using the getInstance() method.
 * The game manager is given the responsibility of mediating the game logic
 * and acting as a controller between views and the model
 */
public abstract class GameManager {
	private static DisplayType displayType = DisplayType.CLI;
	private static GameManager instance;
	protected DisplayStrategy displayStrategy;
	private Configuration config = Configuration.getInstance();

	/**
	 * The GameEnvironment instance for this GameManager.
	 */
	protected GameEnvironment gameEnvironment;

	/**
	 * The Shop instance for this GameManager.
	 */
	protected Shop shop;

	/**
	 * The list of all available champions.
	 */
	protected ArrayList<Champion> allChampions;

	/**
	 * The list of all available weapons.
	 */
	protected ArrayList<Weapon> allWeapons;

	/**
	 * The player's team for ease of access.
	 */
	protected Team playerTeam;

	/**
	 * All the teams including the player team.
	 */
	protected ArrayList<Team> teams = new ArrayList<>();

	/**
	 * The currently selected enemy team.
	 */
	protected Team enemyTeam;

	/**
	 * The currently active match.
	 */
	protected Match match;

	/**
	 * The current cutscene for the game.
	 */
	protected Cutscene cutscene;

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
		// All champions
		allChampions = new ArrayList<>(List.of(new CharlesDarwin(), new ElvisPresley(), new JoeRogan(),
				new JosefStalin(), new KingGeorge(), new MarcusAurelius(), new MargaretThatcher(), new MarieCurie(),
				new SimonHoermann(), new MatthiasGalster(), new MikolosHorthy(), new NapoleonBonaparte(),
				new NeilArmstrong(), new NikitaKrustchev(), new PhilGarland(), new PhilippePetain(),
				new QueenVictoria(), new RobertMuldoon(), new RudyardKipling(), new ShokoAsahara(),
				new StephenHawking(), new SunTzu(), new TedKaczynski(), new TimBell(), new WilliamShakespeare(),
				new AdamSmith(), new AugustoPinochet(), new AugustusCaesar(), new BernardMontgomery(),
				new JohnBrowning(), new JohnFKennedy(), new JohnMaynardKeynes(), new FranzFerdinand(),
				new GeorgeWashington(), new GhengisKhan(), new HarryTruman(), new Confucius(), new DavidLange(),
				new DouglasMacArthur(), new DwightEisenhower()));

		// All weapons
		allWeapons = new ArrayList<>(List.of(new Axe(), new BaseballBat(), new Chainsaw(), new Dagger(),
				new FryingPan(), new GolfClub(), new Katana(), new Mace(), new Machete(), new Nunchucks(),
				new Pickaxe(), new Pitchfork(), new Scythe(), new Shield(), new Shovel(), new Shuriken(),
				new Sledgehammer(), new Spear(), new Sword(), new TennisRacket()));

		// Game environment
		gameEnvironment = new GameEnvironment();

		// Shop
		shop = new Shop();
	}

	/**
	 * Starts the game. Handles individually by the GameManager subclasses.
	 */
	public abstract void play();

	/**
	 * Gets the GameManager instance. If the instance is null, creates a new
	 * instance based on the DisplayType specified in GameInitializer.
	 *
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
	 * Gets the display type
	 * @return The display type as an enum
	 */
	public static DisplayType getDisplayType() {
		return displayType;
	}

	/**
	 * Sets the display type
	 * @param displayType The display type to set
	 */
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
	 *
	 * @return the Shop instance.
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * Sets the Shop instance.
	 *
	 * @param shop the Shop instance to set.
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * Gets the list of all champions.
	 *
	 * @return the list of all champions.
	 */
	public ArrayList<Champion> getAllChampions() {
		return allChampions;
	}

	/**
	 * Sets the list of all champions.
	 *
	 * @param allChampions the list of all champions to set.
	 */
	public void setAllChampions(ArrayList<Champion> allChampions) {
		this.allChampions = allChampions;
	}

	/**
	 * Gets the list of all weapons.
	 *
	 * @return the list of all weapons.
	 */
	public ArrayList<Weapon> getAllWeapons() {
		return allWeapons;
	}

	/**
	 * Sets the list of all weapons.
	 *
	 * @param allWeapons the list of all weapons to set.
	 */
	public void setAllWeapons(ArrayList<Weapon> allWeapons) {
		this.allWeapons = allWeapons;
	}

	/**
	 * gets the player's team.
	 *
	 * @return the playerTeam
	 */
	public Team getPlayerTeam() {
		return playerTeam;
	}

	/**
	 * Sets the player's team to the given team.
	 *
	 * @param playerTeam the playerTeam to set
	 */
	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}

	/**
	 * Get list of all the teams in the game.
	 *
	 * @return teams the list of teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * set list of all the teams in the game.
	 *
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

	/**
	 * Gets the current match
	 * @return The current match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * Sets the current match
	 * @param match sets the current match
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * Returns the current cutscene
	 * @return The current cutscene
	 */
	public Cutscene getCutscene() {
		return cutscene;
	}

	/**
	 * Sets the current cutscene
	 * @param cutscene The cutscene to be set
	 */
	public void setCutscene(Cutscene cutscene) {
		this.cutscene = cutscene;
	}

	/**
	 * Get list of all AI teams in the game.
	 *
	 * @return teams the list of AI teams
	 */
	public ArrayList<Team> getAITeams() {
		ArrayList<Team> AITeams = new ArrayList<>();
		AITeams.remove(playerTeam);
		for (Team team : teams) {
			if (!team.isPlayerTeam()) {
				AITeams.add(team);
			}
		}
		return AITeams;
	}

	/**
	 * Sets up the playerTeam, the difficulty and the number of weeks, should be
	 * called by the setup
	 *
	 * @param teamName   The team name
	 * @param numWeeks   The number of weeks in the season
	 * @param champions  List of champions in the player team
	 * @param difficulty The difficulty of the game
	 */
	public void setupPlayerTeam(String teamName, int numWeeks, ArrayList<Champion> champions, float difficulty) {
		Team playerTeam = new Team(true, teamName, champions);
		setPlayerTeam(playerTeam);
		teams.add(playerTeam);

		config.setDifficulty(difficulty);
		gameEnvironment.setMaxWeeks(numWeeks);
	}

	/**
	 * Generates all the AI teams
	 *
	 * @return A list of 3 AI teams
	 */
	public ArrayList<Team> generateAITeams() {
		ArrayList<Team> teams = new ArrayList<>();

		// List of champions in use by the AI
		// We use this to make sure that duplicate champions are not chosen for the
		// teams
		ArrayList<Champion> setupChampionsInUse = new ArrayList<>();
		// Add the player champions to list to ensure they are not chosen by then AI.
		setupChampionsInUse.addAll(playerTeam.getChampions());

		ArrayList<String> possibleTeamNames = new ArrayList<>(config.AI_TEAM_NAMES);

		Random rand = new Random();

		int teamNum = 0;
		while (teamNum < 3) {
			// get team name
			String name = possibleTeamNames.get(rand.nextInt(possibleTeamNames.size()));
			possibleTeamNames.remove(name);

			// get team champions
			ArrayList<Champion> champions = new ArrayList<>();

			int championNum = 0;
			while (championNum < 4) {
				// Get champion
				@SuppressWarnings("static-access")
				Champion newChamp = Shop.getRandomChampion(getAllChampions());

				if (!setupChampionsInUse.contains(newChamp)) {
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

	/**
	 * Called upon finishing the display of the opening cutscene
	 */
	public void finishedCutscene() {
		displayStrategy.displaySetup();
	}

	/**
	 * Called upon finishing the game setup
	 */
	public void finishedSetup() {
		teams.addAll(generateAITeams());
		shop.generateCatalogue();
		displayStrategy.displayTeam();
	}

	/**
	 * Called to start the match setup
	 * @param team The enemy team being fought
	 */
	public void startMatchSetup(Team team) {
		enemyTeam = team;
		displayStrategy.displayChampionSetup();
	}

	/**
	 * Displays the weapon setup. Intended to be called once the champion setup for a match is complete
	 */
	public void finishedChampionSetup() {
		displayStrategy.displayWeaponSetup();
	}

	/**
	 *  Starts the live match. Intended to be called once the weapon setup for a match is complete
	 */
	public void finishedWeaponSetup() {
		setMatch(new LiveMatch(getPlayerTeam(), getEnemyTeam()));
		displayStrategy.displayLiveMatch((LiveMatch) getMatch());
	}

	/**
	 * Shows the provided match results and finishes the week
	 * @param matchResult The match result to show
	 */
	public void finishedMatch(MatchResult matchResult) {
		displayStrategy.displayMatchResults(matchResult);
		finishedWeek();
	}

	/**
	 * Forces AI teams to fight eachother in dumbmatches
	 * @param teams An arrayList of teams to fight
	 */
	private void fightTeams(ArrayList<Team> teams) {
		if (teams.size() % 2 == 1 && Configuration.DEBUG) {
			System.out.println("WARNING: Odd number of teams. One of the AI team will not be fighting this week!");
		}
		while (teams.size() > 1) {
			Random random = new Random();
			Team team1 = teams.remove(random.nextInt(teams.size()));
			Team team2 = teams.remove(random.nextInt(teams.size()));
			new DumbMatch(team1, team2).getMatchResult();
		}
	}

	/**
	 * Gets the AI to shop for new weapons and champions. Intended to be called at the end of each week
	 * @param teams ArrayList of teams who should go shopping. This should not include the player team
	 */
	private void shopTeams(ArrayList<Team> teams) {
		// Get all shop champions except the one(s) player has already bought
		ArrayList<Champion> championsLeft = new ArrayList<>(shop.getAvailableChampions());
		for (Champion champion : playerTeam.getChampions()) {
			championsLeft.remove(champion);
		}
		// Get all shop weapons except the one(s) player has already bought
		ArrayList<Weapon> weaponsLeft = new ArrayList<>(shop.getAvailableWeapons());
		for (Weapon weapon : playerTeam.getWeapons()) {
			weaponsLeft.remove(weapon);
		}
		Random random = new Random();
		for (Team team : teams) {
			try {
				int championIndex = random.nextInt(championsLeft.size());
				championsLeft.get(championIndex).buy(team);
				championsLeft.remove(championIndex);
			} catch (FullTeamException | InsufficientFundsException | IllegalPurchaseException e) {
				if (Configuration.DEBUG) {
					System.out.println(team.getName() + " BUY CHAMPION: " + e.getMessage());
				}
			}
			try {
				int weaponIndex = random.nextInt(weaponsLeft.size());
				weaponsLeft.get(weaponIndex).buy(team);
				weaponsLeft.remove(weaponIndex);
			} catch (FullTeamException | InsufficientFundsException e) {
				if (Configuration.DEBUG) {
					System.out.println(team.getName() + " BUY WEAPON: " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Gets the AI to shop,
	 * Gets them to fight, Resets relevant data, 
	 * displays the week results and then moves the game to the next week
	 */
	public void finishedWeek() {
		// Shop for all AI teams including the one that the player just fought
		shopTeams(getAITeams());
		// Fight for AI teams that haven't fought yet this week
		ArrayList<Team> teamsLeft = new ArrayList<>(teams);
		teamsLeft.remove(playerTeam);
		teamsLeft.remove(enemyTeam);
		fightTeams(teamsLeft);
		// Reset match and enemy team
		setMatch(null);
		setEnemyTeam(null);
		// Show end of week results and random events
		displayStrategy.displayWeekResults(gameEnvironment.generateWeeklyEvents());
		// Tell all teams to take a bye
		for (Team team : getTeams()) {
			team.rest();
		}
		try {
			// Iterate the current week
			gameEnvironment.nextWeek();
			// Refresh the shop catalogue
			shop.generateCatalogue();
			// Show the next week
			displayStrategy.displayTeam();
		} catch (GameFinishedException e) {
			finishedGame();
		}
	}

	/**
	 * Displays the game results
	 */
	public void finishedGame() {
		displayStrategy.displayGameResults();
	}

	/**
	 * Levels up a champion depending on whether the champion is on the players team
	 * or not
	 *
	 * @param champion The champion to level up
	 */
	public void championLevelUp(Champion champion) {
		if (playerTeam.getChampions().contains(champion)) {
			// The champion is on the player team

			displayLevelUpDialogue(champion);
		} else {
			LevelUpStat stat = getRandomLevelUpStat();
			applyLevelUp(champion, stat);
		}
	}

	/**
	 * Lets the view know that a champion has leveled up and the player needs to be
	 * promtped for a stat to upgrade. Called by champion.
	 *
	 * @param champion The champion that has leveled up
	 */
	public void displayLevelUpDialogue(Champion champion) {
		LevelUpView.showLevelUpDialogue(champion);
	}

	/**
	 * Lets the champion know that the player has chosen their stat to level up
	 * Called by LevelUpView
	 *
	 * @param champion The champion to level up
	 * @param stat     The stat to increase
	 */
	public void applyLevelUp(Champion champion, LevelUpStat stat) {
		champion.applyLevelUp(stat);
	}

	/**
	 * Returns a random LevelUpStat
	 *
	 * @return A random levelUpStat
	 */
	private LevelUpStat getRandomLevelUpStat() {
		Random random = new Random();
		int randInt = random.nextInt(4);

		LevelUpStat out = LevelUpStat.STAMINA;

		switch (randInt) {
		case 0:
			out = LevelUpStat.STAMINA;
			break;
		case 1:
			out = LevelUpStat.REGEN;
			break;
		case 2:
			out = LevelUpStat.OFFENSE;
			break;
		case 3:
			out = LevelUpStat.DEFENSE;
			break;
		}

		return out;
	}

}
