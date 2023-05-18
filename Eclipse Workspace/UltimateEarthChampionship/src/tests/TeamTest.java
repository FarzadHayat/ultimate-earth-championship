/**
 *
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.Confucius;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import champions.JohnMaynardKeynes;
import exception.FullTeamException;
import exception.InsufficientFundsException;
import manager.GameManager;
import model.Champion;
import model.Team;

/**
 * Unit test for the Team class.
 */
class TeamTest {

	private Team team;
	private static GameManager gameManager;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gameManager = GameManager.getInstance();
		gameManager.initialize();
		gameManager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
				List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);
		gameManager.getTeams().addAll(gameManager.generateAITeams());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		team = new Team(false, "Test", new ArrayList<Champion>(
				List.of(new JohnBrowning(), new JohnDoe(), new JohnFKennedy(), new JohnMaynardKeynes())));

	}

	/**
	 * Test method for
	 * {@link model.Team#Team(boolean, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testTeam() {
		assertFalse(team.isPlayer());
		assertEquals("Test", team.getName());
		assertEquals(4, team.getChampions().size());
		assertEquals(100f, team.getMoney());
		assertEquals(0, team.getScore());
	}

	/**
	 * Test method for {@link model.Team#addMoney(float)}.
	 */
	@Test
	void testAddMoney() {

		assertEquals(100f, team.getMoney());

		team.addMoney(50f);

		assertEquals(150f, team.getMoney());
	}

	/**
	 * Test method for {@link model.Team#removeMoney(float)}.
	 */
	@Test
	void testRemoveMoney_blueSky() {
		assertEquals(100f, team.getMoney());

		try {
			team.removeMoney(50f);
		} catch (InsufficientFundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(50f, team.getMoney());
	}

	/**
	 * Test method for {@link model.Team#removeMoney(float)}.
	 */
	@Test
	void testRemoveMoney_insufficientFunds() {
		assertEquals(100f, team.getMoney());
		InsufficientFundsException thrown = assertThrows(InsufficientFundsException.class,
				() -> team.removeMoney(100.1f), "Expected team.removeMoney(100.1f) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("You do not have enough money to perform this action!"));
		assertEquals(100.0f, team.getMoney());
	}

	/**
	 * Test method for {@link model.Team#hasMoney(float)}.
	 */
	@Test
	void testHasMoney() {
		assertTrue(team.hasMoney(100f));
		assertFalse(team.hasMoney(100.1f));
	}

	/**
	 * Test method for {@link model.Team#addScore()}.
	 */
	@Test
	void testAddScore() {
		assertEquals(0, team.getScore());

		team.addScore(7);

		assertEquals(7, team.getScore());
	}

	/**
	 * Test method for {@link model.Team#addChampion(model.Champion)}.
	 */
	@Test
	void testAddChampion() {
		Champion champion = new Confucius();
		try {
			team.addChampion(champion);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		assertTrue(team.getChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#removeChampion(model.Champion)}.
	 */
	@Test
	void testRemoveChampion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#randomChampion()}.
	 */
	@Test
	void testRandomChampion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#addChosenChampion(model.Champion)}.
	 */
	@Test
	void testAddChosenChampion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#removeChosenChampion(model.Champion)}.
	 */
	@Test
	void testRemoveChosenChampion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#addChosenWeapon(model.Weapon)}.
	 */
	@Test
	void testAddChosenWeapon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#removeChosenWeapon(model.Weapon)}.
	 */
	@Test
	void testRemoveChosenWeapon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#addWeapon(model.Weapon)}.
	 */
	@Test
	void testAddWeapon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#removeWeapon(model.Weapon)}.
	 */
	@Test
	void testRemoveWeapon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#randomlySelectPurchasables()}.
	 */
	@Test
	void testRandomlySelectPurchasables() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#assignChosenWeapons()}.
	 */
	@Test
	void testAssignChosenWeapons() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#unselectPurchasables()}.
	 */
	@Test
	void testUnselectPurchasables() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#unselectChampions()}.
	 */
	@Test
	void testUnselectChampions() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#unselectWeapons()}.
	 */
	@Test
	void testUnselectWeapons() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#unassignChosenWeapons()}.
	 */
	@Test
	void testUnassignChosenWeapons() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Team#rest()}.
	 */
	@Test
	void testRest() {
		fail("Not yet implemented");
	}

}
