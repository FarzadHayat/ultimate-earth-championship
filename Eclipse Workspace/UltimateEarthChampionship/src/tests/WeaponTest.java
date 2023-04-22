package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Purchasable;
import model.Weapon;
import weapons.Shield;

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
		assertEquals("Weapon [name=Shield, damageMultiplier=1, offenseBoost=-1, "
				+ "defenseBoost=3, price=10.0, priceChangeWeekly=0.9]", shield.toString());
	}
}
