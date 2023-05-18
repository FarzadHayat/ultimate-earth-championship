/**
 *
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import exception.FullTeamException;
import exception.InsufficientFundsException;
import model.Champion;
import model.Configuration;
import model.Team;
import model.Weapon;
import weapons.Fists;
import weapons.Shield;

/**
 * Unit test for the Weapon class.
 */
class WeaponTest {

	/**
	 * Test method for
	 * {@link model.Weapon#Weapon(java.lang.String, float, int, int, float, float, boolean)}.
	 */
	@Test
	void testWeapon() {
		Weapon weapon = new Fists();
		Object[] expectedStats = { "Fists", 1.0F, 0, 0, 0.0F, 1.0F, true, ImageIcon.class };
		Object[] realStats = { weapon.getName(), weapon.getDamageMultiplier(), weapon.getOffenseBoost(),
				weapon.getDefenseBoost(), weapon.getPrice(), weapon.getPriceChangeWeekly(), weapon.isDefaultWeapon(),
				weapon.getImage().getClass() };
		assertArrayEquals(expectedStats, realStats);
	}

	/**
	 * Test method for {@link model.Weapon#isDefault()}.
	 */
	@Test
	void testIsDefault_fists_true() {
		Weapon weapon = new Fists();
		assertTrue(weapon.isDefaultWeapon());
	}

	/**
	 * Test method for {@link model.Weapon#isDefault()}.
	 */
	@Test
	void testIsDefault_fists_false() {
		Weapon weapon = new Shield();
		assertFalse(weapon.isDefaultWeapon());
	}

	/**
	 * Test method for {@link model.Weapon#buy(model.Team)}.
	 */
	@Test
	void testBuy_emptyTeamWeapons() {
		Weapon weapon = new Shield();
		Team team = new Team(false, "Test", new ArrayList<Champion>());
		try {
			team.removeMoney(50);
		} catch (InsufficientFundsException e1) {
			e1.printStackTrace();
		}
		try {
			weapon.buy(team);
		} catch (InsufficientFundsException | FullTeamException e) {
			e.printStackTrace();
		}
		assertTrue(team.getWeapons().size() == 1);
		assertEquals(0.0F, team.getMoney());
	}

	/**
	 * Test method for {@link model.Weapon#buy(model.Team)}.
	 */
	@Test
	void testBuy_fullTeamWeapons() {
		Weapon weapon = new Shield();
		Team team = new Team(false, "Test", new ArrayList<Champion>());
		try {
			for (int i = 0; i < Configuration.NUM_WEAPONS; i++) {
				team.addWeapon(new Fists());
			}
		} catch (FullTeamException e1) {
			e1.printStackTrace();
		}
		FullTeamException thrown = assertThrows(FullTeamException.class, () -> weapon.buy(team),
				"Expected weapon.buy(team) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("Reached team max weapon limit!"));
		assertTrue(team.getWeapons().size() == Configuration.NUM_WEAPONS);
		assertEquals(100.0F, team.getMoney());
	}

	/**
	 * Test method for {@link model.Weapon#buy(model.Team)}.
	 */
	@Test
	void testBuy_notEnoughMoney() {
		Weapon weapon = new Shield();
		Team team = new Team(false, "Test", new ArrayList<Champion>());
		try {
			team.removeMoney(51);
		} catch (InsufficientFundsException e1) {
			e1.printStackTrace();
		}
		InsufficientFundsException thrown = assertThrows(InsufficientFundsException.class, () -> weapon.buy(team),
				"Expected weapon.buy(team) to throw, but it didn't.");
		assertTrue(thrown.getMessage().contentEquals("You do not have enough money to perform this action!"));
		assertTrue(team.getWeapons().size() == 0);
		assertEquals(49.0F, team.getMoney());
	}

	/**
	 * Test method for {@link model.Weapon#sell(model.Team)}.
	 */
	@Test
	void testSell() {
		Weapon weapon = new Shield();
		Team team = new Team(false, "Test", new ArrayList<Champion>());
		try {
			team.addWeapon(weapon);
		} catch (FullTeamException e) {
			e.printStackTrace();
		}
		weapon.sell(team);
		assertFalse(team.getWeapons().contains(weapon));
		assertEquals(150, team.getMoney());
	}

	/**
	 * Test method for {@link model.Weapon#toString()}.
	 */
	@Test
	void testToString() {
		Weapon weapon = new Fists();
		assertEquals(
				"Weapon [name=Fists, damageMultiplier=1.0, offenseBoost=0, defenseBoost=0, price=0.0, priceChangeWeekly=1.0]",
				String.valueOf(weapon));
	}

	/**
	 * Test method for {@link model.Weapon#clone()}.
	 */
	@Test
	void testClone() {
		Weapon weapon = new Shield();
		weapon.setName("Adam");
		Weapon clone = weapon.clone();
		assertNotEquals(weapon, clone);
		assertEquals("Adam", clone.getName());
	}

}
