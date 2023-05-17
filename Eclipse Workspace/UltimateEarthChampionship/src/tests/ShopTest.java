/**
 *
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Shop;
import model.Team;
import model.Weapon;
import weapons.Chainsaw;
import weapons.Pickaxe;
import weapons.Shield;
import weapons.Sledgehammer;

/**
 * @author fha62
 *
 */
class ShopTest {

	private Shop shop;
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
	@BeforeEach
	void setUp() throws Exception {
		Shop newShop = new Shop();
		gameManager.setShop(newShop);
		shop = newShop;
	}

	/**
	 * Test method for {@link model.Shop#generateCatalogue()}.
	 */
	@Test
	void testGenerateCatalogue() {
		shop.generateCatalogue();
		assertTrue(shop.getAvailableChampions().size() == config.NUM_TEAMS);
		assertTrue(shop.getAvailableWeapons().size() == config.NUM_TEAMS);
	}

	/**
	 * Test method for {@link model.Shop#generateChampions()}.
	 */
	@Test
	void testGenerateChampions() {
		shop.generateChampions();
		boolean pass = true;
		for (Champion champion : shop.getAvailableChampions()) {
			if (!gameManager.getAllChampions().contains(champion)) {
				pass = false;
			}
			for (Team team : gameManager.getTeams()) {
				if (team.getChampions().contains(champion)) {
					pass = false;
				}
			}
		}
		if (shop.getAvailableChampions().size() != config.NUM_TEAMS) {
			pass = false;
		}
		assertTrue(pass);
	}

	/**
	 * Test method for {@link model.Shop#getRemainingChampions()}.
	 */
	@Test
	void testGetRemainingChampions() {
		ArrayList<Champion> remainingChampions = shop.getRemainingChampions();
		ArrayList<Champion> usedChampions = new ArrayList<Champion>();
		for (Team team : gameManager.getTeams()) {
			usedChampions.addAll(team.getChampions());
		}
		boolean pass = true;
		for (Champion champion : remainingChampions) {
			if (usedChampions.contains(champion)) {
				pass = false;
			}
		}
		assertTrue(pass);
	}

	/**
	 * Test method for {@link model.Shop#getRandomWeapon(java.util.ArrayList)}.
	 */
	@Test
	void testGetRandomWeapon() {
		Weapon weapon1 = new Shield();
		Weapon weapon2 = new Chainsaw();
		Weapon weapon3 = new Pickaxe();
		Weapon weapon4 = new Sledgehammer();
		ArrayList<Weapon> weaponList = new ArrayList<>(List.of(weapon1, weapon2, weapon3, weapon4));
		ArrayList<Class<?>> classList = new ArrayList<>(
				List.of(weapon1.getClass(), weapon2.getClass(), weapon3.getClass(), weapon4.getClass()));
		Weapon randomWeapon = shop.getRandomWeapon(weaponList);
		assertFalse(weaponList.contains(randomWeapon));
		assertTrue(classList.contains(randomWeapon.getClass()));
	}

	/**
	 * Test method for {@link model.Shop#getStartingChampions()}.
	 */
	@Test
	void testGetStartingChampions() {
		ArrayList<Champion> champions = shop.getStartingChampions();
		assertEquals(Configuration.NUM_SETUP_CHAMPIONS, champions.size());
	}

	/**
	 * Test method for {@link model.Shop#generateWeapons()}.
	 */
	@Test
	void testGenerateWeapons() {
		shop.generateWeapons();
		assertEquals(config.NUM_TEAMS, shop.getAvailableWeapons().size());
	}

	/**
	 * Test method for {@link model.Shop#removeChampion(model.Champion)}.
	 */
	@Test
	void testRemoveChampion() {
		Champion champion1 = new JohnDoe();
		Champion champion2 = new JohnDoe();
		Champion champion3 = new AdamSmith();
		shop.setAvailableChampions(new ArrayList<>(List.of(champion1, champion2, champion3)));
		shop.removeChampion(champion2);
		assertEquals(new ArrayList<>(List.of(champion1, champion3)), shop.getAvailableChampions());
	}

	/**
	 * Test method for {@link model.Shop#removeWeapon(model.Weapon)}.
	 */
	@Test
	void testRemoveWeapon() {
		Weapon weapon1 = new Shield();
		Weapon weapon2 = new Shield();
		Weapon weapon3 = new Chainsaw();
		shop.setAvailableWeapons(new ArrayList<>(List.of(weapon1, weapon2, weapon3)));
		shop.removeWeapon(weapon2);
		assertEquals(new ArrayList<>(List.of(weapon1, weapon3)), shop.getAvailableWeapons());
	}

}
