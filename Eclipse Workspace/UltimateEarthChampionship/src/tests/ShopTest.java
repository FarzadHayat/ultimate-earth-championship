package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champion.Champion;
import champion.champions.AdamSmith;
import champion.champions.JohnDoe;
import manager.GameManager;
import model.Configuration;
import model.Shop;
import model.Team;
import weapon.Weapon;
import weapon.weapons.Chainsaw;
import weapon.weapons.Pickaxe;
import weapon.weapons.Shield;
import weapon.weapons.Sledgehammer;

class ShopTest {

	private Shop shop;
	private GameManager gameManager;
	private Configuration config;
	
	@BeforeEach
	void setup() {
		gameManager = GameManager.getInstance();
		gameManager.initialize();
		config = Configuration.getInstance();
		shop = new Shop();
	}
	
	@Test
	void testGenerateCatalogue() {
		shop.generateCatalogue();
		assertTrue(shop.getAvailableChampions().size() == config.NUM_TEAMS &&
				shop.getAvailableWeapons().size() == config.NUM_TEAMS);
	}

	@Test
	void testGenerateChampions() {
		shop.generateChampions();
		boolean pass = true;
		for (Champion champion : shop.getAvailableChampions()) {
			if (!gameManager.getAllChampions().contains(champion)) {
				pass = false;
			}
			for (Team team : gameManager.getTeams()) {
				if (team.getAllChampions().contains(champion)) {
					pass = false;
				}
			}
		}
		if (shop.getAvailableChampions().size() != config.NUM_TEAMS) {
			pass = false;
		}
		assertTrue(pass);
	}

	@Test
	void testGetRandomChampion_oneArray() {
		Champion champion1 = new JohnDoe();
		Champion randomChampion = shop.getRandomChampion(new ArrayList<Champion>(List.of(champion1)));
		assertEquals(champion1, randomChampion);
	}

	@Test
	void testGenerateWeapons() {
		shop.generateWeapons();
		boolean pass = true;
		for (Weapon weapon : shop.getAvailableWeapons()) {
			if (!gameManager.getAllWeapons().contains(weapon)) {
				pass = false;
			}
			for (Team team : gameManager.getTeams()) {
				if (team.getAllWeapons().contains(weapon)) {
					pass = false;
				}
			}
		}
		if (shop.getAvailableWeapons().size() != config.NUM_TEAMS) {
			pass = false;
		}
		assertTrue(pass);
	}

	@Test
	void testGetRandomWeapon_oneArray() {
		Weapon weapon1 = new Shield();
		Weapon randomWeapon = shop.getRandomWeapon(new ArrayList<Weapon>(List.of(weapon1)));
		assertEquals(weapon1, randomWeapon);
	}
	
	@Test
	void testGetRandomWeapon_fourArray() {
		int numTests = 1000;
		Weapon weapon1 = new Shield();
		Weapon weapon2 = new Chainsaw();
		Weapon weapon3 = new Pickaxe();
		Weapon weapon4 = new Sledgehammer();
		ArrayList<Weapon> weaponList = new ArrayList<Weapon>(List.of(weapon1, weapon2, weapon3, weapon4));
		Map<Weapon, Integer> freq = new HashMap<>();
		for (int i = 0; i < numTests; i++) {
			Weapon randomWeapon = shop.getRandomWeapon(weaponList);
			freq.put(randomWeapon, freq.getOrDefault(randomWeapon, 0) + 1);
		}
		ArrayList<Boolean> inRange = new ArrayList<Boolean>();
		for (Weapon weapon : weaponList) {
			int repeats = freq.get(weapon);
			inRange.add(repeats <= (numTests / weaponList.size() + numTests * 0.1) &&
					repeats >= (numTests / weaponList.size() - numTests * 0.1));
		}
		for (boolean bool : inRange) {
			assertTrue(bool);
		}
	}

	@Test
	void testRemoveChampion() {
		Champion champion1 = new JohnDoe();
		Champion champion2 = new JohnDoe();
		Champion champion3 = new AdamSmith();
		shop.setAvailableChampions(new ArrayList<Champion>(List.of(champion1, champion2, champion3)));
		shop.removeChampion(champion2);
		assertEquals(new ArrayList<Champion>(List.of(champion1, champion3)), shop.getAvailableChampions());
	}

	@Test
	void testRemoveWeapon() {
		Weapon weapon1 = new Shield();
		Weapon weapon2 = new Shield();
		Weapon weapon3 = new Chainsaw();
		shop.setAvailableWeapons(new ArrayList<Weapon>(List.of(weapon1, weapon2, weapon3)));
		shop.removeWeapon(weapon2);
		assertEquals(new ArrayList<Weapon>(List.of(weapon1, weapon3)), shop.getAvailableWeapons());
	}

}
