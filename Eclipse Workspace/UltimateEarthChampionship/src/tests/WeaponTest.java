package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.Purchasable;
import weapon.Weapon;
import weapon.weapons.Shield;

class WeaponTest
{
	@Test
	void testGetPrice_asPurchasable() {
		Purchasable purchasable = new Shield();
		assertEquals(10, purchasable.getPrice());
	}
	
	@Test
	void testToString() {
		Weapon shield = new Shield();
		assertEquals("Weapon [Name: Shield | Damage boost: 1 | Offense boost: -1 "
				+ "| Defense boost: 3 | Price: 10.0 | Price change weekly: 0.9]", shield.toString());
	}
}
