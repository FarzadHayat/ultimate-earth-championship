/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import exception.GameFinishedException;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.GameEnvironment;
import model.Team;

/**
 * Unit test for the GameEnvironment class.
 */
class GameEnvironmentTest {

	private static GameEnvironment gameEnvironment;
	private static GameManager gameManager;
	private static Configuration config;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gameManager = GameManager.getInstance();
		gameManager.initialize();
		config = Configuration.getInstance();
		gameManager.setPlayerTeam(new Team(true, "Test", new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy()))));
		gameManager.generateAITeams();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		GameEnvironment newGameEnvironment = new GameEnvironment();
		gameManager.setGameEnvironment(newGameEnvironment);
		gameEnvironment = newGameEnvironment;

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.GameEnvironment#getWeeksRemaining()}.
	 */
	@Test
	void testGetWeeksRemaining() {
		gameEnvironment.setCurrentWeek(2);
		gameEnvironment.setMaxWeeks(5);
		assertEquals(3, gameEnvironment.getWeeksRemaining());
	}

	/**
	 * Test method for {@link model.GameEnvironment#getRandomTeam()}.
	 */
	@Test
	void testGetRandomTeam() {
		Team team = gameEnvironment.getRandomTeam();
		assertTrue(gameManager.getTeams().contains(team));
	}

	/**
	 * Test method for {@link model.GameEnvironment#nextWeek()}.
	 */
	@Test
	void testNextWeek_blueSky() {
		gameEnvironment.setCurrentWeek(2);
		gameEnvironment.setMaxWeeks(5);
		try {
			gameEnvironment.nextWeek();
		} catch (GameFinishedException e) {
			e.printStackTrace();
		}
		assertEquals(3, gameEnvironment.getCurrentWeek());
	}

	/**
	 * Test method for {@link model.GameEnvironment#nextWeek()}.
	 */
	@Test
	void testNextWeek_lastWeek() {
		gameEnvironment.setCurrentWeek(5);
		gameEnvironment.setMaxWeeks(5);
		assertThrows(GameFinishedException.class, () -> gameEnvironment.nextWeek(),
				"Expected gameEnvironment.nextWeek() to throw, but it didn't.");
		assertEquals(5, gameEnvironment.getCurrentWeek());
	}

	/**
	 * Test method for {@link model.GameEnvironment#trainChampionsNotInUse()}.
	 */
	@Test
	void testTrainChampionsNotInUse() {
		ArrayList<Champion> teamChampions = new ArrayList<>();
		for (Team team : gameManager.getTeams()) {
			teamChampions.addAll(team.getChampions());
		}
		gameEnvironment.trainChampionsNotInUse();
		for (Champion champion : gameManager.getAllChampions()) {
			if (teamChampions.contains(champion)) {
				assertEquals(1, champion.getLevel());
			} else {
				assertEquals(2, champion.getLevel());
			}
		}
	}

	/**
	 * Test method for {@link model.GameEnvironment#applyWeeklyPriceChange()}.
	 */
	@Test
	void testApplyWeeklyPriceChange() {
		ArrayList<Champion> teamChampions = new ArrayList<>();
		for (Team team : gameManager.getTeams()) {
			teamChampions.addAll(team.getChampions());
		}
		ArrayList<Champion> clonedChampions = new ArrayList<>();
		for (Champion champion : gameManager.getAllChampions()) {
			clonedChampions.add(champion.clone());
		}
		gameEnvironment.applyWeeklyPriceChange();
		for (int i = 0; i < clonedChampions.size(); i++) {
			Champion clone = clonedChampions.get(i);
			Champion real = gameManager.getAllChampions().get(i);
			if (teamChampions.contains(real)) {
				assertEquals(clone.getPrice() * clone.getPriceChangeWeekly(), real.getPrice());
			} else {
				assertEquals(clone.getPrice(), real.getPrice());
			}

		}
	}

}
