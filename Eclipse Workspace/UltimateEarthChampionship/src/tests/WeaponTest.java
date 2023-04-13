package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import weapons.*;

class WeaponTest
{
	@Test
	void testToString() {
		Weapon shield = new Shield();
		assertEquals("<< Name: Shield | Damage boost: 1 | Offense boost: 1 "
				+ "| Defense boost: 4 | Price: 10.0 | Price change weekly: 0.9 >>", shield.toString());
	}
}
