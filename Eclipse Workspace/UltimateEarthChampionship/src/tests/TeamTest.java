/**
 *
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.AugustoPinochet;
import champions.AugustusCaesar;
import champions.BernardMontgomery;
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
import model.Weapon;
import weapons.Fists;
import weapons.Shield;

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
		gameManager.setupPlayerTeam("Test", 5,
				new ArrayList<Champion>(
						List.of(new AdamSmith(), new AugustoPinochet(), new AugustusCaesar(), new BernardMontgomery())),
				1.0F);
		gameManager.getTeams().addAll(gameManager.generateAITeams());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		team = new Team(false, "Test", new ArrayList<Champion>(
				List.of(new JohnBrowning(), new JohnDoe(), new JohnFKennedy(), new JohnMaynardKeynes())));
		team.addWeapon(new Shield());
		team.addWeapon(new Shield());
		team.addWeapon(new Shield());
		team.addWeapon(new Shield());
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
	 * Test method for {@link model.Team#addChampion(model.Champion)}.
	 */
	@Test
	void testAddChampion_fullTeam() {
		try {
			for (int i = 0; i < 5; i++) {
				team.addChampion(new Confucius());
			}
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		Champion champion = new Confucius();

		FullTeamException thrown = assertThrows(FullTeamException.class, () -> team.addChampion(champion),
				"Expected team.addChampion(champion) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("Reached team max champions limit!"));
		assertFalse(team.getChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#removeChampion(model.Champion)}.
	 */
	@Test
	void testRemoveChampion() {
		Champion champion = new Confucius();
		try {
			team.addChampion(champion);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		team.removeChampion(champion);
		assertFalse(team.getChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#randomChampion()}.
	 */
	@Test
	void testRandomChampion() {
		Champion champion = team.randomChampion();
		assertTrue(team.getChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#addChosenChampion(model.Champion)}.
	 */
	@Test
	void testAddChosenChampion() {
		Champion champion = new Confucius();
		try {
			team.addChosenChampion(champion);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		assertTrue(team.getChosenChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#addChosenChampion(model.Champion)}.
	 */
	@Test
	void testAddChosenChampion_fullTeam() {
		team.randomlySelectChampions();
		Champion champion = new Confucius();
		FullTeamException thrown = assertThrows(FullTeamException.class, () -> team.addChosenChampion(champion),
				"Expected team.addChosenChampion(champion) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("Reached team max chosen champions limit!"));
		assertFalse(team.getChosenChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#removeChosenChampion(model.Champion)}.
	 */
	@Test
	void testRemoveChosenChampion() {
		Champion champion = new Confucius();
		try {
			team.addChosenChampion(champion);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		team.removeChosenChampion(champion);
		assertFalse(team.getChosenChampions().contains(champion));
	}

	/**
	 * Test method for {@link model.Team#addChosenWeapon(model.Weapon)}.
	 */
	@Test
	void testAddChosenWeapon() {
		Weapon weapon = new Shield();
		try {
			team.addChosenWeapon(weapon);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		assertTrue(team.getChosenWeapons().contains(weapon));
	}

	/**
	 * Test method for {@link model.Team#addChosenWeapon(model.Weapon)}.
	 */
	@Test
	void testAddChosenWeapon_fullTeam() {
		team.randomlySelectWeapons();
		Weapon weapon = new Shield();
		FullTeamException thrown = assertThrows(FullTeamException.class, () -> team.addChosenWeapon(weapon),
				"Expected team.addChosenWeapon(weapon) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("Reached team max chosen weapons limit!"));
		assertFalse(team.getChosenWeapons().contains(weapon));
	}

	/**
	 * Test method for {@link model.Team#removeChosenWeapon(model.Weapon)}.
	 */
	@Test
	void testRemoveChosenWeapon() {
		Weapon weapon = new Shield();
		try {
			team.addChosenWeapon(weapon);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		team.removeChosenWeapon(weapon);
		assertFalse(team.getChosenWeapons().contains(weapon));
	}

	/**
	 * Test method for {@link model.Team#addWeapon(model.Weapon)}.
	 */
	@Test
	void testAddWeapon() {
		try {
			for (int i = 0; i < 5; i++) {
				team.addWeapon(new Shield());
			}
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		Weapon weapon = new Shield();

		FullTeamException thrown = assertThrows(FullTeamException.class, () -> team.addWeapon(weapon),
				"Expected team.addWeapon(weapon) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("Reached team max weapon limit!"));
		assertFalse(team.getWeapons().contains(weapon));
	}

	/**
	 * Test method for {@link model.Team#removeWeapon(model.Weapon)}.
	 */
	@Test
	void testRemoveWeapon() {
		Weapon weapon = new Shield();
		try {
			team.addWeapon(weapon);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		team.removeWeapon(weapon);
		assertFalse(team.getWeapons().contains(weapon));
	}

	/**
	 * Test method for {@link model.Team#randomlySelectPurchasables()}.
	 */
	@Test
	void testRandomlySelectPurchasables() {
		assertEquals(0, team.getChosenChampions().size());
		assertEquals(0, team.getChosenWeapons().size());
		for (Champion champion : team.getChampions()) {
			assertEquals(Fists.class, champion.getWeapon().getClass());
		}
		team.randomlySelectPurchasables();
		assertEquals(4, team.getChosenChampions().size());
		assertEquals(4, team.getChosenWeapons().size());
		for (Champion champion : team.getChosenChampions()) {
			assertNotEquals(Fists.class, champion.getWeapon().getClass());
		}
	}

	/**
	 * Test method for {@link model.Team#randomlySelectChampions()}.
	 */
	@Test
	void testRandomlySelectChampions() {
		assertEquals(0, team.getChosenChampions().size());
		team.randomlySelectChampions();
		assertEquals(4, team.getChosenChampions().size());
	}

	/**
	 * Test method for {@link model.Team#randomlySelectWeapons()}.
	 */
	@Test
	void testRandomlySelectWeapons() {
		assertEquals(0, team.getChosenWeapons().size());
		team.randomlySelectWeapons();
		assertEquals(4, team.getChosenWeapons().size());
	}

	/**
	 * Test method for {@link model.Team#assignChosenWeapons()}.
	 */
	@Test
	void testAssignChosenWeapons() {
		team.randomlySelectChampions();
		team.randomlySelectWeapons();
		for (Champion champion : team.getChampions()) {
			assertEquals(Fists.class, champion.getWeapon().getClass());
		}
		team.assignChosenWeapons();
		for (Champion champion : team.getChosenChampions()) {
			assertNotEquals(Fists.class, champion.getWeapon().getClass());
		}
	}

	/**
	 * Test method for {@link model.Team#unselectPurchasables()}.
	 */
	@Test
	void testUnselectPurchasables() {
		team.randomlySelectPurchasables();
		assertEquals(4, team.getChosenChampions().size());
		assertEquals(4, team.getChosenWeapons().size());
		team.unselectPurchasables();
		assertEquals(0, team.getChosenChampions().size());
		assertEquals(0, team.getChosenWeapons().size());
	}

	/**
	 * Test method for {@link model.Team#unselectChampions()}.
	 */
	@Test
	void testUnselectChampions() {
		team.randomlySelectPurchasables();
		assertEquals(4, team.getChosenChampions().size());
		team.unselectChampions();
		assertEquals(0, team.getChosenChampions().size());
	}

	/**
	 * Test method for {@link model.Team#unselectWeapons()}.
	 */
	@Test
	void testUnselectWeapons() {
		team.randomlySelectPurchasables();
		assertEquals(4, team.getChosenWeapons().size());
		team.unselectWeapons();
		assertEquals(0, team.getChosenWeapons().size());
	}

	/**
	 * Test method for {@link model.Team#unassignChosenWeapons()}.
	 */
	@Test
	void testUnassignChosenWeapons() {
		team.randomlySelectPurchasables();
		for (Champion champion : team.getChosenChampions()) {
			assertNotEquals(Fists.class, champion.getWeapon().getClass());
		}
		team.unassignChosenWeapons();
		for (Champion champion : team.getChampions()) {
			assertEquals(Fists.class, champion.getWeapon().getClass());
		}
	}

	/**
	 * Test method for {@link model.Team#rest()}.
	 */
	@Test
	void testRest() {
		team.setWeeklyChampionPurchased(true);
		for (Champion champion : team.getChampions()) {
			champion.setStamina(0f);
		}
		team.rest();
		assertFalse(team.isWeeklyChampionPurchased());
		for (Champion champion : team.getChampions()) {
			assertEquals(champion.getMaxStamina(), champion.getStamina());
		}
	}

}
