/**
 *
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.DavidLange;
import champions.DouglasMacArthur;
import champions.DwightEisenhower;
import champions.FranzFerdinand;
import champions.GeorgeWashington;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import champions.JosefStalin;
import champions.RudyardKipling;
import champions.ShokoAsahara;
import display.DisplayType;
import manager.GameManager;
import match.MatchResult;
import model.Champion;
import model.Configuration;
import model.Team;

/**
 * Unit test for the GameManager classes.
 */
class GameManagerTest {

	private static GameManager gameManager;
	private static Configuration config;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GameManager.setDisplayType(DisplayType.GUI);
		gameManager = GameManager.getInstance();
		config = Configuration.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		gameManager.forceCreateNewInstance();
		gameManager = GameManager.getInstance();
	}

	/**
	 * Test method for {@link manager.GameManager#initialize()}.
	 */
	@Test
	void testInitialize() {
		gameManager.initialize();
		assertEquals(40, gameManager.getAllChampions().size());
		assertEquals(20, gameManager.getAllWeapons().size());
		assertNotEquals(null, gameManager.getGameEnvironment());
		assertNotEquals(null, gameManager.getShop());
	}

	/**
	 * Test method for {@link manager.GameManager#getAITeams()}.
	 */
	@Test
	void testGetAITeams() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		gameManager.getTeams().addAll(gameManager.generateAITeams());
		ArrayList<Team> teams = gameManager.getAITeams();
		assertEquals(3, teams.size());
	}

	/**
	 * Test method for
	 * {@link manager.GameManager#setupPlayerTeam(java.lang.String, int, java.util.ArrayList, float)}.
	 */
	@Test
	void testSetupPlayerTeam() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Player", 5,
				new ArrayList<Champion>(
						List.of(new DouglasMacArthur(), new JosefStalin(), new ShokoAsahara(), new RudyardKipling())),
				1.2F);
		assertEquals("Player", gameManager.getPlayerTeam().getName());
		assertEquals(1.2F, config.getDifficulty());
		assertEquals(5, gameManager.getGameEnvironment().getMaxWeeks());
	}

	/**
	 * Test method for {@link manager.GameManager#generateAITeams()}.
	 */
	@Test
	void testGenerateAITeams() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		ArrayList<Team> teams = gameManager.generateAITeams();
		assertEquals(3, teams.size());
	}

	/**
	 * Test method for {@link manager.GameManager#fightTeams()}.
	 */
	@Test
	void testFightTeams_oddCount() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		ArrayList<Team> teams = gameManager.generateAITeams();
		ArrayList<MatchResult> allResults = gameManager.fightTeams(teams);
		assertEquals(1, allResults.size());
	}

	/**
	 * Test method for {@link manager.GameManager#fightTeams()}.
	 */
	@Test
	void testFightTeams_evenCount() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		ArrayList<Team> teams = gameManager.generateAITeams();
		teams.add(new Team(false, "Extra", new ArrayList<Champion>(
				List.of(new GeorgeWashington(), new FranzFerdinand(), new DwightEisenhower(), new DavidLange()))));
		ArrayList<MatchResult> allResults = gameManager.fightTeams(teams);
		assertEquals(2, allResults.size());
	}

	/**
	 * Test method for {@link manager.GameManager#shopTeams()}.
	 */
	@Test
	void testShopTeams() {
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		ArrayList<Team> teams = gameManager.generateAITeams();
		for (Team team : teams) {
			team.addMoney(9999f);
		}
		gameManager.getShop().generateCatalogue();
		gameManager.shopTeams(teams);
		for (Team team : teams) {
			assertEquals(5, team.getChampions().size());
			assertEquals(1, team.getWeapons().size());
		}
	}

	/**
	 * Test method for {@link manager.GameManager#forceCreateNewInstance()}.
	 */
	@Test
	void testForceCreateNewInstance() {
		gameManager.forceCreateNewInstance();
		assertNotEquals(gameManager, GameManager.getInstance());
		gameManager = GameManager.getInstance();
	}

	/**
	 * Test method for {@link manager.GameManager#addTeam(model.Team)}.
	 */
	@Test
	void testAddTeam() {
		gameManager.addTeam(new Team(false, "Extra", new ArrayList<Champion>(
				List.of(new GeorgeWashington(), new FranzFerdinand(), new DwightEisenhower(), new DavidLange()))));
		assertEquals(5, gameManager.getTeams().size());
	}

}
